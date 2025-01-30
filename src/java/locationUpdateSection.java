import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;

@WebServlet("/LocationUpdateServlet")
public class locationUpdateSection extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Get the location parameter from the request
        String location = request.getParameter("location");
       
        // Database connection parameters (update with your credentials)
        String url = "jdbc:mysql://localhost:3306/Taxi_project ";
        String username = "root";
        String password = "Password0123456789";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            
            HttpSession hs=request.getSession(false);
        
        String gmail=(String)hs.getAttribute("gamil");
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create a connection to the database
            conn = DriverManager.getConnection(url, username, password);

            // Prepare the SQL statement for updating the driver's location
           // String sql = "UPDATE drivers SET current_location = ? WHERE gmail = ?";
            stmt = conn.prepareStatement("UPDATE driver SET current_location = ? WHERE gmail = ?");
            stmt.setString(1, location);
            stmt.setString(2, gmail);

            // Execute the update
            int rowsUpdated = stmt.executeUpdate();

            // Check if the update was successful
            if (rowsUpdated > 0) {
                out.println("<html><body>");
                out.println("<h2>Location updated successfully!</h2>");
                out.println("<a href='driver_home.html'>Go back to Dashboard</a>");
                out.println("</body></html>");
            } else {
                out.println("<html><body>");
                out.println("<h2>Failed to update location!</h2>");
                out.println("<a href='driver_home.html'>Go back to Dashboard</a>");
                out.println("</body></html>");
            }

        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirect to the POST method
        doPost(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet for updating driver location";
    }
}
