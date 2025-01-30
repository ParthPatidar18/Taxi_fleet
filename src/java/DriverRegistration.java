import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DriverRegistration extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Connection cn = null;
        PreparedStatement ps = null;

        try {
            String email = request.getParameter("email");
            String pwd = request.getParameter("pwd");
//
//            System.out.println("Email: " + email);
//            System.out.println("Password: " + pwd);

            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Taxi_project", "root", "Password0123456789");

            ps = cn.prepareStatement("INSERT INTO Driver (gmail, pwd) VALUES (?, ?)");
            ps.setString(1, email);
            ps.setString(2, pwd);
            int rs = ps.executeUpdate();

            if (rs > 0) {
                out.println("we will contact you soon !!");
                String sub = "Complete Your Driver Registration – Action Required";
               String msg = "Dear " + email + ",\n" +
"\n" +
"Thank you for your interest in joining our taxi service team! We're excited to have you on board.\n" +
"\n" +
"To complete your registration, please provide your vehicle and personal details by clicking on the link below:\n" +
"http://localhost:8080/Taxi_/DriverCompleteInfo?email=" + java.net.URLEncoder.encode(email, "UTF-8") + "\n" +
"Complete Your Registration\n" +
"\n" +
"In this step, you'll be required to fill out the following details:\n" +
"\n" +
"Vehicle information (model, license plate, etc.)\n" +
"Driving license details\n" +
"Contact information\n" +
"Additional documents as needed\n" +
"Please make sure to complete your registration as soon as possible to begin receiving ride requests.\n" +
"\n" +
"If you have any questions or need assistance, feel free to reach out to us at [support email].\n" +
"\n" +
"We look forward to working with you!\n" +
"\n" +
"Best regards,\n" +
"[Your Company Name]\n" +
"[Your Contact Information]\n" +
"[Your Website URL]\n";

                Mailer.send(email, sub, msg);
                out.println("Email sent successfully to " + email);
            } else {
                out.println("Registration failed. Please try again.");
            }
        } catch (Exception e) {
            out.println("An error occurred: " + e.getMessage());
        
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
