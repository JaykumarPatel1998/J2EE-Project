package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import mvcdemo.model.Product;

public class ProductDao {
	
    public static void insertProduct(String productName, String model, String brand) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            // Assuming you have a table named "product"
            String query = "INSERT INTO product (product_name, model, brand) VALUES (?, ?, ?)";
            connection = DB.getConnection();

            ps = connection.prepareStatement(query);
            ps.setString(1, productName);
            ps.setString(2, model);
            ps.setString(3, brand);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM product";
            connection = DB.getConnection();

            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String productName = rs.getString("product_name");
                String model = rs.getString("model");
                String brand = rs.getString("brand");

                Product product = new Product(productId, productName, model, brand);
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }
}
