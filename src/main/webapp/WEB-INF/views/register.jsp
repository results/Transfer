<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page errorPage="error.jsp" %>  
 
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?version=51"/>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/input.js"></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"rel="stylesheet">
    <title>Register</title>
</head>
<body>
    <div class="container-fluid">
    <!-- Start Navigation-->
    <div id="nav_area" class="row">
        <nav class="navbar navbar-expand-md bg-primary fixed-top">
            <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">  <!-- Admin panel, not real work, but for ease of use since no hosting-->

            </div>
            <div class="mx-auto order-0">
                <a class="text-white navbar-brand mx-auto" href="${pageContext.request.contextPath}/"><span class="inline-icon material-icons">send</span> Transfer</a>
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
    <div id="signin-div" class="row"> 
                    <div id="signin-block" class="col-lg-6">
                            <h4 class="display-6">Register</h4>
                            <hr>
                            <h6 style="color: lightcoral;" >${message}</h6>
                            <form:form action="submitRegister" method="post" modelAttribute="user">
                                <table border="0" cellpadding="5">
                                    <tr>
                                        <td>Phone Number: </td>
                                        <td><form:input type="tel" pattern="[0-9]{3}[0-9]{3}[0-9]{4}" path="login.phoneNumber" /></td>
                                       <td> <form:errors style="color: lightcoral;" class="error" path="login.phoneNumber" /></td>
                                    </tr>
                                    <tr>
                                        <td>Password: </td>
                                        <td><form:password path="login.password" /></td>
                                       <td> <form:errors style="color: lightcoral;" class="error" path="login.password" /></td>
                                    </tr>  
                                    <tr>
                                        <td>Name: </td>
                                        <td><form:input path="userInformation.name" /></td>
                                       <td> <form:errors style="color: lightcoral;" class="error" path="userInformation.name" /></td>
                                    </tr>
                                                                        <tr>
                                        <td>Email: </td>
                                        <td><form:input type="email" path="userInformation.email" /></td>
                                       <td> <form:errors style="color: lightcoral;" type="email" id="email" class="error" path="userInformation.email" /></td>
                                    </tr>
                                                                        <tr>
                                        <td>Date of Birth: </td>
                                        <td><form:input path="userInformation.birthDate" type="date" /></td>
                                       <td> <form:errors style="color: lightcoral;" class="error" path="userInformation.birthDate" /></td>
                                    </tr>
                                                                        <tr>
                                        <td>Publicly Viewable: </td>
                                        <td><form:checkbox path="userInformation.viewable" /></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2"><input class="btn btn-primary" type="submit" value="Register"></td>
                                        <td colspan="2"> <a style="text-decoration: none;" href="${pageContext.request.contextPath}/account/signin">Already have an account?</a> </td>
                                    </tr>                    
                                </table>
                            </form:form>
                    </div>
    </div>
    <!-- Footer  -->
 <div id="footerlayer" class="footerlayer bg-primary row">
    <div class="col-12">
        <a href=""><span class="inline-icon-small material-icons">help</span> About</a>
        <a href=""><span class="inline-icon-small material-icons">explore</span> Sitemap</a>
        <a href=""><span class="inline-icon-small material-icons">contact_page</span> Contact</a>
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