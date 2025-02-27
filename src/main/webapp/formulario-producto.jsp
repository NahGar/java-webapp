<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.time.format.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="layout/header.jsp"/>
        
<div class="row justify-content-center"> 
    <div class="col-md-6"> 
        <form action="${pageContext.request.contextPath}/productos/form" method="post" class="p-4">

            <div class="mb-3 row">
                <label for="nombre" class="col-md-4 col-form-label">Nombre</label>
                <div class="col-md-8">
                    <input type="text" name="nombre" id="nombre" maxlength="45" 
                           class="form-control" value="${requestScope.producto.nombre}">
                    <c:if test="${errores != null && errores.containsKey('nombre')}">
                        <div class="text-danger mt-2">${errores.nombre}</div>
                    </c:if>
                </div>
            </div>

            <div class="mb-3 row">
                <label for="precio" class="col-md-4 col-form-label">Precio</label>
                <div class="col-md-8">
                    <input type="number" name="precio" id="precio" class="form-control"
                           value="${producto.precio > 0 ? producto.precio : ''}">
                    <c:if test="${errores != null && !empty errores.precio}">
                        <div class="text-danger mt-2">${errores.precio}</div>
                    </c:if>
                </div>
            </div>

            <div class="mb-3 row">
                <label for="sku" class="col-md-4 col-form-label">Sku</label>
                <div class="col-md-8">
                    <input type="text" name="sku" id="sku" maxlength="10" class="form-control"
                           value="${producto.sku}">
                    <c:if test="${errores != null && not empty errores.sku}">
                        <div class="text-danger mt-2">${errores.sku}</div>
                    </c:if>
                </div>
            </div>

            <div class="mb-3 row">
                <label for="fecha_registro" class="col-md-4 col-form-label">Fecha registro</label>
                <div class="col-md-8">
                    <input type="date" name="fecha_registro" id="fecha_registro" class="form-control"
                           value="${producto.fechaRegistro != null ? producto.fechaRegistro.format(DateTimeFormatter.ofPattern('yyyy-MM-dd')) : ''}">
                    <c:if test="${errores != null && errores.containsKey('fecha_registro')}">
                        <div class="text-danger mt-2">${errores.fecha_registro}</div>
                    </c:if>
                </div>
            </div>

            <div class="mb-3 row">
                <label for="categoria" class="col-md-4 col-form-label">Categoría</label>
                <div class="col-md-8">
                    <select name="categoria" id="categoria" class="form-select">
                        <option value="">--- Seleccionar ---</option>
                        <c:forEach items="${categorias}" var="c">
                            <option value="${c.id}" ${c.id.equals(producto.categoria.id) ? 'selected' : ''}>
                                ${c.nombre}
                            </option>
                        </c:forEach>
                    </select>
                    <c:if test="${errores != null && errores.containsKey('categoria')}">
                        <div class="text-danger mt-2">${errores.categoria}</div>
                    </c:if>
                </div>
            </div>

            <div style="display: flex;"> <!-- entran en una fila -->
                <a href="<%=request.getContextPath()%>/productos1.html" 
                      class="btn btn-danger w-50">Cancelar</a>
                <input type="submit" class="btn btn-primary w-50"
                       value='${producto.id != null && producto.id > 0 ? "Editar" : "Crear"}'>
            </div>

            <input type="hidden" name="id" value="${producto.id}">
        </form>
    </div>
</div>

<jsp:include page="layout/footer.jsp"/>
