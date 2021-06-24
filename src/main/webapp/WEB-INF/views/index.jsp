<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"/>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/input.js"></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"rel="stylesheet">
    <title>Transfer</title>
</head>
<body>
    <div class="container-fluid">
    <!-- Start Navigation-->
    <div id="nav_area" class="row">
        <nav class="navbar navbar-expand-md bg-primary fixed-top">
            <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">  <!-- Admin panel, not real work, but for ease of use since no hosting-->

            </div>
            <div class="mx-auto order-0">
                <a class="text-white navbar-brand mx-auto" href="${pageContext.request.contextPath}"><span class="inline-icon material-icons">send</span> Transfer</a>
            </div>
            <div class="navbar-collapse collapse w-100 order-2 dual-collapse2">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="text-white nav-link" id="signInButton" href="${pageContext.request.contextPath}/account/signin"><span class="inline-icon material-icons">account_box</span> Sign in</a>
                    </li>
                    <li class="nav-item">
                        <a class="text-white nav-link" href="${pageContext.request.contextPath}/account/register"><span class="inline-icon material-icons">create</span> Register</a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
     <!-- End Navigation-->
    <div id="herolayer" class="row">
                    <div id="herolayer-image" class="herolayer-image col-lg-6">
                        <img src="${pageContext.request.contextPath}/images/mobile-banking-send-rec.png" width = 50% alt="">
                        <div id="herolayer-description">
                        <h1 class="display-6">Transfer With No Limits</h1>
                        <h6>We allow you to transfer funds with no limits whatsoever. That means no restrictions on how often, how much or who you transfer to.</h6>
                        <a class="btn btn-success" href="#" role="button">Learn more</a>
                    </div>
                    </div>    
                    <div id="herolayer-signin" class="col-lg-6">
                            <h4 class="display-6">Welcome</h4>
                            <hr>
                            <a class="btn btn-primary" style="width: 49%;"href="${pageContext.request.contextPath}/account/signin" role="button"><span class="inline-icon material-icons">account_box</span> Sign in</a>
                            <a class="btn btn-primary" style="width: 49%;"href="${pageContext.request.contextPath}/account/register" role="button"><span class="inline-icon material-icons">create</span> Register</a>
                            <br><br>
                            <a style="text-decoration: none;" href="${pageContext.request.contextPath}/account/recovery">Need Help Signing In?</a>
            </div>
    </div>
    <div class="row">
        <div class="col-sm ">
            <img id="index-image-left" src="${pageContext.request.contextPath}/images/undraw_transfer_money_rywa.svg" alt="">
        </div>
        <div class="col-sm" id="index-image-left-description">
            <h3 class="display-6">Send And Receive Money</h3>
            <hr>
            <h6>Easily transfer money to your friends</h6>
            <p>Transferring money to and from your friends is as easy as creating an account, adding your friends, and hitting <b>'transfer'</b>.</p>
        </div>
    </div>
    <div class="row">
        <div class="col-sm" id="index-image-right-description">
            <h3 class="display-6">Join The Others</h3>
            <hr>
            <h4> <b>${totalAccounts} accounts</b> registered and <b>${totalTrans} transactions</b> sent</h6>
            <p>Be like your friends and <a href="${pageContext.request.contextPath}/account/register"><span class="inline-icon material-icons">create</span> Register</a> for the easiest platform for transferring money.</p>
        </div>
        <div class="col-sm ">
            <img id="index-image-right" src="${pageContext.request.contextPath}/images/undraw_add_friends_re_3xte.svg" alt="">
        </div>
    </div>
    <!-- Footer  -->
 <div id="footerlayer" class="footerlayer bg-primary row">
    <div class="col-12">
        <a href="#"><span class="inline-icon-small material-icons">help</span> About</a>
        <a href="#"><span class="inline-icon-small material-icons">explore</span> Sitemap</a>
        <a href="#"><span class="inline-icon-small material-icons">contact_page</span> Contact</a>
    </div>
 </div>
</div><!-- container -->
<div id="alert-overlay">
    <div id="alert-overlay-box">
        <span style="float: left;"class="material-icons inline-icon-lg">error</span>  <span onclick="closeMessage()" style="float: right; color: red;"class="material-icons inline-icon-lg show-pointer">close</span>
        <h4 id="alert-overlay-header"style="text-align: center;"></h4>
        <hr>
        <p id="alert-overlay-text"></p>
        <p id="alert-overlay-footer"></p>
    </div>
</div>
<script>
    var errorMessage = '${message}';
    if(errorMessage != "") {
        showMessage("Error",errorMessage);
    }    
</script>
</body>
</html>