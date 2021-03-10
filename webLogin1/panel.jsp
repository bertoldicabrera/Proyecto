
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${sessionScope['sessionNombre']==null}">
    <% response.sendRedirect("index.jsp");%>
</c:if> 

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
					
					<a class="navbar-brand" href="panel.jsp">Menu del Juego</a>
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
									<a class="active-menu" href="panel.jsp"><i class="fa fa-circle-o "></i>Menu</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</nav>
			<!-- /. NAV SIDE  -->
			<div id="page-wrapper">
				<div id="page-inner">
				<!-- /. ROW  fila 0-->
					<div class="row"> 
						<div class="col-md-12">
							<h1 class="page-head-line">Menu general</h1>
							<h1 class="page-subhead-line">Acá puedes crear una partida, unirte a una partida,  reanudar una partida guardada y ver tu progreso </h1>
						</div>
						
						
						<div class="col-md-12">
						<button type="button" class="btn btn-info">Crear partida</button>
						</div>
						
						 <label></label>
					</div>
					<!-- /. ROW  FIN fila 0-->
					
					<!-- /. ROW  fila 1-->
					<div class="row">
					
					<div class="col-md-6 col-sm-6 col-xs-12">
	               			<div class="panel panel-info">
		                        <div class="panel-heading">
		                           Unirse a una partida
		                        </div>
		                        
		                        <div class="panel-body">
		                            <form role="form">
		                                        <div class="form-group">
		                                            <label>Ingrese el id de la partida</label>
		                                            <input class="form-control" type="text">
		                                            <p class="help-block">Este número te lo brinda tu enemigo</p>
		                                        </div>
		                                        <button type="submit" class="btn btn-info">Unirme a una partida </button>
		                             </form>
		                        </div>
	                        </div>
                        </div>
					
					
					
					
					
					
						<div class="col-md-6">
							<div class="panel panel-default">
								<div class="panel-heading">
									Puntaje total acumulado
								</div>
								<div class="panel-body">
									<div class="progress">
										 <c:forEach items="${Jugador}" var="i">
									  <div class="progress-bar" role="progressbar" aria-valuenow="${i.getPuntajeAcumulado()}" aria-valuemin="0" aria-valuemax="10000" style="width: ${i.getPuntajeAcumulado()/10}%;">
										<span class="sr-only">${i.getPuntajeAcumulado()/10}% Complete</span>
									  </div>
									   </c:forEach>
									</div>
								</div>
							</div>
						</div>
				</div>
					<!-- /. ROW FIN fila 1  -->
		<!-- /. TABLE ROW  -->		
		<div class="row">
         <div class="col-md-12">
		      <div class="panel panel-primary">
		     		<!-- Default panel contents -->
		      		<div class="panel-heading"><td><a href="#" class="btn btn-info">Listar partidas Guardadas</a></td></div>
		
		     					 <!-- Table -->
						      <table class="table">
						        <thead>
						          <tr>
						            <th>Id</th>
						            <th>Nombre</th>
						            <th>Fecha</th>
						            <th>Acción </th>
						            
						          </tr>
						        </thead>
						        <tbody>
							        <c:forEach items="${listadePartidas}" var="i">
							          <tr class="list-group-item-success">
							            <td>${i.getPartidaId()}</td>
							            <td>${i.getPartidaNombre()}</td>
							            <td>${i.getPartidaFechaCreada()}</td>
							            <td>
								            <c:if test="${i.isPartidaTermino()== true}" var="condition">
										    <a href="#" class="btn btn-success" disabled="true">Reanudar</a>
										</c:if>
										<c:if test="${!condition}">
										   <a href="#" class="btn btn-success" >Reanudar</a>
										</c:if>
							            </td>
							          </tr>
							          </c:forEach>
						        </tbody>
						      </table>
		    </div>
        </div>
     </div>		
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


