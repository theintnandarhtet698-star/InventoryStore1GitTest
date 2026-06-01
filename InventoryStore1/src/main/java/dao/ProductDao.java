package dao;

import java.sql.*;
import java.util.*;
import model.Product;

public class ProductDao {
    private String url = "jdbc:mysql://localhost:3306/inventory_db1";
    private String user = "root";
    private String password = ""; 

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
        return connection;
    }

    public void insertProduct(Product p) throws SQLException {
        try (Connection conn = getConnection(); 
             PreparedStatement ps = conn.prepareStatement("INSERT INTO products(name, price, quantity) VALUES(?,?,?)")) {
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setInt(3, p.getQuantity());
            ps.executeUpdate();
        }
    }
    public List<Product> selectAllProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection conn = getConnection(); 
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM products")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                products.add(new Product(
                    rs.getInt("id"), 
                    rs.getString("name"), 
                    rs.getDouble("price"), 
                    rs.getInt("quantity")
                ));
            }
        } catch (SQLException e) { 
            e.printStackTrace(); 
        }
        return products;
    }

    public Product selectProduct(int id) {
        Product product = null;
        try (Connection conn = getConnection(); 
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM products WHERE id = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                product = new Product(
                    id, 
                    rs.getString("name"), 
                    rs.getDouble("price"), 
                    rs.getInt("quantity")
                );
            }
        } catch (SQLException e) { 
            e.printStackTrace(); 
        }
        return product;
    }

    public boolean updateProduct(Product p) throws SQLException {
        boolean rowUpdated;
        try (Connection conn = getConnection(); 
             PreparedStatement ps = conn.prepareStatement("UPDATE products SET name = ?, price = ?, quantity = ? WHERE id = ?")) {
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setInt(3, p.getQuantity());
            ps.setInt(4, p.getId());
            rowUpdated = ps.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public void deleteProduct(int id) throws SQLException {
        try (Connection conn = getConnection(); 
             PreparedStatement ps = conn.prepareStatement("DELETE FROM products WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}