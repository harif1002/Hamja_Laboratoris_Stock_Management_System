<%-- 
    Document   : index
    Created on : Dec 11, 2018, 3:39:44 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Shop Homepage</title>

        <!-- Bootstrap core CSS -->
        <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="resources/cssnew/shop-homepage.css" rel="stylesheet">
        <link rel="stylesheet" href="resources/css/animate.css">
        <link href="resources/img/logo.ico" rel="shortcut icon" />
        <script src="resources/js/angular.js"></script>
        <script>
            var productlist = ${productlist};
            angular.module('organocartpackage', []).controller('ProductController',
                    function ($scope) {
                        $scope.productangularobject = productlist;
                    });
        </script>

    </head>

    <body>

        <!-- Navigation -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
            <div class="container">
                <a class="navbar-brand" href="#">Hamja Labratories Ltd.</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="#">Home
                                <span class="sr-only">(current)</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">About</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Services</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Contact</a>
                        </li>
                        <c:if test="${sessionScope.UserLoggedIn ==null}">
                        <li class="nav-item">
                            <a class="nav-link" href="login">Login</a>
                        </li>
                        </c:if>
                        <c:if test="${sessionScope.UserLoggedIn !=null}">
                            <li class="nav-item">
                            <a class="nav-link" href="logout">Logout</a>
                        </li>
                            <li><a href="showcartpage" style="color: #9ccc65; font-size: 19px"> 
                                    <span class="glyphicon glyphicon-shopping-cart"></span> 
                                    <span class="badge">${sessionScope.grandquantity}</span>
                            </a></li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Page Content -->
        <div class="container">

            <div class="row">

                <div class="col-lg-3">

                    <h1 class="my-4">Hamja Labratories</h1>
                    <div class="list-group">
                        <a href="admin" class="list-group-item">Dashboard</a>
                        <a href="showingfeedbackpage" class="list-group-item">Feedback</a>
                        <a href="reportView" class="list-group-item">Report</a>
                    </div>

                </div>
                <!-- /.col-lg-3 -->

                <div class="col-lg-9">

                    <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
                        <ol class="carousel-indicators">
                            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                            <li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
                        </ol>
                        <div class="carousel-inner" role="listbox">
                            <div class="carousel-item active">
                                <img class="d-block img-fluid" src="resources/img/organicproducts4.jpg" alt="First slide">
                            </div>
                            <div class="carousel-item">
                                <img class="d-block img-fluid" src="resources/img/organicproducts4.jpg" alt="Second slide">
                            </div>
                            <div class="carousel-item">
                                <img class="d-block img-fluid" src="resources/img/organicproducts4.jpg" alt="Third slide">
                            </div>
                            <div class="carousel-item">
                                <img class="d-block img-fluid" src="resources/img/organicproducts4.jpg" alt="Third slide">
                            </div>
                        </div>
                        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>

                    <div class="row" ng-app="organocartpackage" ng-controller="ProductController">
                        
                            <div class="col-lg-4 col-md-6 mb-4 animated fadeInUp delay-05s panel" ng-repeat="p in productangularobject| limitTo: 6">
                                
                                <div class="card h-100">
                                    <a href="viewproduct?getId={{p.productid}}">
                                    <img class="card-img-top" src="resources/pimage/{{p.productid}}.jpg" height="300px" width="200px" alt="">
                                    <div class="card-body">
                                        <h4 class="card-title">
                                            {{p.productname}}
                                        </h4>
                                        <h5>Tk.{{p.productprice}}</h5>
                                        <p class="card-text"> {{p.productdescription}}</p>
                                    </div>
                                    </a>
                                    <div class="card-footer">
                                        <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
                                    </div>                                    
                                </div>
                                
                            </div>
                        <a href="buyproductpage" class="btn btn-primary" id="loginproductsbutton"> View More Products</a>
                    </div>
                    <!-- /.row -->

                </div>
                <!-- /.col-lg-9 -->

            </div>
            <!-- /.row -->

        </div>
        <!-- /.container -->

        <!-- Footer -->
        <footer class="py-5 bg-dark">
            <div class="container">
                <p class="m-0 text-center text-white">Copyright &copy; Your Website 2017</p>
            </div>
            <!-- /.container -->
        </footer>

        <!-- Bootstrap core JavaScript -->
        <script src="resources/jquery/jquery.min.js"></script>
        <script src="resources/bootstrap/js/bootstrap.bundle.min.js"></script>

    </body>

</html>
