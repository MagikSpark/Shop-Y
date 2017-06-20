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
 * Servlet implementation class MyCatalog
 */
@WebServlet("/MyCatalog")
public class MyCatalog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyCatalog() {
        super();
        // TODO Auto-generated constructor stub
    }

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       
        try {
             ResultSet rs=null;
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String url="jdbc:derby://localhost:1527/sample";
            Connection con=DriverManager.getConnection(url,"user","123");
            java.sql.Statement stmt=con.createStatement();
            // java.sql.Statement stmt=con.createStatement();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MyCatalog</title>");  
            out.println("</head>");
            out.println("<body>");
          //  out.println("<form action='http://localhost:9386/MyShoppingCartServlet-war/Logout' method='get'>");
          //  out.println("<h1>Servlet AboutUs at " + request.getContextPath () + "</h1>");

           
//              if(session.getAttribute("status")=="logout")
//              {
//
//
//                  // HttpSession session=request.getSession();
//
//              }
//              else if(session.getAttribute("status")=="login")
//              {
             HttpSession session=request.getSession();
             String user=(String)session.getAttribute("username");
             out.println("<h1>The Session User is :-  "+user+"</h1>");

                String sqlQuery="select * from APP.PRODUCTSDETAILS";
               //  rs = stmt.executeQuery(sqlQuery);
                  rs = stmt.executeQuery(sqlQuery);
                  String output="";
                int count=rs.getRow();
                int i=count/3;

            while (rs.next())
            {

                 out.println("id: "+rs.getString("PRODUCT_ID"));
                 out.println("name : "+rs.getString("PRODUCT_NAME"));
                 output+="<table height='300px' width='200px' ><tr><th>Product</th></tr>";
                            output+="<tr>";
                             output+="<td> <img src='images\"> "+rs.getString("IMAGENAME")+".jpg' height='100' weight='100'/>";
                            output+="</td>";
                            output+="</tr>";
                            output+="<tr>";
                            output+="<td>"+rs.getString("PRODUCT_ID");
                            output+="</td>";
                            output+="</tr>";
                            output+="<tr>";
                            output+="<td> "+rs.getString("PRODUCT_NAME");
                            output+="</td>";
                            output+="</tr>";
                            output+="<tr>";
                            output+="<td>  "+rs.getString("PRODUCT_BRAND");
                            output+="</td>";
                            output+="</tr>";
                            output+="<tr>";
                             output+="<td> Price : "+rs.getFloat("PRODUCT_PRICE")+" Rs";
                            output+="</td>";
                            output+="</tr>";
                            output+="<tr>";
                            output+="<td> "+rs.getString("PRODUCT_DESC");
                            output+="</td>";
                            output+="</tr>";
                             output+="<tr>";
                            output+="<td>" +
                                    "<form action='http://localhost:9386/MyShoppingCartServlet-war/MyCart' method='get'>" +
                                    "<input type='hidden' name='prod_id' value='"+rs.getString("PRODUCT_ID")+"'>" +
                                    " <input type='submit' name='sub' value='buy now' >" +
                                    "</form>";
                            output+="</td>";
                            output+="</tr>";

                            output+="</table>";

            }
                  //String unm,pswd;
                 out.println(output);
               

                    
                    
                    out.println(output);
         // out.println("<input type='submit' value='Logoff' name='logoff'>");
          out.println("</form>");



          

           // out.println("<h1>Servlet MyCatalog at " + request.getContextPath () + "</h1>");
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
            Logger.getLogger(MyCatalog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MyCatalog.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MyCatalog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MyCatalog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
