<%-- 
    Document   : viewproduct
    Created on : Dec 3, 2018, 4:17:24 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/default.css">
        <link rel="stylesheet" href="resources/css/bootstrap-theme.css">
        <link rel="stylesheet" href="resources/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <link rel="stylesheet" href="resources/css/animate.css">

        <script src="resources/js/bootstrap.js"></script>
        <script src="resources/js/bootstrap.min.js"></script>

        <script src="resources/js/angular.js"></script>

        <title>Home-Hamja Labratories Ltd.</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <style type="text/css">
            .carousel-inner>.item>img, .carousel-inner>.item>a>img {
                width: 100%;
                height: 440px;
                max-height: 400px;
            }

            .panel {
                display: inline-block;
                background-color: #ffffff;
            }

            .panel:hover {
                cursor: pointer;
                background-color: #dcedc8;
                -webkit-transition: background-color ease-in 0.4s;
                transition: background-color ease-in 0.4s;
            }

            #addtocartbutton, #viewproductsbutton {
                background-color: #9CCC65;
                color: #ffffff;
            }

            #addtocartbutton:hover, #viewproductsbutton:hover {
                background-color: #aed581;
                color: #ffffff;
            }

            #productimage:hover {
                transition: all 0.5s ease;
                transform: scale(1.07);
            }

            #pricediv {
                text-align: right;
                font-size: 20px;
                font-weight: bold;
                font-family: sans-serif;
            }

            #producta {
                color: #000000;
                text-decoration: none;
            }

            #welldiv {
                background-color: #ffffff;
            }

            #loginproductsbutton {
                color: #ffffff;
                text-decoration: none;
                margin-right: 110px;
                background: linear-gradient(to bottom, #aed581 50%, #9ccc65 50%);
            }
            #loginproductsbutton:HOVER {
                background: linear-gradient(to bottom, #9ccc65 50%, #8bc34a 50%);	
            }
        </style>
        <script>
            var productlist = ${productlist};
            angular.module('organocartpackage', []).controller('ProductController',
                    function ($scope) {
                        $scope.productangularobject = productlist;
                    });
        </script>
    </head>
    <body>
        <a href="login">Please Login</a>
        <a href="showcartpage"
           style="color: #9ccc65; font-size: 19px"> <span
                class="glyphicon glyphicon-shopping-cart"></span> <span
                class="badge">${sessionScope.grandquantity}</span>
        </a>
        <jsp:include page="header.jsp" />
        <div id="welldiv" ng-app="organocartpackage"
             ng-controller="ProductController">
            <h3 style="text-align: center;">Products</h3>
            <div class="panel scroll animated fadeInUp delay-05s" style="margin-left: 50px;"
                 ng-repeat="p in productangularobject">
                <a href="viewproduct?getId={{p.productid}}" id="producta">

                    <div class="panel-body">

                        <img id="productimage" class="img-rounded img-fluid"
                             src="resources/pimage/{{p.productid}}.jpg" height="200px"
                             width="200px">

                        <div class="lead" style="margin-top: 5px">{{p.productname}}</div>
                        <div class="">{{p.productdescription}}</div>
                        <hr>
                        <div id="pricediv">
                            <strong style="margin-top: 10px">Tk.{{p.productprice}}</strong>
                        </div>
                    </div>
                </a>
            </div>
            <a href="buyproductpage" class="btn pull-right" id="loginproductsbutton"><span
                    class="glyphicon glyphicon-hand-right"></span>&nbsp; View More Products</a>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
