<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link href="css/SiteStyle.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Medical UP</title>
    </head>
    <body>
        <div class="content">
            <h1>Medical UP</h1>
            <div class="btnContent">
                <button class="btn1" type="button" onclick="location.href ='IniciarSesion'">Iniciar Sesion</button>
            </div>
            <div class="btnContent">
                <button class="btn1" type="button" onclick="location.href ='Registrate'">Registrate</button>
            </div>
            
        </div>
    </body>
</html>
<form action="BuscarUsuario" method="Post">