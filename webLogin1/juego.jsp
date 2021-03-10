
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 

<c:if test="${sessionScope['sessionNombre']==null}">
    <% response.sendRedirect("index.jsp");%>
</c:if> 
 -->

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<style>

.bt3{padding:6px;border-radius:10px;}
</style>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>Panel</title>
		<!-- BOOTSTRAP STYLES-->
		<link href="assets/css/bootstrap.css" rel="stylesheet" />
		<!-- FONTAWESOME STYLES-->
		<link href="assets/css/font-awesome.css" rel="stylesheet" />
		<!--CUSTOM BASIC STYLES-->
		<link href="assets/css/basic.css" rel="stylesheet" />
		<!--CUSTOM MAIN STYLES-->
		<link href="assets/css/custom.css" rel="stylesheet" />
		<!-- GOOGLE FONTS-->
		<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
	</head>
	<body>
	
	
	
	
	
		<div id="wrapper">
			<nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
				<div class="navbar-header">
					
					<a class="navbar-brand" href="panel.jsp">Juego</a>
				</div>

				<div class="header-right">
					<a title="Salir" >
						<form action="Logout" method="post"  >
						<input  class="btn btn-danger"  type="submit"  value="Salir" onClick="location.reload();">
						</form>
					</a>
				</div>
			</nav>
		
			<!-- /. NAV TOP  -->
			<nav class="navbar-default navbar-side" role="navigation">
				<div class="sidebar-collapse">
					<ul class="nav" id="main-menu">
						<li>
							<div class="user-img-div">
								<img src="images/jugador1.jpg" class="img-thumbnail" />

								<div class="inner-text">
									<br>
										 <c:forEach items="${Jugador}" var="i">
							        Usuario:  ${i.getJugadorUserName()} 
							          </c:forEach>
										
									<br/>
								</div>
							</div>
						</li>
						<li>
							
							 <ul class="nav nav-second-level collapse in">
								 <li>
									<a class="active-menu" href="panel.jsp"><i class="fa fa-circle-o "></i>Juego</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</nav>
			<!-- /. NAV SIDE  -->
			<div id="page-wrapper">
				<div id="page-inner">
				
						<!-- /. ROW  FIN fila 0-->
						<div class="row">
							<div class="col-md-6 col-sm-6 col-xs-12">
								<div class="col-md-12">
								<p>
									 <button type="button" class="btn btn-sm btn-default">Default</button>
							        <button type="button" class="btn btn-sm btn-primary">Primary</button>
							        <button type="button" class="btn btn-sm btn-success">Success</button>
							        <button type="button" class="btn btn-sm btn-info">Info</button>
							        <button type="button" class="btn btn-sm btn-warning">Pausar</button>
							        <button type="button" class="btn btn-sm btn-danger">Abandonar</button>
								</p>
								</div>
							</div>
						</div>
						<!-- Fin de  fila 0-->
					<div class="row">
							<div class="col-md-6 col-sm-6 col-xs-12">
								<img src="images/aviones1.jpg" alt="War Thunder" title="War Thunder">
							</div>
						</div>
					<!-- Poner el juego/. ROW  fila 1-->
		
		
		
		
					<!-- /. ROW FIN fila 1  -->
					
					
						
		
				<!-- /. PAGE INNER  -->
				</div>
			</div>
			<!-- /. PAGE WRAPPER  -->
		</div>
		<!-- /. WRAPPER  -->
		<div id="footer-sec">
			&copy; 2014 YourCompany | Design By : <a href="http://www.binarytheme.com/" target="_blank">BinaryTheme.com</a>
		</div>
		<!-- /. FOOTER  -->
		<!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
		<!-- JQUERY SCRIPTS -->
		<script src="assets/js/jquery-1.10.2.js"></script>
		<!-- BOOTSTRAP SCRIPTS -->
		<script src="assets/js/bootstrap.js"></script>
		<!-- METISMENU SCRIPTS -->
		<script src="assets/js/jquery.metisMenu.js"></script>
		<!-- CUSTOM SCRIPTS -->
		<script src="assets/js/custom.js"></script>
	</body>
</html>


