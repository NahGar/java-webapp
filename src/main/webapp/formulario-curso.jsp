<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Formulario de curso</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
        <h3>Formulario de curso</h3>

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
        </div>
    </body>
</html>
