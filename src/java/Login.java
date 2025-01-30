/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author parth
 */
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  request
     * @param response  response
     * @throws ServletException if a -specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
            String gamil=request.getParameter("email");
            String pwd=request.getParameter("pwd");
//            out.println(gamil);
//            out.println(pwd);

           Class.forName("com.mysql.cj.jdbc.Driver");
          Connection  cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Taxi_project", "root", "Password0123456789");
            PreparedStatement ps=cn.prepareStatement("select * from user where gamil=? and pwd=?");
            PreparedStatement ps2=cn.prepareStatement("select * from driver where gmail=? and pwd=?");

        ps.setString(1, gamil);
        ps.setString(2, pwd);
        ps2.setString(1, gamil);
        ps2.setString(2, pwd);
        ResultSet rs = ps.executeQuery();
         ResultSet rs2 = ps2.executeQuery();
if (rs.next()) {
    //user login
    out.println("Login successful");
    
                    //String name=rs.getString("name");
                  
                    HttpSession hs=request.getSession();
                    
                  hs.setAttribute("gamil", gamil);
                  //hs.setAttribute("pwd", pwd);                    
                    RequestDispatcher rd=request.getRequestDispatcher("user_home.html");
                rd.forward(request, response);
} else if (rs2.next()) {
    //driver login 
    out.println("Login successful");
    
                    //String name=rs.getString("name");
                  
                    HttpSession hs1=request.getSession();
                    
                  hs1.setAttribute("gamil", gamil);
                  //hs.setAttribute("pwd", pwd);                    
                    RequestDispatcher rd=request.getRequestDispatcher("driver_home.html");
                rd.forward(request, response);}
else {
    out.println("Login failed");
}}
        catch(Exception e){
            out.println(e.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
