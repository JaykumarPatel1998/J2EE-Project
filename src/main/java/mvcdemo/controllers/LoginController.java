package mvcdemo.controllers;
 
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DB;
import mvcdemo.model.Authenticator;
import mvcdemo.model.User;
 
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	public LoginController() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
 
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		RequestDispatcher rd = null;
 
		try {
			Authenticator authenticator = new Authenticator();
			User user = authenticator.authenticate(username, password);
			
			UUID sessionId = UUID.randomUUID();
			
			Cookie myCookie = new Cookie("ssid", sessionId.toString());
			myCookie.setMaxAge(3600); //1 hour
			response.addCookie(myCookie);
			
			String sql = "update user set session_id=? where user_id=?";
			PreparedStatement st = DB.getConnection().prepareStatement(sql);
			st.setString(1, sessionId.toString());
			st.setInt(2, user.getUserId());
			
			st.executeUpdate();
			
			if(user.getIsAdmin() == 1) {
				rd = request.getRequestDispatcher("/adminPanel.html");
			} else {
				rd = request.getRequestDispatcher("/userPanel.jsp");
			}
			
			
			request.setAttribute("user", user);
		} catch (Exception e) {
			rd = request.getRequestDispatcher("/error.jsp");
			request.setAttribute("error", e.getMessage());
		}
		
		rd.forward(request, response);
	}  
 
}