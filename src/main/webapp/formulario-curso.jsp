<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="layout/header.jsp"/>
<form action="${pageContext.request.contextPath}/cursos/form" method="post" class="p-4">
    <div>
        <label for="nombre">Nombre</label>
        <div>
            <input type="text" name="nombre" id="nombre" maxlength="60"
            value="${curso.nombre}">
        </div>
        <c:if test='${errores != null && not empty errores.nombre}'>
            <div class="text-danger mt-2">${errores.nombre}</div>
        </c:if>
    </div>
    <div>
        <label for="descripcion">Descripción</label>
        <div>
            <textarea name="descripcion" id="descripcion" maxlength="120"
            rows="4" cols="50">${curso.descripcion}</textarea>
        </div>
        <c:if test='${errores != null && not empty errores.descripcion}'>
            <div class="text-danger mt-2">${errores.descripcion}</div>
        </c:if>
    </div>
    <div>
        <label for="instructor">Instructor</label>
        <div>
            <input type="text" name="instructor" id="instructor" maxlength="120"
            value="${curso.instructor}">
        </div>
        <c:if test='${errores != null && not empty errores.instructor}'>
            <div class="text-danger mt-2">${errores.instructor}</div>
        </c:if>
    </div>
    <div>
        <label for="duracion">Duración</label>
        <div>
            <input type="text" name="duracion" id="duracion"
            value="${curso.duracion}">
        </div>
        <c:if test='${errores != null && not empty errores.duracion}'>
            <div class="text-danger mt-2">${errores.duracion}</div>
        </c:if>
    </div>
    <div>
        <input type="submit" class="btn btn-primary w-100"
           value='${curso.id != null && curso.id > 0 ? "Editar" : "Crear"}'>
    </div>
    <input type="hidden" name="id" value="${curso.id}">
</form>
<jsp:include page="layout/footer.jsp"/>
