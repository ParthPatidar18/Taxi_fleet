import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Registration extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Connection cn = null;
        PreparedStatement ps = null;

        try {
            String email = request.getParameter("email");
            String pwd = request.getParameter("pwd");

            System.out.println("Email: " + email);
            System.out.println("Password: " + pwd);

            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Taxi_project", "root", "Password0123456789");

            ps = cn.prepareStatement("INSERT INTO user (gamil, pwd) VALUES (?, ?)");
            ps.setString(1, email);
            ps.setString(2, pwd);
            int rs = ps.executeUpdate();

            if (rs > 0) {
                out.println("Registration successful!");
            } else {
                out.println("Registration failed. Please try again.");
            }
        } catch (Exception e) {
            out.println("An error occurred: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (Exception e) {
                out.println("Error closing resources: " + e.getMessage());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
