import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Retrieve session if it exists
        HttpSession hs = request.getSession(false);
        if (hs != null) {
            // Invalidate session to log out the user
            hs.invalidate();
        }

        // Display logout confirmation message
        out.println("<html><head><title>Logout</title></head><body>");
        out.println("<h2>You have successfully logged out.</h2>");
        
        // Include the login page (index.html) after the message
        RequestDispatcher rd = request.getRequestDispatcher("index.html");
        rd.include(request, response);
        
        out.println("</body></html>");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Handles user logout";
    }
}
