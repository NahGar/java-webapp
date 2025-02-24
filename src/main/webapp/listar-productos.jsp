<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de productos</title>
    </head>
    <body>
        <h1>Listado de productos</h1>
        <c:if test="${requestScope.username.isPresent()}">
            <div>Hola ${requestScope.username.get()}</div>
            <p><a href="${pageContext.request.contextPath}/productos/form">crear</a></p>
        </c:if>
        <table>
            <tr>
                <th>id</th>
                <th>nombre</th>
                <th>tipo</th>
                <!-- equivalente a isPresent()-->
                <c:if test="${requestScope.username.present}">
                    <th>precio</th>
                    <th>sku</th>
                    <th>fecha registro</th>
                    <th>agregar</th>
                    <th>editar</th>
                    <th>eliminar</th>
                </c:if>
            </tr>

            <c:forEach items="${productos}" var="p">
            <tr>
                <td>${p.id}
                    <input type="hidden" name="id_producto" value="${p.id}"/></td>
                <td>${p.nombre}</td>
                <td>${p.categoria.nombre}</td>
                <c:if test="${requestScope.username.isPresent()}">
                    <td>${p.precio}</td>
                    <td>${p.sku}</td>
                    <td>${p.fechaRegistroFormateada}</td>
                    <td><a href="${pageContext.request.contextPath}/carro/agregar?id=${p.id}">agregar al carro<a/></td>
                    <td><a href="${pageContext.request.contextPath}/productos/form?id=${p.id}">editar<a/></td>
                    <td>
                        <a
                            onclick="return confirm('¿Está seguro que quiere eliminar?')"
                            href="${pageContext.request.contextPath}/productos/eliminar?id=${p.id}">eliminar
                        <a/>
                    </td>
                </c:if>
            </tr>
            </c:forEach>
        </table>
        <p>${applicationScope.mensaje}</p>
        <p>${requestScope.mensaje}</p>
    </body>
</html>

