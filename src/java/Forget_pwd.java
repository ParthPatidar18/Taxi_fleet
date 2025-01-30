import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Forget_pwd extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String to = request.getParameter("mail"); // Get email from the form

            if (to == null || to.isEmpty()) {
                out.println("Please provide an email.");
            } else {
                // Define subject and message body
                String sub = "Create a new password by clicking the link below";
                String msg = "http://localhost:8080/Taxi_/Forget_pwd/udpatepwd.html?mail=" + to;

                // Send the email
                Mailer.send(to, sub, msg);
                out.println("Email sent successfully to " + to);
            }
        } catch (Exception e) {
            out.println("Failed to send the email. Error: " + e.getMessage());
        } finally {
            // Output the form again after processing
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Forget Password</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Forgot Password</h2>");
            out.println("<form action='Forget_pwd' method='POST'>");
            out.println("<input type='email' name='mail' placeholder='Enter Email' required>");
            out.println("<input type='submit' value='Send'>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
            out.close(); // Ensure PrintWriter is closed
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
        return "Servlet for handling forgotten passwords";
    }
}
