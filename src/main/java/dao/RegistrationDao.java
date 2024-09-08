package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import mvcdemo.model.Registration;

public class RegistrationDao {
	 public static void insertRegistration(Registration registration) {
	        try {
	            Connection connection = DB.getConnection();
	            String insertQuery = "INSERT INTO registration (user_id, product_id, serial_number, purchase_date) VALUES (?, ?, ?, ?)";
	            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
	            preparedStatement.setInt(1, registration.getUserId());
	            preparedStatement.setInt(2, registration.getProductId());
	            preparedStatement.setString(3, registration.getSerialNumber());
	            preparedStatement.setString(4, registration.getPurchaseDate());
	            preparedStatement.executeUpdate();
	            preparedStatement.close();
	            connection.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	  }
	 
	 public static List<Registration> getRegistrationsByUserId(int userId) {
	        List<Registration> registrations = new ArrayList<>();
	        try {
	            Connection connection = DB.getConnection();
	            String selectQuery = "SELECT * FROM registration WHERE user_id = ?";
	            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
	            preparedStatement.setInt(1, userId);
	            ResultSet resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	                int registrationId = resultSet.getInt("registration_id");
	                int productId = resultSet.getInt("product_id");
	                String serialNumber = resultSet.getString("serial_number");
	                String purchaseDate = resultSet.getString("purchase_date");

	                Registration registration = new Registration(registrationId, userId, productId, serialNumber, purchaseDate);
	                registrations.add(registration);
	            }

	            resultSet.close();
	            preparedStatement.close();
	            connection.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return registrations;
	    }

}
