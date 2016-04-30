<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p>/main --> $ajax post,get, dataTable.js</p>
        <p>/autores --> JSON lista de autores</p>
        <p>/autores/delete/{id} --> eliminar autor con id = {xxxxx}</p>
        <p>/autores/deleteByNombre/{nombre} --> eliminar autor con nombre = {xxxxx}</p>
        <p>/autores/update/{id}/{nombre} --> modificar el nombre del autor con id = {xxxx} a nombre= {xxxxxx}</p>
        <p>**********************************************************</p>
        <p>/libros --> JSON lista de libros</p>
        <p>/libros/add/{isbn}/{title} --> agregar libro con isbn = {xxxxx} y title = {xxxxxx}</p>
        <p>/libros/delete/{id} --> eliminar libro con id = {xxxxx}</p>
        <p>/libros/deleteByNombre/{isbn} --> eliminar libro con isbn = {xxxxx}</p>
        <p>*ya se que estas pensando para que puta esta el campo id como PK si tiene isbn, pero así estaba el codigo del ejemplo y me fio fiaca dropear la tabla :D!</p>
        <p>/libros/update/{id}/{isbn}/{title} --> modificar el fucking libro... me podri de escribir ya sabes lo que hace..</p>
        <p>/libros/addAuthor/{id_libro}/{id_author} --> agrega en ambos lados</p>
        <p>/libros/deleteAuthor/{id_libro}/{id_author} --> elimina en ambos lados</p>
        <br>
        <p>PD: para ver documentación ver mvc-dispatcher-servlet.xml, HomeController, Author y AuthorDAOService</p>
    </body>
</html>
