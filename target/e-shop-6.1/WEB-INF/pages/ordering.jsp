<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">

<head>

    <meta charset="utf-8">
    <meta name="robots" content="all,follow">
    <meta name="googlebot" content="index,follow,snippet,archive">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="BeautyTree || Интернет магазин кистей для макияжа">
    <meta name="author" content="Liliya Yalovchenko | liliyayalovchenko@gmail.com">
    <meta name="keywords" content="кисти, макияж, киев, недорого">

    <title>
        BeautyTree || Интернет магазин кистей для макияжа
    </title>

    <link href='http://fonts.googleapis.com/css?family=Roboto:400,500,700,300,100' rel='stylesheet' type='text/css'>

    <!-- styles -->
    <link href="/resources/css/font-awesome.css" rel="stylesheet">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/animate.min.css" rel="stylesheet">
    <link href="/resources/css/owl.carousel.css" rel="stylesheet">
    <link href="/resources/css/owl.theme.css" rel="stylesheet">

    <!-- theme stylesheet -->
    <link href="/resources/css/style.violet.css" rel="stylesheet" id="theme-stylesheet">

    <!-- your stylesheet with modifications -->
    <link href="/resources/css/custom.css" rel="stylesheet">

    <script src="/resources/js/respond.min.js"></script>

    <link rel="shortcut icon" href="/resources/favicon.png">

</head>

