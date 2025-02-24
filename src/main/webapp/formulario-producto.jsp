<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.time.format.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Formulario de producto</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <h3>Formulario de producto</h3>

        <form action="${pageContext.request.contextPath}/productos/form" method="post">
            <div>
                <label for="nombre">Nombre</label>
                <div>
                    <input type="text" name="nombre" id="nombre" maxlength="45" value="${requestScope.producto.nombre}">
                </div>
                <c:if test="${errores != null && errores.containsKey('nombre')}" >
                    <!-- también es válido errores.get("nombre") -->
                    <div style="color: red;">${errores.nombre}</div>
                </c:if>
            </div>
            <div>
                <label for="precio">Precio</label>
                <div>
                    <input type="number" name="precio" id="precio"
                    value="${producto.precio > 0? producto.precio :""}">
                </div>
                <c:if test="${errores != null && !empty errores.precio}" >
                    <div style="color: red;">${errores.precio}</div>
                </c:if>
            </div>
            <div>
                <label for="sku">Sku</label>
                <div>
                    <input type="text" name="sku" id="sku" maxlength="10" value="${producto.precio}">
                </div>
                <c:if test="${errores != null && not empty errores.sku}" >
                    <div style="color: red;">${errores.sku}</div>
                </c:if>
            </div>
            <div>
                <label for="fecha_registro">Fecha registro</label>
                <div>
                    <input type="date" name="fecha_registro" id="fecha_registro"
                    value="${producto.fechaRegistro != null ? producto.fechaRegistro.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : ""}">
                </div>
                <c:if test="${errores != null && errores.containsKey('fecha_registro')}" >
                    <div style="color: red;">${errores.fecha_registro}</div>
                </c:if>
            </div>
            <div>
                <label for="categoria">Categoría</label>
                <div>
                    <select name="categoria" id="categoria">
                        <option value=""> --- seleccionar ---</option>
                        <c:forEach items="${categorias}" var="c">
                            <option value="${c.id}"
                            ${c.id.equals(producto.categoria.id) ? "selected" : ""}>
                            ${c.nombre}</option>
                        </c:forEach>
                    </select>
                </div>
                <c:if test="${errores != null && errores.containsKey('categoria')}" >
                    <div style="color: red;">${errores.categoria}</div>
                </c:if>
            </div>
            <div>
                <input type="submit" value=
                '${producto.id != null && producto.id > 0 ? "Editar" : "Crear"}'>
            </div>
            <input type="hidden" name="id" value="${producto.id}">
        </form>
    </body>
</html>
