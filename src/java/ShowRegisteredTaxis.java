import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowRegisteredTaxis extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection cn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Taxi_project", "root", "Password0123456789");
            PreparedStatement ps = cn.prepareStatement("SELECT name, gmail, mobile, vehicle_name, vehicle_number FROM driver where approver=?");
              ps.setBoolean(1, true);
            ResultSet rs = ps.executeQuery();
            
            // Start writing HTML response
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Registered Taxis</title>");
            out.println("<style>");
            out.println("body {font-family: Arial, sans-serif; background-color: #f4f4f4; color: #333;}");
            out.println(".container {width: 80%; margin: 0 auto;}");
            out.println("table {width: 100%; border-collapse: collapse; margin-top: 20px;}");
            out.println("th, td {padding: 12px; border: 1px solid #ddd; text-align: left;}");
            out.println("th {background-color: #3498db; color: white;}");
            out.println("h1 {text-align: center; margin-top: 20px;}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<h1>Registered Taxis</h1>");

            // Check if there are any records
            if (!rs.isBeforeFirst()) {
                out.println("<p>No registered taxis found.</p>");
            } else {
                out.println("<table>");
                out.println("<tr>");
                out.println("<th>Name</th>");
                out.println("<th>Email</th>");
                out.println("<th>Mobile</th>");
                out.println("<th>Vehicle Name</th>");
                out.println("<th>Vehicle Number</th>");
                out.println("</tr>");
                
                // Loop through the ResultSet and display records in a table
                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getString("name") + "</td>");
                    out.println("<td>" + rs.getString("gmail") + "</td>");
                    out.println("<td>" + rs.getString("mobile") + "</td>");
                    out.println("<td>" + rs.getString("vehicle_name") + "</td>");
                    out.println("<td>" + rs.getString("vehicle_number") + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            }

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                out.println("<p>Error closing connection: " + e.getMessage() + "</p>");
            }
            out.close();
        }
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
        return "Servlet for displaying registered taxis.";
    }
}
