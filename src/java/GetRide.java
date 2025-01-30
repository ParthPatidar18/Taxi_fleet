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
public class GetRide extends HttpServlet {

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
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("gamil") == null) {
                out.println("<html><body>");
                out.println("<h2>Session expired. Please log in again.</h2>");
                out.println("<a href='login.html'>Go to Login</a>");
                out.println("</body></html>");
                return;
            }

            // Get the driver email from the session
            //String gmail = (String) session.getAttribute("mail");

            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create a connection to the database
            conn = DriverManager.getConnection(url, username, password);

            // SQL query to fetch upcoming rides for the driver

            stmt = conn.prepareStatement("SELECT user_gamil, pick_up, drop_location, ride_book_Date FROM ridedetail WHERE status = 'unbooked'");
            //stmt.setString(1, gmail);

            // Execute the query
            rs = stmt.executeQuery();

            // Generate HTML for the upcoming rides section
            
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
            out.println("<table border='1'>");
            out.println("<tr><th>user gmail</th><th>Pickup Location</th><th>Drop-off Location</th><th>Date</th><th>click for book</th></tr>");

            // Loop through the result set and display each upcoming ride
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString("user_gamil") + "</td>");
                out.println("<td>" + rs.getString("pick_up") + "</td>");
                out.println("<td>" + rs.getString("drop_location") + "</td>");
                out.println("<td>" + rs.getDate("ride_book_Date") + "</td>");
               // out.println("<td>" + rs.getTime("ride_time") + "</td>");
               out.println("<td><a href='SelectRide?gmail=" + rs.getString("user_gamil") + "'>book</a></td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("<a href='driver_home.html'>Go back to Dashboard</a>");
            out.println("</body></html>");

        } catch (Exception e) {
            out.println(e.getMessage());
        
            out.println("<html><body>");
            out.println("<h2>Error retrieving booking rides: " + e.getMessage() + "</h2>");
            out.println("<a href='driver_home.html'>Go back to Dashboard</a>");
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
