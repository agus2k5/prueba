<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h5>Listado de Autores</h5>
<div class="table-responsive" style="overflow:auto; overflow-y:hidden; margin: 0 auto; withe-space: nowrap">
    <table class="bordered highlight centered" id="authoresTable">
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
</div>
<script type="text/javascript">$(document).ready(function () {var table = $('#authoresTable').dataTable({"autoWidth": false});});</script>