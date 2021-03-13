<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- Cargo el error desde el servlet --%>
<jsp:useBean id='msgError' scope='request' class='java.lang.String' />  


<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
      <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Página de Error</title>

    <!-- BOOTSTRAP STYLES-->
    <link href="assets/css/bootstrap.css" rel="stylesheet" />
    <!-- FONTAWESOME STYLES-->
    <link href="assets/css/font-awesome.css" rel="stylesheet" />
     <!-- PAGE LEVEL STYLES-->
    <link href="assets/css/error.css" rel="stylesheet" />
    <!-- GOOGLE FONTS-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />

</head>
<body>
    <div class="container">
        
         <div class="row text-center">
               
                <div class="col-md-12 set-pad" >
                           
                            <strong class="error-txt">${msgError}</strong>
                           <p class="p-err">Opps. Lo que buscas no se encuentra</p>
                    <a href="index.jsp" class="btn btn-danger" ><i class="fa fa-mail-reply"></i>&nbsp;Volvé al inicio</a>
                        </div>
                
                
        </div>
    </div>
    <div class="c-err">
        <div class="container">
      


    </div>
    </div>
    <div class="container">
        <div class="row text-center">
            <div class="col-md-12">
                  <br />
    <br />
               <a class="btn btn-success" href="index.html">
                       Volver al inicio
                    </a>
                  <br />
    <br />
            </div>

        </div>

    </div>
  
  
    <hr />
    <div class="container">
        <div class="row text-center">
            <div class="col-md-12">
                
               
            </div>

        </div>

    </div>
    
</body>
</html>
