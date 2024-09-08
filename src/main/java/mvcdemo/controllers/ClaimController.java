package mvcdemo.controllers;
 
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ClaimDao;
 
@WebServlet("/ClaimController")
public class ClaimController extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get parameters from the form
            int registration_id = Integer.parseInt(request.getParameter("registration_id"));
            String date_of_claim = request.getParameter("date_of_claim");
            String description = request.getParameter("description");
            String status = request.getParameter("status");
 
            // Insert the claim into the database using ClaimDao
            ClaimDao.insertClaim(registration_id, date_of_claim, description, "submitted");
 
            // Redirect to a success page or display a success message
            response.sendRedirect("viewClaims.jsp");
        } catch (Exception e) {
            // Handle any exceptions, you can redirect to an error page or display an error message
            request.setAttribute("errorMessage", "An error occurred while processing your request.");
            request.getRequestDispatcher("createClaim.jsp").forward(request, response);
        }
    }
}