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
    <meta name="author" content="Liliya Yalovchenko">


    <title>
        Beauty Tree | Корзина
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
                        <li>Корзина</li>
                    </ul>
                </div>

                <div class="col-md-9" id="basket">

                    <div class="box">

                        <form method="post" action="/updateCart">

                            <h1>Корзина</h1>
                            <div class="row">
                                <div class="col-sm-6">
                                    <p class="text-muted text-left">Сейчас у вас ${cartSize} товара в корзине.</p>
                                </div>
                                </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <p class="text-left">
                                        <a href="/cart-clear" class="btn"><i class="fa fa-trash-o"></i> Очистить корзну</a>
                                    </p>
                                </div>
                            </div>

                            <div class="table-responsive">
                                <table class="table">

                                    <thead>
                                        <tr>
                                            <th style="text-align: center" colspan="2">Товар</th>
                                            <th>Кол-во</th>
                                            <th>Цена</th>
                                            <th>Скидка</th>
                                            <th style="text-align: center">Итог</th>
                                            <th style="text-align: center">Удалить</th>
                                            <th colspan="2">Всего</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <c:forEach items="${ProductsInCart}" var="product">
                                            <tr>
                                                <td>
                                                    <a href="/product/${product.product_id.id}">
                                                        <img src="/resources/${product.smallimage}" alt="<c:out value="${product.name}"/>">
                                                    </a>
                                                </td>
                                                <td align="center">
                                                    <a href="/product/${product.product_id.id}">
                                                        <c:out value="${product.name}"/>
                                                    </a>
                                                </td>
                                                <td align="center">
                                                    <input type="number" name="quantity" value="${product.quantity}" class="form-control">
                                                </td>
                                                <td align="center">₴<fmt:formatNumber value="${product.price/((100 - product.product_id.discount)/100)}" type="number" pattern="#"/></td>
                                                <td align="center">${product.product_id.discount}%</td>
                                                <td align="center">₴${product.price}</td>
                                                <td align="center"><a href="/deleteFromCart/${product.name}/${product.quantity}"><i class="fa fa-trash-o"></i></a>
                                                <td align="center">₴${product.price * product.quantity}</td>
                                            </tr>
                                        </c:forEach>

                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <th style="text-align: right;" colspan="5">Всего</th>
                                            <th style="text-align: right;" colspan="3">₴${totalValue}</th>
                                        </tr>
                                    </tfoot>
                                </table>

                            </div>
                            <!-- /.table-responsive -->
                            <div class="box-footer">
                                <div class="pull-right">
                                    <button type="submit" class="btn btn-default"><i class="fa fa-refresh"></i> Обновить корзну</button>
                                </div>

                                <div class="pull-left">
                                    <a href="/catalog"  onclick="history.back()" class="btn btn-default"><i class="fa fa-chevron-left"></i> Продолжить покупки</a>
                                </div>

                            </div>
                        </form>
                        <hr>
                        <form action="/ordering" method="POST">
                        <div class="content">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label for="firstname">Имя</label>
                                            <input type="text" name="firstName" maxlength="25" class="form-control" id="firstname" pattern="[A-Za-zА-Яа-яЁё-Іі-Її ]+" required>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label for="lastname">Фамилия</label>
                                            <input type="text" name="secondName" maxlength="25" class="form-control" id="lastname" pattern="[A-Za-zА-Яа-яЁё-Іі-Її ]+" required>
                                        </div>
                                    </div>
                                </div>
                                <!-- /.row -->

                                <div class="row">

                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label for="phone">Введите телефон в формате 38-0xx-xxx-xx-xx</label>
                                            <input type="text" name="phoneNumber" maxlength="25" class="form-control" id="phone" pattern="38-0[0-9]{2}-[0-9]{3}-[0-9]{2}-[0-9]{2}" required>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label for="email">Email</label>
                                            <input type="email" name="email" maxlength="40" class="form-control" id="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" required>
                                        </div>
                                    </div>

                                </div>

                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="box shipping-method">

                                            <h4>Самовывоз</h4>

                                            <p>Заберите свой товар у нас на Левом берегу</p>

                                            <div class="box-footer text-center">

                                                <input type="radio" name="delivery" value="Самовывоз">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="box shipping-method">

                                            <h4>Новая почта</h4>

                                            <p>Доставка на удобный для вам склад Новой почты</p>

                                            <div class="box-footer text-center">

                                                <input type="radio" name="delivery" value="Новая почта" checked>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <textarea rows="5" class="form-control" name="comments" maxlength="400" placeholder="Напиши склад Новой почты, город или твои коментарии и пожелания"></textarea>
                                    </div>
                                </div>


                                <!-- /.row -->
                            </div>

                            <div class="box-footer">
                                <div class="pull-left">
                                    <a href="/catalog"  onclick="history.back()" class="btn btn-default"><i class="fa fa-chevron-left"></i> Продолжить покупки</a>
                                </div>
                                <div class="pull-right">
                                    <button type="submit" class="btn btn-primary">
                                        Оформить заказ <i class="fa fa-chevron-right"></i>
                                    </button>
                                </div>
                            </div>

                        </form>

                    </div>
                    <!-- /.box -->

                </div>
                <!-- /.col-md-9 -->

                <div class="col-md-3">
                    <div class="box" id="order-summary">
                        <div class="box-header">
                            <h3>Итоги заказа</h3>
                        </div>
                        <div class="table-responsive">
                            <table class="table">
                                <tbody>
                                    <tr>
                                        <td>Сумма заказа</td>
                                        <th>₴${totalValue}</th>
                                    </tr>
                                </tbody>
                            </table>
                        </div>

                    </div>




                </div>
                <!-- /.col-md-3 -->

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

                        <hr>

                        <%--<h4>Для пользователя</h4>--%>

                        <%--<ul>--%>
                            <%--<li><a href="#" data-toggle="modal" data-target="#login-modal">Войти</a>--%>
                            <%--</li>--%>
                            <%--<li><a href="#">Регистрация</a>--%>
                            <%--</li>--%>
                        <%--</ul>--%>

                        <%--<hr class="hidden-md hidden-lg hidden-sm">--%>

                    </div>
                    <!-- /.col-md-3 -->

                    <div class="col-md-3 col-sm-6">

                        <h4>Категории</h4>

                        <ul>
                            <c:forEach items="${categories}" var="category">
                                <li><a href="/catalog/${category.id}">${category.name}</a></li>
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