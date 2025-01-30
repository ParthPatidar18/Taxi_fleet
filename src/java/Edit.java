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

public class Edit extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // Database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Taxi_project", "root", "Password0123456789");

            // If an approval action is sent
            String gmail = request.getParameter("gmail");
            if (gmail != null) {
                // Update the driver to approved
                PreparedStatement psApprove = cn.prepareStatement("UPDATE driver SET approver = TRUE WHERE gmail = ?");
                psApprove.setString(1, gmail);
                int rowsUpdated = psApprove.executeUpdate();

                if (rowsUpdated > 0) {
                    out.println("<h3>Driver with Gmail " + gmail + " has been approved.</h3>");
                    
                out.println("mail has been send to driver !!");
                String sub = "Approval Notification - You Are Ready to Start Working";
               String msg = "Hello Sir,\n" +
"\n" +
"I hope this email finds you well.\n" +
"\n" +
"We are pleased to inform you that your account has been approved, and you can now log in to our platform and begin your work. We are excited to have you on board and look forward to your contributions.\n" +
"\n" +
"To get started, simply log in using your gamil and password \n" +
"\n" +
"If you have any questions or need assistance, feel free to reach out.\n" +
"\n" +
"Best regards,\n" +
"parth\n" +
"\n" +
"Taxi Service\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n";

                Mailer.send(gmail, sub, msg);
                out.println("Email sent successfully to " + gmail);
                    
                } else {
                    out.println("<h3>Failed to approve driver with Gmail " + gmail + "</h3>");
                }
            }

            // Display all drivers
            PreparedStatement ps = cn.prepareStatement("SELECT name, gmail, approver FROM driver");
            ResultSet rs = ps.executeQuery();

            out.println("<table border='1'>");
            out.println("<tr><th>Name</th><th>Email</th><th>Status</th><th>Action</th></tr>");
            
            while (rs.next()) {
                String driverName = rs.getString("name");
                String driverGmail = rs.getString("gmail");
                boolean approver = rs.getBoolean("approver");
                
                out.println("<tr>");
                out.println("<td>" + driverName + "</td>");
                out.println("<td>" + driverGmail + "</td>");
                out.println("<td>" + (approver ? "Approved" : "Pending") + "</td>");
               

                out.println("</tr>");
            }

            out.println("</table>");
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        } finally {
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
        return "Edit Driver Servlet";
    }
}
