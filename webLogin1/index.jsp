<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Workland Factory</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/fontawesome.min.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container">
                <a class="navbar-brand" href="index.html"><img src="imgs/logo2.png" alt="#" /></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#nav-links" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse text-capitalize" id="nav-links">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="#home">Home <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#about">About</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#service">More</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#test">Testimonial</a>
                        </li>
                    </ul>
                    
                     <li class="nav-item">
                           <a class="nav-link" href="register.jsp">Register</a>
                     </li>
                     <li class="nav-item">
                          <a class="nav-link" href="login.jsp">Log in</a>
                     </li>
                     
                     <li class="nav-item search">
                            <a class="nav-link search-link" href=""><i class="fas fa-search"></i></a>
                            <form>
                                <input type="search">
                            </form>
                    </li>
                </div>
            </div>
        </nav>
        
        <header id="home">
            <div class="content">
                <div class="d-flex align-items-center flex-column justify-content-center">
                    <h1>Bienvenidos a la<br> Aviación en la primera guerra mundial</h1>
                    <p>La victoria está reservada para aquellos que están dispuestos a pagar su precio.</p>
                    <a href="#">
                        <span>read more</span>
                        <i class="fas fa-long-arrow-alt-right fa-2x"></i>
                    </a>
                </div>
            </div>
          <div id="slideToNext" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
              <div class="carousel-item active">
                <img src="imgs/header.jpg" class="d-block w-100">
              </div>
              <div class="carousel-item">
                <img src="imgs/header.jpg" class="d-block w-100">
              </div>
              <div class="carousel-item">
                <img src="imgs/header.jpg" class="d-block w-100">
              </div>
            </div>
            <a class="carousel-control-prev" href="#slideToNext" role="button" data-slide="prev">
              <i class="fas fa-chevron-left"></i>
              <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#slideToNext" role="button" data-slide="next">
              <i class="fas fa-chevron-right"></i>
              <span class="sr-only">Next</span>
            </a>
          </div>
     </header>
        
        <div class="about" id="about">
            <div class="row">
                <div class="col-lg-6 col-12">
                    <div class="img"><img src="imgs/pic7.jpg"></div>
                </div>
                <div class="col-lg-6 col-12 text-right">
                    <div class="about-child">
                        <h2 class="text-uppercase"><span>abo</span>ut us</h2>
                        <p>El mejor juego de guerra en el aire</p>
                        <p>Mucho tiro, bombas y aviones!! Destruye a tu enemigo, demuestra que sos el mejor!!! </p>
                        <a href="#"><i class="fas fa-long-arrow-alt-left fa-2x"></i><span>Read more</span></a>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="services" id="service">
            <div class="container"><h2 class="text-capitalize"><span>Our</span> services</h2></div>
            <div class="services-child">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-4 col-md-6 col-12">
                            <div>
                                <img src="imgs/pic4.jpg">
                                <a href="#"><h4>Aviones</h4></a>
                                <p> Puedes pilotear y destruir las bases enemigas</p>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-6 col-12">
                            <div>
                                <img src="imgs/pic1.jpg">
                                <a href="#"><h4>Monta tu avión</h4></a>
                                <p>Puedes elegir el color y eso define tu equipo</p>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-6 col-12">
                            <div>
                                <img src="imgs/pic2.jpg">
                                <a href="#"><h4>Derriba el enemigo</h4></a>
                                <p> Destruye los aviones y bases enemigas para ser el campeon</p>
                            </div>
                        </div>
                    </div>
                    <a href="#"><i class="fas fa-long-arrow-alt-left fa-2x"></i><span>Read more</span></a>
                </div>
            </div>
        </div>
        
              
       
        <div class="testimonial" id="test">
            <div class="container"><h2 class="text-capitalize text-center"><span>test</span>imonial</h2></div>
            <div class="test-child">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-2 col-md-1"></div>
                        <div class="col-lg-2 col-md-3 col-4"><img src="imgs/pic8.png"></div>
                        <div class="col-lg-6 col-8">
                            <h4 class="text-ippercase">Lorena</h4>
                            <p>Lorena: Es como estar en la guerra, no paro de jugar!!</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-2 col-md-3 col-4"><img src="imgs/pic9.png"></div>
                        <div class="col-lg-6 col-8">
                            <h4 class="text-ippercase">jack</h4>
                            <p>Jack: Más divertido imposible!!</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <footer>
            <div class="container">
                <div class="copyright">
                    <p>© 2018 All Rights Reserved. Free Website Templates</p>
                </div>
            </div>
        </footer>    
            
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script>
            $(function () {
                
                'use strict';
                
                var winH = $(window).height();
                
                $('header, .slide').height(winH);
                
                $('.navbar a.search-link').on('click', function () {
                    $(this).siblings('form').fadeToggle();
                });
                
                $('.navbar ul.navbar-nav li a, footer ul.main-list li a').on('click', function (e) {
                    
                    var getAttr = $(this).attr('href');
                    
                    e.preventDefault();
                    $('html').animate({scrollTop: $(getAttr).offset().top}, 1000);
                });
            });
        </script>
    </body>
</html>



