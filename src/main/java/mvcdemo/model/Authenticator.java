package mvcdemo.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;

import dao.DB;
import dao.UserDao;

public class Authenticator {
 
	public User authenticate(String username, String password) throws Exception {
			UserDao dao = new UserDao(DB.getConnection());
			return dao.authenticate(username, password);
	}
	
	public Map<String, String> parseCookie(Cookie[] cookies) {
		Map<String, String> cookieMap = new HashMap<String, String>();
		
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        cookieMap.put(cookie.getName(), cookie.getValue());
		    }
		}
		
		return cookieMap;
	}
	
	public boolean checkSessionExists(Map<String, String> cookies) {
		return cookies.containsKey("ssid");
	}
	
	public User checkUserExistsAndGetUser(String sessionId) throws Exception {
		String sql = "select * from user where session_id=?";
		PreparedStatement st = DB.getConnection().prepareStatement(sql);
		st.setString(1, sessionId.toString());
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
			throw new RuntimeException("user not found");
		}
	}
}