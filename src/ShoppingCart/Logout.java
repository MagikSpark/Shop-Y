package ShoppingCart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#getServletInfo()
	 */
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null; 
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
		        response.setContentType("text/html;charset=UTF-8");
		        PrintWriter out = response.getWriter();
		        try {
		           
		            out.println("<html>");
		            out.println("<head>");
		            out.println("<title>Servlet Logout</title>");  
		            out.println("</head>");
		            out.println("<body>");
		            String pageurl="http://localhost:8080/YeshaShoppingCart";
		            
		            HttpSession session=request.getSession();
		             session.setAttribute("username", null);
		             session.setAttribute("status", "logout");
		             session.removeAttribute("username");
		             
		             
		             response.sendRedirect(pageurl);
		            
		             
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
		        processRequest(request, response);
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
		        processRequest(request, response);
		    }


}
