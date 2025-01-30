import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BookRide extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Connection cn = null;
        PreparedStatement ps = null;

        try {
                    HttpSession hs=request.getSession(false);

            String pickup = request.getParameter("pickup");
            String dropoff = request.getParameter("dropoff");
            String time = request.getParameter("ride-time");
            String email=(String)hs.getAttribute("mail");


//            System.out.println("Email: " + email);
//            System.out.println("Password: " + pwd);

            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Taxi_project", "root", "Password0123456789");
           
           PreparedStatement  p=cn.prepareStatement("select * from driver where current_location=?&& free=?");
              p.setString(1, pickup);
              p.setBoolean(2, true);

          
        ResultSet s = p.executeQuery();
if (s.next()) {
    ps = cn.prepareStatement("INSERT INTO RideDetail (user_gamil, pick_up,drop_location,ride_book_Date) VALUES (?, ?,?,?)");
           
            ps.setString(1, email);
            ps.setString(2, pickup);
            ps.setString(3, dropoff);
            ps.setString(4, time);
            int rs = ps.executeUpdate();

            if (rs > 0) {
                out.println("Ride book sussesfull!!");
                 
                  //hs.setAttribute("pwd", pwd);                    
                    RequestDispatcher rd=request.getRequestDispatcher("user_home.html");
                rd.include(request, response);
            } else{
                out.print("Driver is not available for this date");
            }
    
                    //String name=rs.getString("name");
                  
                   
} else {
                out.println("We are not serve to this location");
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
