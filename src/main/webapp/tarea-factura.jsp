<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="layout/header.jsp"/>

<div class="mb-3 row">
    <label for="cliente" class="col-md-4 col-form-label">Cliente</label>
    <div class="col-md-8">
        <label for="cliente" class="form-control">${factura.cliente.nombre} ${factura.cliente.apellidos}</label>
    </div>
</div>
<div class="mb-3 row">
    <label for="folio" class="col-md-4 col-form-label">Folio</label>
    <div class="col-md-8">
        <label for="folio" class="form-control">${factura.folio}</label>
    </div>
</div>
<div class="mb-3 row">
    <label for="numero" class="col-md-4 col-form-label">Número</label>
    <div class="col-md-8">
        <label for="numero" class="form-control">${factura.numero}</label>
    </div>
</div>
<div class="mb-3 row">
    <label for="descripcion" class="col-md-4 col-form-label">Descripción</label>
    <div class="col-md-8">
        <label for="descripcion" class="form-control">${factura.descripcion}</label>
    </div>
</div>

<table class="table table-striped table-hover">
    <thead class="thead-dark">
        <tr>
            <th>Producto</th>
            <th>Cantidad</th>
            <th>Precio</th>
            <th>Total</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${factura.lineas}" var="lin">
            <tr>
                <td>${lin.producto}</td>
                <td>${lin.cantidad}</td>
                <td>${lin.precio}</td>
                <td>${lin.total}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<jsp:include page="layout/footer.jsp"/>