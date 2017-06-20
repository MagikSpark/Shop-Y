<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     
<!DOCTYPE HTML>
<html>
<title>SHOP-Y!</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
.w3-sidebar a {font-family: "Roboto", sans-serif}
body,h1,h2,h3,h4,h5,h6,.w3-wide {font-family: "Montserrat", sans-serif;}
table, th,td {border:1px solid black;
    border-collapse: collapse;}
</style>
<body class="w3-content" style="max-width:1200px">

<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-bar-block w3-white w3-collapse w3-top" style="z-index:3;width:250px" id="mySidebar">
  <div class="w3-container w3-display-container w3-padding-16">
    <i onclick="w3_close()" class="fa fa-remove w3-hide-large w3-button w3-display-topright"></i>
    <h3 class="w3-wide"><b>SHOP-Y!</b></h3>
  </div>
  <div class="w3-padding-64 w3-large w3-text-grey" style="font-weight:bold">
    <a href="#" class="w3-bar-item w3-button">Shirts</a>
    <a href="#" class="w3-bar-item w3-button">Dresses</a>
    <a onclick="myAccFunc()" href="javascript:void(0)" class="w3-button w3-block w3-white w3-left-align" id="myBtn">
      Jeans <i class="fa fa-caret-down"></i>
    </a>
    <div id="demoAcc" class="w3-bar-block w3-hide w3-padding-large w3-medium">
      <a href="#" class="w3-bar-item w3-button w3-light-grey"><i class="fa fa-caret-right w3-margin-right"></i>Skinny</a>
      <a href="#" class="w3-bar-item w3-button">Relaxed</a>
    </div>
    <a href="#" class="w3-bar-item w3-button">Jackets</a>
  </div>
  <a href="#footer" class="w3-bar-item w3-button w3-padding"></a> 
</nav>

<!-- Top menu on small screens -->
<header class="w3-bar w3-top w3-hide-large w3-black w3-xlarge">
  <div class="w3-bar-item w3-padding-24 w3-wide">SHOP-Y!</div>
  <a href="javascript:void(0)" class="w3-bar-item w3-button w3-padding-24 w3-right" onclick="w3_open()"><i class="fa fa-bars"></i></a>
</header>

<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:250px">

  <!-- Push down content on small screens -->
  <div class="w3-hide-large" style="margin-top:83px"></div>
  
  <!-- Top header -->
  <header class="w3-container w3-xlarge">
    <p class="w3-left">Jeans</p>
    <p class="w3-right">
	  Welcome  
	  	<% 
		  	session=request.getSession();
	        String user=(String)session.getAttribute("username");
	        if (user!=null){
	        	out.println("<a href=\"Logout\">"+user.toUpperCase()+"!  <i class=\"fa fa-male w3-margin-right\"></i></a>");
	        }else
	        	out.println("<a href=\"login.jsp\">Guest!  <i class=\"fa fa-male w3-margin-right\"></i></a>");
		%> 
	  
      <i style="cursor:pointer" onclick="document.getElementById('cart').style.display='block'" class="fa fa-shopping-cart w3-margin-right" ></i>
    </p>
  </header>

  <!-- Image header -->
  <div class="w3-display-container w3-container">
    <img src="w3images/jeans.jpg" alt="Jeans" style="width:100%">
    <div class="w3-display-topleft w3-text-white" style="padding:24px 48px">
      <h1 class="w3-jumbo w3-hide-small">New arrivals</h1>
      <h1 class="w3-hide-large w3-hide-medium">New arrivals</h1>
      <h1 class="w3-hide-small">COLLECTION 2016</h1>
      <p><a href="#jeans" class="w3-button w3-black w3-padding-large w3-large">SHOP NOW</a></p>
    </div>
  </div>

  <div class="w3-container w3-text-grey" id="jeans">
    <p><%
    Class.forName("org.apache.derby.jdbc.ClientDriver");
    String url="jdbc:derby://localhost:1527/sample";
    Connection con=DriverManager.getConnection(url,"user","123");
    Statement stmt=con.createStatement();
    String sqlQuery="select * from APP.PRODUCTSDETAILS";
    ResultSet rs = stmt.executeQuery(sqlQuery);
    int c = 0;
    while(rs.next())
    {
    	c++;
    }
    out.print(c);
    %>
     items</p>
  </div>

  <!-- Product grid -->
	<div class="w3-row w3-grayscale">
	<%
	String pid;
	String cur;
	 rs = stmt.executeQuery(sqlQuery);
