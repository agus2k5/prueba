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
        <h1>Autores!</h1>
        <c:if test="${not empty mensaje}"><p>Mensaje ${mensaje}</p></c:if>
            <table border="1">
                <thead><tr><td>Id</td><td>Nombre</td><td>Libros</td></thead>
                <tbody>
                <c:forEach items="${autores}" var="autor">
                    <tr>
                        <td><p>${autor.getId()}</p></td>
                        <td><p>${autor.getName()}</p></td>
                        <td>
                            <c:forEach items="${autor.getLibros()}" var="libro">
                                <p>${libro.getTitle()}</p>
                            </c:forEach>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <c:if test="${not empty error}"><p>Error: ${error}</p></c:if>

    </body>
</html>
