<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="layout/header.jsp"/>
<p class="text-end">
    <a href="${pageContext.request.contextPath}/cursos/form"
       class="btn btn-primary">Agregar</a>
</p>
<table class="table table-striped table-bordered table-hover">
    <thead class="thead-dark">
        <tr>
            <th>id</th>
            <th>nombre</th>
            <th>descripción</th>
            <th>instructor</th>
            <th>duración</th>
            <th></th>
            <th></th>
        </tr>
    <thead>
    <tbody>
    <c:forEach items="${cursos}" var="c">

        <tr>
            <td>${c.id}</td>
            <td>${c.nombre}</td>
            <td>${c.descripcion}</td>
            <td>${c.instructor}</td>
            <td>${c.duracion}</td>
            <td><a class="btn btn-warning btn-sm"
                    href="${pageContext.request.contextPath}/cursos/form?id=${c.id}">Editar<a/></td>
            <td>
                <a class="btn btn-danger btn-sm"
                    onclick="return confirm('¿Está seguro que quiere eliminar?')"
                    href="${pageContext.request.contextPath}/cursos/eliminar?id=${c.id}">Eliminar<a/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="layout/footer.jsp"/>