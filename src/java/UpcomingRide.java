import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UpcomingRidesServlet")
public class UpcomingRide extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/Taxi_project";
        String username = "root";
        String password = "Password0123456789";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Retrieve the current session (or return an error if no session exists)
            HttpSession hs1 = request.getSession(false);
            if (hs1 == null || hs1.getAttribute("gamil") == null) {
                out.println("<html><body>");
                out.println("<h2>Session expired. Please log in again.</h2>");
                out.println("<a href='login.html'>Go to Login</a>");
                out.println("</body></html>");
                return;
            }

            // Get the driver email from the session
            String gmail = (String) hs1.getAttribute("gamil");

            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create a connection to the database
            conn = DriverManager.getConnection(url, username, password);

            // SQL query to fetch upcoming rides for the driver
            String sql = "SELECT user_gamil, pick_up, drop_location, ride_book_Date,driver_gamil "
                       + "FROM ridedetail WHERE driver_gamil = ? or user_gamil=? and ride_status = 'upcoming'";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, gmail);
            stmt.setString(2, gmail);
            // Execute the query
            rs = stmt.executeQuery();

            // Generate HTML with inline CSS for the upcoming rides section
            out.println("<html><head><style>");
            out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f9; }");
            out.println("h2 { color: #333; }");
            out.println("table { width: 80%; margin: 20px auto; border-collapse: collapse; }");
            out.println("table, th, td { border: 1px solid #ccc; }");
            out.println("th, td { padding: 10px; text-align: left; }");
            out.println("th { background-color: #f2f2f2; }");
            out.println("tr:nth-child(even) { background-color: #f9f9f9; }");
            out.println("a { text-decoration: none; background-color: #4CAF50; color: white; padding: 10px 20px; display: inline-block; margin-top: 20px; }");
            out.println("a:hover { background-color: #45a049; }");
            out.println("</style></head><body>");
            
            out.println("<h2>Upcoming Rides</h2>");
            out.println("<table>");
            out.println("<tr><th>User Email</th><th>Pickup Location</th><th>Drop-off Location</th><th>Date</th><th>Driver Email</th></tr>");

            // Loop through the result set and display each upcoming ride
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString("user_gamil") + "</td>");
                out.println("<td>" + rs.getString("pick_up") + "</td>");
                out.println("<td>" + rs.getString("drop_location") + "</td>");
                out.println("<td>" + rs.getDate("ride_book_Date") + "</td>");    
                out.println("<td>" + rs.getString("driver_gamil") + "</td>");

                
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body></html>");

        } catch (Exception e) {
            out.println(e.getMessage());
            out.println("<html><body>");
            out.println("<h2>Error retrieving upcoming rides: " + e.getMessage() + "</h2>");
            out.println("</body></html>");
        } finally {
            // Close database resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for displaying driver's upcoming rides";
    }
}
