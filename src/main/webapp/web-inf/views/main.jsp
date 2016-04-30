<%-- 
    Document   : main
    Created on : 28/04/2016, 23:24:59
    Author     : Mariano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="base/Header.jsp"/>
<c:set var="base_url" value="${pageContext.request.contextPath}"/>
<div class="container">
    <!--div para mensajes del servidor-->
    <div id="mensaje" class="row"></div>
    <div class="row" id="main">

    </div>
</div>
<jsp:include page="base/Footer.jsp"/>
<script>
    /*
     * $() = cuando el documento este listo
     * $.get(o post,put,etc...) = get forma corta, $.ajax = forma compleja donde el parametro type especifica la petición html
     * */
    function insertAuthor() {$.get("${base_url}/insertAuthor", function (data) {$("#main").html(data);});}
    function listAuthor() {$.get("${base_url}/listAuthor", function (data) {$("#main").html(data);});}
    $(function () {insertAuthor();});
</script>