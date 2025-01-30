import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DriverCompleteInfo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
 final String email = request.getParameter("email");
        Connection cn = null;
        PreparedStatement ps = null;

        try {
            
            
            
            
                        //final String email = request.getParameter("email");
           out.println(email);
            // Handling form submission
            String name = request.getParameter("name");
            String mobile = request.getParameter("mob");
            String address = request.getParameter("address");
            String aadharNo = request.getParameter("aadhar_no");
            String vehicleNumber = request.getParameter("vehicle_number");
            String vehicleType = request.getParameter("vehicle_type");
            String seatingCapacity = request.getParameter("seating_capacity");
            String gender = request.getParameter("gender");
            String vehicleName = request.getParameter("vehicle_name");
out.println("Name: " + name);
out.println("Mobile: " + mobile);
out.println("Address: " + address);
out.println("Aadhar No: " + aadharNo);
out.println("Vehicle Number: " + vehicleNumber);
out.println("Vehicle Type: " + vehicleType);
out.println("Seating Capacity: " + seatingCapacity);
out.println("Gender: " + gender);
out.println("Vehicle Name: " + vehicleName);
out.println("Email: " + email);

            // Database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Taxi_project", "root", "Password0123456789");

            // Insert the driver and vehicle information into the database
            ps = cn.prepareStatement("UPDATE driver SET name = ?, mobile = ?, address = ?, aadhar_no = ?, vehicle_number = ?, vehicle_type = ?, seating_capacity = ?, gender = ?, vehicle_name = ? WHERE gmail = ?");
            ps.setString(1, name);
            ps.setString(2, mobile);
            ps.setString(3, address);
            ps.setString(4, aadharNo);
           
            ps.setString(5, vehicleNumber);
            ps.setString(6, vehicleType);
            ps.setString(7, seatingCapacity);
            ps.setString(8, gender);
            ps.setString(9, vehicleName);
            ps.setString(10,email );
            int rs = ps.executeUpdate();

            if (rs > 0) {
                out.println("<h2>informatin fill succefully  we will contact you soon!</h2>");
            } else {
                out.println("<h2>informatin  fill failed. Please try again.</h2>");
            }
        } catch (Exception e) {
            out.println("<h2>An error occurred: " + e.getMessage() + "</h2>");
        } finally {
            // Display the form
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>Complete Driver Information</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; }");
            out.println("h1 { text-align: center; }");
            out.println("form { max-width: 500px; margin: 0 auto; background: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); }");
            out.println("input[type='text'], input[type='email'], select, input[type='submit'] { width: 100%; padding: 10px; margin: 10px 0; border: 1px solid #ccc; border-radius: 5px; }");
            out.println("input[type='submit'] { background-color: #5cb85c; color: white; border: none; cursor: pointer; }");
            out.println("input[type='submit']:hover { background-color: #4cae4c; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Fill Driver Information</h1>");
            out.println("<form action='DriverCompleteInfo' method='POST'>");
            out.println("<input type='text' name='name' placeholder='Enter full name' required>");
            out.println("<input type='text' name='mob' placeholder='Enter mobile number' required>");
            out.println("<input type='text' name='address' placeholder='Enter address' required>");
            out.println("<input type='text' name='aadhar_no' placeholder='Enter Aadhar number' required>");
            out.println("<input type='email' name='email' placeholder='Enter email address' required readonly value='" + email + "'>");
             out.println("<input type='text' name='vehicle_number' placeholder='Enter vehicle number' required>");
            out.println("<input type='text' name='vehicle_type' placeholder='Enter vehicle type (e.g., Sedan, SUV)' required>");
            out.println("<input type='text' name='seating_capacity' placeholder='Enter seating capacity' required>");
            out.println("<select name='gender' required>");
            out.println("<option value='' disabled selected>Select gender</option>");
            out.println("<option value='Male'>Male</option>");
            out.println("<option value='Female'>Female</option>");
            out.println("</select>");
            out.println("<input type='text' name='vehicle_name' placeholder='Enter vehicle name (e.g., Toyota, Honda)' required>");
            out.println("<input type='submit' value='Submit'>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
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
        return "DriverCompleteInfo Servlet";
    }
}
