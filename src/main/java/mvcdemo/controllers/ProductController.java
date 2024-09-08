package mvcdemo.controllers;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DB;
import dao.ProductDao;
import mvcdemo.model.Authenticator;
import mvcdemo.model.User;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/ProductController")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Authenticator authenticator;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductController() {
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
				request.setAttribute("user", user);
				
				rd = request.getRequestDispatcher("/getProducts.jsp");
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
		Cookie[] cookies = request.getCookies();
		Map<String, String> cookiesMap = authenticator.parseCookie(cookies);
		RequestDispatcher rd = null;
		
		try {
			if(authenticator.checkSessionExists(cookiesMap)) {
				User user = authenticator.checkUserExistsAndGetUser(cookiesMap.get("ssid"));
				request.setAttribute("user", user);
				
				if (user.getIsAdmin() == 1) {
					String productName = request.getParameter("product_name");
		            String model = request.getParameter("model");
		            String brand = request.getParameter("brand");

		            // Assuming you have a ProductDAO class to handle database operations
		            ProductDao.insertProduct(productName, model, brand);
					rd = request.getRequestDispatcher("/getProducts.jsp");
					request.setAttribute("user", user);
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
