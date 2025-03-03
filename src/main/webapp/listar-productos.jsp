<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="layout/header.jsp"/>

<c:if test="${requestScope.username.isPresent()}">
    <div class="alert alert-info">Hola ${requestScope.username.get()}</div>
</c:if>
<c:if test="${requestScope.username.isPresent()}">
    <p class="text-end">
        <a href="${pageContext.request.contextPath}/productos/form"
           class="btn btn-primary">Agregar</a>
    </p>
</c:if>
<table class="table table-striped table-hover">
    <thead class="thead-dark">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Tipo</th>
            <c:if test="${requestScope.username.present}">
                <th>Precio</th>
                <th>SKU</th>
                <th>Fecha Registro</th>
                <th></th>
                <th></th>
                <th></th>
            </c:if>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${productos}" var="p">
            <tr>
                <td class="text-center">${p.id}
                    <input type="hidden" name="id_producto" value="${p.id}"/>
                </td>
                <td>${p.nombre}</td>
                <td>${p.categoria.nombre}</td>
                <c:if test="${requestScope.username.isPresent()}">
                    <td>${p.precio}</td>
                    <td>${p.sku}</td>
                    <td>${p.fechaRegistroFormateada}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/carro/agregar?id=${p.id}" class="btn btn-success btn-sm">Agregar al carro</a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/productos/form?id=${p.id}" class="btn btn-warning btn-sm">Editar</a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/productos/eliminar?id=${p.id}" 
                           class="btn btn-danger btn-sm" 
                           onclick="return confirm('¿Está seguro que quiere eliminar?')">Eliminar</a>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </tbody>
</table>

<div class="alert alert-info">
    <p>${applicationScope.mensaje}</p>
    <p>${requestScope.mensaje}</p>
</div>
        
<jsp:include page="layout/footer.jsp"/>
