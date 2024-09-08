package dao;

import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mvcdemo.model.User;

public class UserDao {
	
	private Connection conn;
	
	public UserDao(Connection conn){
		this.conn = conn;
	}
	
	public User authenticate(String username, String password) throws Exception {
		String sql = "select * from user where username = ? and password = ?";
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, username);
		st.setString(2, password);
		
		ResultSet rs = st.executeQuery();
		
		if (rs.next()) {
			int userId = rs.getInt(1);
			String usernameFromDB = rs.getString(2);
			String passwordFromDB = rs.getString(3);
			String name = rs.getString(4);
			String address = rs.getString(5);
			String phoneNumber = rs.getString(6);
			String email = rs.getString(7);
			int isAdmin = rs.getInt(8);
			
			return new User(userId, usernameFromDB, passwordFromDB, name, address, phoneNumber, email, isAdmin);
		} else {
			throw new RuntimeException("invalid username and/or password");
		}
		
		
	}
	
	public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DB.getConnection();
            String query = "SELECT * FROM user"; // Assuming your table name is 'user'
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String username = resultSet.getString("username");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                int isAdmin = resultSet.getInt("is_admin");

                User user = new User(userId, username, null, name, null, null, email, isAdmin);
                users.add(user);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }
        return users;
    }

	public static User getUserById(int userId) {
        User user = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Assuming you have a table named "user"
            String query = "SELECT * FROM user WHERE user_id = ?";
            connection = DB.getConnection();

            ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            if (rs.next()) {
                String username = rs.getString("username");
                String name = rs.getString("name");
                String email = rs.getString("email");
                int isAdmin = rs.getInt("is_admin");

                user = new User(userId, username, null, name, null, null, email, isAdmin);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }

        return user;
    }
}
