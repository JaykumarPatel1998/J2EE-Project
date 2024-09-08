package mvcdemo.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DB;
import dao.ProductDao;
import dao.RegistrationDao;
import mvcdemo.model.Authenticator;
import mvcdemo.model.Registration;
import mvcdemo.model.User;

/**
 * Servlet implementation class RegistrationController
 */
@WebServlet("/RegistrationController")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Authenticator authenticator;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		authenticator = new Authenticator();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		Map<String, String> cookiesMap = authenticator.parseCookie(cookies);
		RequestDispatcher rd = null;
		
		try {
			if(authenticator.checkSessionExists(cookiesMap)) {
				User user = authenticator.checkUserExistsAndGetUser(cookiesMap.get("ssid"));
				rd = request.getRequestDispatcher("/registerProduct.jsp");
				request.setAttribute("user", user);
			} else {
				rd = request.getRequestDispatcher("/login.jsp");
			}
		} catch (Exception e) {
			request.setAttribute("message", "401 : Unauthorized Access");
			rd = request.getRequestDispatcher("/error.jsp");
		}
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("product"));
        String serialNumber = request.getParameter("serial_number");
        
        

        Cookie[] cookies = request.getCookies();
		Map<String, String> cookiesMap = authenticator.parseCookie(cookies);
		RequestDispatcher rd = null;
		
		try {
			if(authenticator.checkSessionExists(cookiesMap)) {
				User user = authenticator.checkUserExistsAndGetUser(cookiesMap.get("ssid"));
				Registration registration = new Registration(123, user.getUserId(), productId, serialNumber, (new Date()).toString());
				
				RegistrationDao.insertRegistration(registration);
				rd = request.getRequestDispatcher("/getUserRegistrations.jsp");
				request.setAttribute("user", user);
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
