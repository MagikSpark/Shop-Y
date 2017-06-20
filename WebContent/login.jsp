<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="css/style.css" />
</head>
<body>
	<div class="container">
		<section id="content">
		
			<form action="../YeshaShoppingCart/LoginCheck" method="post">
				<h1>Login Form</h1>
				<div>
					<input type="text" placeholder="Username" name="username" required="" id="username" />
					</div>
				<div>
					<input type="password" placeholder="Password" name="password" required="" id="password" />
				</div>
				<div style="margin-left: 10%">
						<input type="submit" value="Log in" />
						<input type="reset" value="Reset"/>
				</div>
			</form>
		</section>
	</div>
</body>
</html>