<body>
    <!-- *** TOPBAR ***
 _________________________________________________________ -->
    <div id="top">
        <div class="container">
            <div class="col-sm-3 offer" data-animate="fadeInDown">
                <a href="/catalog/sale" class="btn btn-success btn-sm" style="background-color: #f1e1ef; color: #777777; border-color: #f1e1ef;" data-animate-hover="shake">Предложение дня</a>
            </div>
            <div class="col-sm-5 offer" data-animate="fadeInDown">
                <p style="padding-top: 5px;"><a href="/catalog">Получи скидку 20% от заказа на сумму больше 1000 грн</a></p>
            </div>
            <div class="col-sm-4" data-animate="fadeInDown">

                <p class="text-center"><a href="/contacts">Контакты</a></p>

            </div>
        </div>
    </div>

    <!-- *** TOP BAR END *** -->

    <!-- *** NAVBAR ***
 _________________________________________________________ -->

    <div class="navbar navbar-default yamm" role="navigation" id="navbar">
        <div class="container">
            <div class="navbar-header">

                <a class="navbar-brand home" href="/" data-animate-hover="bounce">
                    <img src="/resources/img/logo.png" alt="Obaju logo" class="hidden-xs">
                    <img src="/resources/img/logo-small.png" alt="Obaju logo" class="visible-xs"><span class="sr-only">BeautyTree - go to homepage</span>
                </a>
                <div class="navbar-buttons">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation">
                        <span class="sr-only">Toggle navigation</span>
                        <i class="fa fa-align-justify"></i>
                    </button>
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#search">
                        <span class="sr-only">Toggle search</span>
                        <i class="fa fa-search"></i>
                    </button>
                    <a class="btn btn-default navbar-toggle" href="/cart">
                        <i class="fa fa-shopping-cart"></i>  <span class="hidden-xs">${cartSize} ед. в корзине</span>
                    </a>
                </div>
            </div>
            <!--/.navbar-header -->

            <div class="navbar-collapse collapse" id="navigation">

                <ul class="nav navbar-nav navbar-left">
                    <li class="active">
                        <a href="/">Главная</a>
                    </li>
                    <li class="yamm-fw">
                        <a href="/news">Советы и новости</a>
                    </li>
                    <li class="dropdown yamm-fw">
                        <a href="/catalog" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="200">Каталог<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <div class="yamm-content">
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <h5>Китсти</h5>
                                            <ul>
                                                <c:forEach items="${categories}" var="category">
                                                    <li>
                                                        <a href="/catalog/${category.id}">
                                                            <c:out value="${category.name}"/>
                                                        </a>
                                                    </li>
                                                </c:forEach>

                                            </ul>
                                        </div>
                                        <div class="col-sm-3">
                                            <h5>Бренды</h5>
                                            <ul>
                                                <c:forEach items="${brands}" var="brand">
                                                    <li>
                                                        <a href="/catalog/brand/${brand}">${brand}</a>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                        <div class="col-sm-3">
                                            <h5>Другое</h5>
                                            <ul>
                                                <li><a href="/catalog">Все товары</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <!-- /.yamm-content -->
                            </li>
                        </ul>
                    </li>

                </ul>

            </div>
            <!--/.nav-collapse -->

            <div class="navbar-buttons">

                <div class="navbar-collapse collapse right" id="basket-overview">
                    <a href="/cart" class="btn btn-primary navbar-btn"><i class="fa fa-shopping-cart"></i><span class="hidden-sm">${cartSize} ед. в корзине</span></a>
                </div>
                <!--/.nav-collapse -->

                <div class="navbar-collapse collapse right" id="search-not-mobile">
                    <button type="button" class="btn navbar-btn btn-primary" data-toggle="collapse" data-target="#search">
                        <span class="sr-only">Toggle search</span>
                        <i class="fa fa-search"></i>
                    </button>
                </div>

            </div>

            <div class="collapse clearfix" id="search">

                <form class="navbar-form" role="search" method="post" action="/search">
                    <div class="input-group">
                        <input type="text" class="form-control" name="pattern" placeholder="Поиск">
                        <span class="input-group-btn">

			<button type="submit" class="btn btn-primary"><i class="fa fa-search"></i></button>

		    </span>
                    </div>
                </form>

            </div>
            <!--/.nav-collapse -->

        </div>
        <!-- /.container -->
    </div>
    <!-- /#navbar -->

    <!-- *** NAVBAR END *** -->

    <div id="all">

        <div id="content">
            <div class="container">

                <div class="col-md-12">

                    <ul class="breadcrumb">
                        <li><a href="/">Главная</a>
                        </li>
                        <li><a href="#">Твой заказ</a>
                        </li>
                        <li>Заказ номер #10${order.id}</li>
                    </ul>

                </div>

                <div class="col-md-3">
                    <!-- *** CUSTOMER MENU ***
 _________________________________________________________ -->
                    <div class="panel panel-default sidebar-menu">

                        <div class="panel-heading">
                            <h3 class="panel-title">Твой заказ готов</h3>
                        </div>

                        <div class="panel-body">


                        </div>

                    </div>
                    <!-- /.col-md-3 -->

                    <!-- *** CUSTOMER MENU END *** -->
                </div>

                <div class="col-md-9" id="customer-order">
                    <div class="box">
                        <h1>Заказ #10${order.id}</h1>

                        <p class="lead"><c:out value="${client.firstName}"/>, твой заказ #10${order.id} оформлен <strong>${date}</strong> и сейчас <strong>обрабатывается</strong>.</p>
                        <p class="text-muted">Если у тебя есть любые вопросы, <a href="/contacts">свяжись с нами</a>, мы будем рады тебе помочь.</p>

                        <hr>

                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th style="text-align: center" colspan="2">Товар</th>
                                        <th style="text-align: center">Количество</th>
                                        <th style="text-align: center">Цена</th>
                                        <th style="text-align: center">Скидка</th>
                                        <th style="text-align: center">Итог</th>
                                        <th style="text-align: center">Всего</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${ProductsInCart}" var="product">
                                    <tr>
                                        <td align="center">
                                            <a href="/product/${product.product_id.id}">
                                                <img src="/resources/${product.product_id.image1}" alt="<c:out value="${product.name}"/>">
                                            </a>
                                        </td>
                                        <td align="center"><a href="/product/${product.product_id.id}"><c:out value="${product.name}"/></a>
                                        <td align="center">${product.quantity}</td>
                                        <td align="center"><fmt:formatNumber value="${product.price/((100 - product.product_id.discount)/100)}" type="number" pattern="#"/></td>
                                        <td align="center">${product.product_id.discount}%</td>
                                        <td align="center">₴${product.price}</td>
                                        <td align="center">₴${product.price * product.quantity}</td>

                                    </tr>
                                </c:forEach>

                                </tbody>
                                <tfoot>
                                    <tr>
                                        <th colspan="5" class="text-right">Итог</th>
                                        <th>₴${totalAmount}</th>
                                    </tr>
                                    <tr>
                                        <th colspan="5" class="text-right">Доставка</th>
                                        <th><c:out value="${order.delivery}"/></th>
                                    </tr>
                                    <tr>
                                        <th colspan="5" class="text-right">Ваш коментарий</th>
                                        <th><c:out value="${order.comments}"/></th>
                                    </tr>

                                </tfoot>
                            </table>

                        </div>
                        <!-- /.table-responsive -->

                    </div>
                </div>

            </div>
            <!-- /.container -->
        </div>
        <!-- /#content -->


        <!-- *** FOOTER ***
 _________________________________________________________ -->
        <div id="footer" data-animate="fadeInUp">
            <div class="container">
                <div class="row">
                    <div class="col-md-3 col-sm-6">
                        <h4>Навигация</h4>

                        <ul>
                            <li><a href="/about">О нас</a>
                            </li>

                            <li><a href="/contacts">Связь с нами</a>
                            </li>
                        </ul>

                    </div>
                    <!-- /.col-md-3 -->

                    <div class="col-md-3 col-sm-6">

                        <h4>Категории</h4>

                        <ul>
                            <c:forEach items="${categories}" var="category">
                                <li>
                                    <a href="/catalog/${category.id}">
                                        <c:out value="${category.name}"/>
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>



                    </div>
                    <!-- /.col-md-3 -->

                    <div class="col-md-3 col-sm-6">

                        <h4>Где мы находимся</h4>

                        <p><strong>BeautyTree</strong>
                            <br>Тычины Павла проп-т
                            <br>Левый берег
                            <br>Березняки
                            <br>Киев
                            <br>
                            <strong>Украина</strong>
                        </p>

                        <a href="/contacts">Контакты</a>

                        <hr class="hidden-md hidden-lg">

                    </div>
                    <!-- /.col-md-3 -->



                    <div class="col-md-3 col-sm-6">

                        <h4>Stay in touch</h4>

                        <p class="social">
                            <%--<a href="#" class="facebook external" data-animate-hover="shake"><i class="fa fa-facebook"></i></a>--%>

                            <a href="#" class="instagram external" data-animate-hover="shake"><i class="fa fa-instagram"></i></a>

                            <a href="#" class="email external" data-animate-hover="shake"><i class="fa fa-envelope"></i></a>
                        </p>


                    </div>
                    <!-- /.col-md-3 -->

                </div>
                <!-- /.row -->

            </div>
            <!-- /.container -->
        </div>
        <!-- /#footer -->

        <!-- *** FOOTER END *** -->

        <!-- *** COPYRIGHT ***
 _________________________________________________________ -->
        <div id="copyright">
            <div class="container">
                <div class="col-md-6">
                    <p class="pull-left">© 2016 BeautyTree</p>

                </div>
                <div class="col-md-6">
                    <p class="pull-right">Design by <a href="http://bootstrapious.com/e-commerce-templates">Bootstrapious</a> with support from <a href="https://kakusei.cz">Kakusei</a>
                        <!-- Not removing these links is part of the licence conditions of the template. Thanks for understanding :) -->
                    </p>
                </div>
            </div>
        </div>
        <!-- *** COPYRIGHT END *** -->

    </div>
    <!-- /#all -->

    <!-- *** SCRIPTS TO INCLUDE ***
 _________________________________________________________ -->
    <script src="/resources/js/jquery-1.11.0.min.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <script src="/resources/js/jquery.cookie.js"></script>
    <script src="/resources/js/waypoints.min.js"></script>
    <script src="/resources/js/modernizr.js"></script>
    <script src="/resources/js/bootstrap-hover-dropdown.js"></script>
    <script src="/resources/js/owl.carousel.min.js"></script>
    <script src="/resources/js/front.js"></script>

</body>

</html>
