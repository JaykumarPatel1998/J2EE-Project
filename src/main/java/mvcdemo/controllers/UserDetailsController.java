package mvcdemo.controllers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDao;
import mvcdemo.model.Authenticator;
import mvcdemo.model.User;

/**
 * Servlet implementation class UserDetailsController
 */
@WebServlet("/UserDetailsController")
public class UserDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Authenticator authenticator;
    
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDetailsController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		authenticator = new Authenticator();
	}

    //get all users
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		Map<String, String> cookiesMap = authenticator.parseCookie(cookies);
		RequestDispatcher rd = null;
		
		try {
			if(authenticator.checkSessionExists(cookiesMap)) {
				User user = authenticator.checkUserExistsAndGetUser(cookiesMap.get("ssid"));
				request.setAttribute("user", user);
				
				if (user.getIsAdmin() == 1) {
					rd = request.getRequestDispatcher("/getUsers.jsp");
					request.setAttribute("authorized", "true");
				} else {
					throw new RuntimeException();
				}
			} else {
				rd = request.getRequestDispatcher("/login.jsp");
			}
		} catch (Exception e) {
			request.setAttribute("message", "401 : Unauthorized Access");
			rd = request.getRequestDispatcher("/error.jsp");
		}
		rd.forward(request, response);
	}


}
