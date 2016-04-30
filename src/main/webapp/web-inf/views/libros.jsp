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
        <h1>Libros!</h1>
        <c:if test="${not empty mensaje}"><p>Mensaje ${mensaje}</p></c:if>
            <table border="1">
                <thead><tr><td>Id</td><td>Isbn</td><td>year</td><td>title</td><td>Authores</td></thead>
                <tbody>
                <c:forEach items="${libros}" var="libro">
                    <tr>
                        <td><p>${libro.getId()}</p></td>
                        <td><p>${libro.getIsbn()}</p></td>
                        <td><p>${libro.getYear()}</p></td>
                        <td><p>${libro.getTitle()}</p></td>
                        <td>
                            <c:forEach items="${libro.getAutores()}" var="autor">
                                <p>${autor.getName()}</p>
                            </c:forEach>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <c:if test="${not empty error}"><p>Error: ${error}</p></c:if>

    </body>
</html>