while(rs.next())
{
	
	%>
	<div class="w3-col l3 s6">
			<div class="w3-container">
				<div class="w3-display-container">
					<img src="<% out.print("http://localhost:8080/YeshaShoppingCart/"+rs.getString("IMAGENAME")); %>" style="width:100%">
					<div class="w3-display-middle w3-display-hover w3-white">
					  <button class="w3-button" onclick="document.getElementById('cart').style.display='block'">
							Buy now <i class="fa fa-shopping-cart"></i>
						</button> 
						
					</div>
				</div>
				<p><% out.print(rs.getString("PRODUCT_NAME")); %><br><b>$<% out.print(rs.getInt("PRODUCT_PRICE")); %></b></p>
			</div>
		</div>
	
	
	
	<%
	
}
//out.print();

%>
		

	</div>
  
  <!-- Footer -->
<div class="w3-black w3-center w3-padding-24">Powered by Yesha</div>

  <!-- End page content -->
</div>

	<!--Cart Modal-->
<div id="cart" class="w3-modal">
    <div class="w3-modal-content">
		<div class="w3-container">
        <span onclick="document.getElementById('cart').style.display='none'" class="w3-button w3-display-topright">&times;</span>
        <p>Shopping Cart</p>
		<!--- Yet to be done -->
		<%
		
        sqlQuery="select * from APP.CART where APP.CART.USERS='"+user+"'";
       // String sqlQuery="select * from APP.CART where APP.CART.USERS='admin'";
        // String sqlQuery="select * from APP.CART";
       rs = stmt.executeQuery(sqlQuery);
       float total=0,grandTotal=0,price=0;

        String output="<table width='100%' style='text-align:center;'><tr><th>ProductName</th><th>Price</th><th>Quantity</th><th>Total</th></tr>";
       while (rs.next())
       {
           output+="<tr>";
           // out.println("<br><br><br>id: "+rs.getString("PRODUCTID"));
             String sqlQuery1 = "select * from APP.PRODUCTSDETAILS where APP.PRODUCTSDETAILS.PRODUCT_ID='"+rs.getString("PRODUCTID")+"'";

             // out.println("id: "+rs.getInt("QUANTITY"));
              java.sql.Statement stmt1 = con.createStatement();
              ResultSet rs1 = stmt1.executeQuery(sqlQuery1);
              int qut=rs.getInt("QUANTITY");

                   while (rs1.next())
                   {
                       output+="<td>"+rs1.getString("PRODUCT_NAME")+"</td>";
                       output+="<td>"+rs1.getInt("PRODUCT_PRICE")+"</td>";
       //                out.println("<br>Name: " + rs1.getString("PRODUCT_NAME"));
       //                out.println("<br>Price: " + rs1.getString("PRODUCT_PRICE"));
                       total=rs1.getInt("PRODUCT_PRICE")*rs.getInt("QUANTITY");
                       output+="<td>"+total+"</td>";
                       grandTotal=grandTotal+total;


                   }

               output+="<td>"+rs.getInt("QUANTITY")+"</td>";
              output+="</tr>";

       }
        output+="</table><h1>Grand Total is : "+grandTotal+"</h1>";
        out.println(output);
                
		%>
		
		
      </div>
    </div>
  </div>
</div>

<script>
// Accordion 
function myAccFunc() {
    var x = document.getElementById("demoAcc");
    if (x.className.indexOf("w3-show") == -1) {
        x.className += " w3-show";
    } else {
        x.className = x.className.replace(" w3-show", "");
    }
}

// Click on the "Jeans" link on page load to open the accordion for demo purposes
document.getElementById("myBtn").click();


// Script to open and close sidebar
function w3_open() {
    document.getElementById("mySidebar").style.display = "block";
    document.getElementById("myOverlay").style.display = "block";
}
 
function w3_close() {
    document.getElementById("mySidebar").style.display = "none";
    document.getElementById("myOverlay").style.display = "none";
}
</script>

</body>
</html>


