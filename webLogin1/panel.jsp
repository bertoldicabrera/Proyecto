
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- si no esta logueado --%>
<c:if test="${sessionScope['sessionEmail']==null}">
    <% response.sendRedirect("index.jsp");%>
</c:if> 
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
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
				
					
					
					<a class="navbar-brand" href="panel.jsp">workland</a>
				</div>

				<div class="header-right">

					<a href="message-task.html" class="btn btn-info" title="New Message"><b>30 </b><i class="fa fa-envelope-o fa-2x"></i></a>
					<a href="message-task.html" class="btn btn-primary" title="New Task"><b>40 </b><i class="fa fa-bars fa-2x"></i></a>
					<a href="#" class="btn btn-primary" title="New Task"><b>No </b><i class="fa fa-exclamation-circle fa-2x"></i></a>
					<a title="Logout" >
						<form action="Logout" method="post"  >
								
						<input  class="btn btn-danger"  type="submit"  value="logout" onClick="location.reload();">
								
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
								<img src="assets/img/user.png" class="img-thumbnail" />

								<div class="inner-text">
									<br>
										User ${sessionScope['sessionNombre']}
									<br/>
									<br>
										Email ${sessionScope['sessionEmail']}
									<br/>
									<small>Last Login : 2 Weeks Ago </small>
								</div>
							</div>

						</li>


					   <li>
							<a href="index.html"><i class="fa fa-dashboard "></i>Dashboard</a>
						</li>
						<li>
							<a href="#"  class="active-menu-top"><i class="fa fa-desktop "></i>UI Elements <span class="fa arrow"></span></a>
							 <ul class="nav nav-second-level collapse in">
							 	<li>
								<a href="tables.jsp"><i class="fa fa-flash "></i>Data Tables</a>
								</li>
								
								 <li>
									<a class="active-menu" href="panel.jsp"><i class="fa fa-circle-o "></i>Progressbars</a>
								</li>
								
								
							   
							</ul>
						</li>
						 <li>
							<a href="#"><i class="fa fa-yelp "></i>Extra Pages <span class="fa arrow"></span></a>
							 <ul class="nav nav-second-level">
								<li>
									<a href="invoice.html"><i class="fa fa-coffee"></i>Invoice</a>
								</li>
								<li>
									<a href="pricing.html"><i class="fa fa-flash "></i>Pricing</a>
								</li>
								 <li>
									<a href="component.html"><i class="fa fa-key "></i>Components</a>
								</li>
								 <li>
									<a href="social.html"><i class="fa fa-send "></i>Social</a>
								</li>
								
								 <li>
									<a href="message-task.html"><i class="fa fa-recycle "></i>Messages & Tasks</a>
								</li>
								
							   
							</ul>
						</li>
						
						<li>
							<a href="tables.jsp"><i class="fa fa-flash "></i>Data Tables*** </a>
							
						</li>
						<li>
							<a href="#"><i class="fa fa-bicycle "></i>Forms <span class="fa arrow"></span></a>
							 <ul class="nav nav-second-level">
							   
								 <li>
									<a href="form.html"><i class="fa fa-desktop "></i>Basic </a>
								</li>
								 <li>
									<a href="form-advance.html"><i class="fa fa-code "></i>Advance</a>
								</li>
								 
							   
							</ul>
						</li>
						 <li>
							<a href="gallery.html"><i class="fa fa-anchor "></i>Gallery</a>
						</li>
						 <li>
							<a href="error.html"><i class="fa fa-bug "></i>Error Page</a>
						</li>
						<li>
							<a href="login.html"><i class="fa fa-sign-in "></i>Login Page</a>
						</li>
						<li>
							<a href="#"><i class="fa fa-sitemap "></i>Multilevel Link <span class="fa arrow"></span></a>
							 <ul class="nav nav-second-level">
								<li>
									<a href="#"><i class="fa fa-bicycle "></i>Second Level Link</a>
								</li>
								 <li>
									<a href="#"><i class="fa fa-flask "></i>Second Level Link</a>
								</li>
								<li>
									<a href="#">Second Level Link<span class="fa arrow"></span></a>
									<ul class="nav nav-third-level">
										<li>
											<a href="#"><i class="fa fa-plus "></i>Third Level Link</a>
										</li>
										<li>
											<a href="#"><i class="fa fa-comments-o "></i>Third Level Link</a>
										</li>

									</ul>

								</li>
							</ul>
						</li>
					   
						<li>
						
							<a href="blank.html"><i class="fa fa-square-o "></i>Blank Page</a>
						</li>
						<li>
							<form action="Logout" method="post" class="fa fa-square-o ">
							<p><input type="submit" value="Salir" onClick="location.reload();"</p>
							</form>
							
						</li>
					</ul>
						
				</div>

			</nav>
			<!-- /. NAV SIDE  -->
			<div id="page-wrapper">
				<div id="page-inner">
					<div class="row">
						<div class="col-md-12">
							<h1 class="page-head-line">Progress Bars</h1>
							<h1 class="page-subhead-line">This is dummy text , you can replace it with your original text. </h1>

						</div>
					</div>
					<!-- /. ROW  -->
					<div class="row">
						<div class="col-md-6">
						   <div class="panel panel-default">
							<div class="panel-heading">
								Basic Example
							</div>
							<div class="panel-body">
	<div class="progress">
	  <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
		<span class="sr-only">60% Complete</span>
	  </div>
	</div>
							</div>
						   
						</div>
						</div>
						<div class="col-md-6">
						   <div class="panel panel-default">
							<div class="panel-heading">
								With label Example
							</div>
							<div class="panel-body">
								<div class="progress">
	  <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
		60%
	  </div>
	</div>
							</div>
						   
						</div>
						</div>
					</div>
					 <!-- /. ROW  -->
					  <div class="row">
						<div class="col-md-6">
						   <div class="panel panel-default">
							<div class="panel-heading">
								Low Percentages Example
							</div>
							<div class="panel-body">
	<div class="progress">
	  <div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100">
		0%
	  </div>
	</div>
	<div class="progress">
	  <div class="progress-bar" role="progressbar" aria-valuenow="2" aria-valuemin="0" aria-valuemax="100" style="width: 2%;">
		2%
	  </div>
	</div>
							</div>
						   
						</div>
							<div class="panel panel-default">
							<div class="panel-heading">
								Stacked Example
							</div>
							<div class="panel-body">
	<div class="progress">
	  <div class="progress-bar progress-bar-success" style="width: 35%">
		<span class="sr-only">35% Complete (success)</span>
	  </div>
	  <div class="progress-bar progress-bar-warning progress-bar-striped" style="width: 20%">
		<span class="sr-only">20% Complete (warning)</span>
	  </div>
	  <div class="progress-bar progress-bar-danger" style="width: 10%">
		<span class="sr-only">10% Complete (danger)</span>
	  </div>
	</div>
							</div>
						   
						</div>
						</div>
						<div class="col-md-6">
						   <div class="panel panel-default">
							<div class="panel-heading">
							   Contextual Alternatives
							</div>
							<div class="panel-body">
								<div class="progress">
				  <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
					<span class="sr-only">40% Complete (success)</span>
				  </div>
				</div>
				<div class="progress">
				  <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">
					<span class="sr-only">20% Complete</span>
				  </div>
				</div>
				<div class="progress">
				  <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
					<span class="sr-only">60% Complete (warning)</span>
				  </div>
				</div>
				<div class="progress">
				  <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
					<span class="sr-only">80% Complete</span>
				  </div>
				</div>
							</div>
						   
						</div>
						</div>
					</div>
					 <!-- /. ROW  -->
			<div class="row">
					<div class="col-md-6">
						  <div class="panel panel-default">
							<div class="panel-heading">
								Striped Example
							</div>
							<div class="panel-body">
								<div class="progress">
								  <div class="progress-bar progress-bar-success progress-bar-striped" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
									<span class="sr-only">40% Complete (success)</span>
								  </div>
								</div>
								<div class="progress">
								  <div class="progress-bar progress-bar-info progress-bar-striped" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">
									<span class="sr-only">20% Complete</span>
								  </div>
								</div>
								<div class="progress">
								  <div class="progress-bar progress-bar-warning progress-bar-striped" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
									<span class="sr-only">60% Complete (warning)</span>
								  </div>
								</div>
								<div class="progress">
								  <div class="progress-bar progress-bar-danger progress-bar-striped" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
									<span class="sr-only">80% Complete (danger)</span>
								  </div>
								</div>
							</div>
						   
						</div>
					</div>
				<div class="col-md-6">
					<div class="panel panel-default">
							<div class="panel-heading">
								Animated Example
							</div>
						<div class="panel-body">
							<div class="progress">
							  <div class="progress-bar progress-bar-info progress-bar-striped active"  role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 45%">
								<span class="sr-only">45% Complete</span>
							  </div>
							</div>
							<div class="progress">
							  <div class="progress-bar progress-bar-warning progress-bar-striped active"  role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 75%">
								<span class="sr-only">75% Complete</span>
							  </div>
							</div>
							<div class="progress">
							  <div class="progress-bar progress-bar-success progress-bar-striped active"  role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 45%">
								<span class="sr-only">45% Complete</span>
							  </div>
							</div>
							<div class="progress">
							  <div class="progress-bar progress-bar-danger progress-bar-striped active"  role="progressbar" aria-valuenow="55" aria-valuemin="0" aria-valuemax="100" style="width: 55%">
								<span class="sr-only">55% Complete</span>
							  </div>
							</div>
						</div>
						   
					</div>
				</div>
			</div>
					 <!-- /. ROW  -->
					
				</div>
				<!-- /. PAGE INNER  -->
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


