package ShoppingCart;

import java.io.IOException;
import java.sql.*;
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
 * Servlet implementation class initDB
 */
@WebServlet(description = "For creating database for the first time.", urlPatterns = { "/initDB" })
public class initDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String url="jdbc:derby://localhost:1527/sample";
    public Connection con;
    public Statement stmt;
    public String sqlQuery;
	
    /**
     * @throws SQLException 
     * @see HttpServlet#HttpServlet()
     */
    public initDB() throws SQLException {
        super();
        // TODO Auto-generated constructor stub
        con=DriverManager.getConnection(url,"user","123");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException, ClassNotFoundException, SQLException {
    	        response.setContentType("text/html;charset=UTF-8");

    	        PrintWriter out = response.getWriter();
    	        try {
    	          
    	            out.println("<html>");
    	            out.println("<head>");
    	            out.println("<title>Initializing</title>");  
    	            out.println("</head>");
    	            out.println("<body>");
    	          
    	            //Getting connection and installing JDBC Driver
    	            out.println("<H2>Initializing for the first time</H2>");
    	            out.print("installing jdbc driver....");
    	            Class.forName("org.apache.derby.jdbc.ClientDriver");
    	            out.println("<font color=\"#00CC00\">done</font></br>");
    	            
    	            //Creating statement
    	            out.print("connecting derby database server....");
      	           	stmt=con.createStatement();
      	           	out.println("<font color=\"#00CC00\">done</font></br>");

      	           	//Dropping all existing tables if any
      	           	out.print("dropping old USERS database....");
	      	        try{
	  	            	sqlQuery = "DROP TABLE APP.USERS";
	      	            stmt.executeUpdate(sqlQuery);  
	      	            out.println("<font color=\"#00CC00\">done</font></br>");
	  	            }catch(Exception e){out.println("</br>"+e+"</br>");}
	      	        
	      	      out.print("dropping old PRODUCTDETAILS database....");
	      	        try{
	  	            	sqlQuery = "DROP TABLE APP.PRODUCTSDETAILS";
	      	            stmt.executeUpdate(sqlQuery);  
	      	            out.println("<font color=\"#00CC00\">done</font></br>");
	  	            }catch(Exception e){out.println("</br>"+e+"</br>");}
	      	        
	      	      out.print("dropping old CART database....");
	      	        try{
	  	            	sqlQuery = "DROP TABLE APP.CART";
	      	            stmt.executeUpdate(sqlQuery);  
	      	            out.println("<font color=\"#00CC00\">done</font></br>");
	  	            }catch(Exception e){out.println("</br>"+e+"</br>");}
	  	
	      	        
	      	        //Creating new tables
    	            out.print("creating new USERS database....");
    	            sqlQuery="Create table APP.USERS("
    	           		+ "USERNAME VARCHAR(25) NOT NULL,"
    	           		+ "PASSWORD VARCHAR(25) NOT NULL,"
    	           		+ "ROLE INTEGER NOT NULL,"
						+ "PRIMARY KEY (USERNAME)"
						+ ")";
    	            try{
    	            	int rs = stmt.executeUpdate(sqlQuery);
    	            	out.println("<font color=\"#00CC00\">done</font></br>");
    	            }catch(Exception e){
    	            	out.println("<font color=\"#FF0000\">failed</font></br>");
    	            }
    	            
    	            out.print("creating new PRODUCTDETAILS database....");    	          	  	            
    	            sqlQuery="Create table APP.PRODUCTSDETAILS("
    	           		+ "PRODUCT_ID VARCHAR(50) NOT NULL,"
    	           		+ "PRODUCT_NAME VARCHAR(50) NOT NULL,"
    	           		+ "PRODUCT_BRAND VARCHAR(30),"
    	           		+ "PRODUCT_PRICE INTEGER,"
    	           		+ "PRODUCT_DESC VARCHAR(100),"
    	           		+ "IMAGENAME VARCHAR(100),"
						+ "PRIMARY KEY (PRODUCT_ID)"
						+ ")";
    	            try{
    	            	int rs = stmt.executeUpdate(sqlQuery);
    	            	out.println("<font color=\"#00CC00\">done</font></br>");
    	            }catch(Exception e){
	    	            	out.println("<font color=\"#FF0000\">failed</font></br>");
    	            }
    	            
    	            out.print("creating new CART database....");    	          	  	            
    	            sqlQuery="Create table APP.CART("
    	           		+ "TRANSACTION_ID INTEGER NOT NULL,"
    	           		+ "PRODUCTID VARCHAR(50) NOT NULL,"
    	           		+ "USERS VARCHAR(50),"
    	           		+ "QUANTITY INTEGER,"
						+ "PRIMARY KEY (TRANSACTION_ID)"
						+ ")";
    	            try{
    	            	int rs = stmt.executeUpdate(sqlQuery);
    	            	out.println("<font color=\"#00CC00\">done</font></br>");
    	            }catch(Exception e){
	    	            	out.println("<font color=\"#FF0000\">failed</font></br>");
    	            }
    	            
    	            
    	            //Inserting default rows//
    	            out.print("inserting rows in USERS database....");
    	            try{
    	            	//Role 1: Admin  2: User
	    	            sqlQuery="INSERT INTO APP.USERS (ROLE, USERNAME, PASSWORD) VALUES (1, 'admin', 'admin')";
	    	            stmt.executeUpdate(sqlQuery);
	    	            sqlQuery="INSERT INTO APP.USERS (ROLE, USERNAME, PASSWORD) VALUES (2, 'root', 'toor')";	
	    	            stmt.executeUpdate(sqlQuery);
	    	            out.println("<font color=\"#00CC00\">done</font></br>");
    	            }catch(Exception e){
    	            	out.println("<font color=\"#FF0000\">failed</font></br>");
    	            }
    	            out.print("inserting rows in PRODUCTDETAILS database....");
    	            try{
    	            	addProduct("P001", "Cotton Jeans","Levis", 26, "Light weight", "w3images\\jeans1.jpg");	    	
    	            	addProduct("P002", "Fabric Jeans","Loot", 26, "Light weight", "w3images\\jeans2.jpg");	    	           
    	            	addProduct("P003", "Fashion Jeans","Levis", 26, "Light weight", "w3images\\jeans3.jpg");	    	           
    	            	addProduct("P004", "Cotton Jeans","Levis", 26, "Light weight", "w3images\\jeans4.jpg");	    	           
	    	            
	    	            out.println("<font color=\"#00CC00\">done</font></br>");
    	            }catch(Exception e){
    	            	out.println("<font color=\"#FF0000\">failed</font></br>");
    	            }

    	            
    	            stmt.close();
      	           	out.println("</body>");
    	            out.println("</html>");
    	           
    	        } finally { 
    	            out.close();
	        } 
	    } 

    public void addProduct(String Pid, String Pname,String Pbrand, int Pprice, String Pdesc, String Pimage) throws SQLException{
    	String sqlQuery="INSERT INTO APP.PRODUCTSDETAILS (PRODUCT_ID, PRODUCT_NAME, PRODUCT_BRAND, PRODUCT_PRICE, PRODUCT_DESC, IMAGENAME) "
        		+ "VALUES "
        		+ "('"+ Pid+"', '"+Pname+"', '"+Pbrand+"', "+Pprice+", '"+Pdesc+"', '"+Pimage+"')";
    	stmt=con.createStatement(); 	
    	stmt.executeUpdate(sqlQuery);
	}
    
    public void addCart(String Tid, String Pid,String Tuser, int Tqty) throws SQLException{
    	String sqlQuery="INSERT INTO APP.CART (TRANSACTION_ID, PRODUCTID, USERS, QUANTITY) "
        		+ "VALUES "
        		+ "('"+ Tid+"', '"+Pid+"', '"+Tuser+"', "+Tqty+"')";
    	stmt=con.createStatement(); 	
    	stmt.executeUpdate(sqlQuery);
	}
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method 

		try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginCheck.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginCheck.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginCheck.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginCheck.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

}
