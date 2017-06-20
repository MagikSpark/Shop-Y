package ShoppingCart;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginCheck() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String user=request.getParameter("username");
        String pass=request.getParameter("password");
        ResultSet rs=null;

        PrintWriter out = response.getWriter();
        try {
           Class.forName("org.apache.derby.jdbc.ClientDriver");
           String url="jdbc:derby://localhost:1527/sample";
		   String pageurl="http://localhost:8080/YeshaShoppingCart";
           Connection con=DriverManager.getConnection(url,"user","123");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginCheck</title>");  
            out.println("</head>");
            out.println("<body>");
          
            java.sql.Statement stmt=con.createStatement();
            String sqlQuery="select * from APP.USERS where APP.USERS.USERNAME='"+user+"'";
            rs = stmt.executeQuery(sqlQuery);
           String output="",unm="",pswd="";

            while (rs.next())
            {
                unm=""+rs.getString("USERNAME").toString();
                pswd=""+rs.getString("PASSWORD").toString();

            }
          // out.println(output);
           if(unm.equals(user) && pswd.equals(pass))
           {

        	   out.println("<h1>Hello Every one Specially "+user+"</h1>");
        	   out.println("<form action='"+pageurl+"' method='get'>");
        	   HttpSession session=request.getSession(true);
        	   session.setAttribute("username", user);
        	   session.setAttribute("status","login");
        	   out.println("<input type='submit' name='sub' value='next page'>");
        	   response.sendRedirect(""+pageurl+"");
           }else{
                 response.sendRedirect(""+pageurl+"/Login.jsp");
           }
             out.println("</body>");
            out.println("</html>");
           
        } finally { 
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginCheck.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginCheck.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginCheck.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginCheck.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
