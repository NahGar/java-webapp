<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="org.ngarcia.webapp.models.*"%>
<%
Map<String,String> errores = (Map<String,String>) request.getAttribute("errores");
Curso curso = (Curso) request.getAttribute("curso");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Formulario de curso</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <h3>Formulario de curso</h3>

        <form action="<%=request.getContextPath()%>/cursos/form" method="post">
            <div>
                <label for="nombre">Nombre</label>
                <div>
                    <input type="text" name="nombre" id="nombre" maxlength="60"
                    value="<%=curso.getNombre() != null ? curso.getNombre() : "" %>">
                </div>
                <% if(errores != null && errores.containsKey("nombre")) { %>
                    <div style="color: red;"><%=errores.get("nombre")%></div>
                <% } %>
            </div>
            <div>
                <label for="descripcion">Descripción</label>
                <div>
                    <textarea name="descripcion" id="descripcion" maxlength="120"
                    rows="4" cols="50"><%=curso.getDescripcion() != null ? curso.getDescripcion() : "" %></textarea>
                </div>
                <% if(errores != null && errores.containsKey("descripcion")) { %>
                    <div style="color: red;"><%=errores.get("descripcion")%></div>
                <% } %>
            </div>
            <div>
                <label for="instructor">Instructor</label>
                <div>
                    <input type="text" name="instructor" id="instructor" maxlength="120"
                    value="<%=curso.getInstructor() != null ? curso.getInstructor() : "" %>">
                </div>
                <% if(errores != null && errores.containsKey("instructor")) { %>
                    <div style="color: red;"><%=errores.get("instructor")%></div>
                <% } %>
            </div>
            <div>
                <label for="duracion">Duración</label>
                <div>
                    <input type="text" name="duracion" id="duracion"
                    value="<%=curso.getDuracion() != 0 ? curso.getDuracion() : "" %>">
                </div>
                <% if(errores != null && errores.containsKey("duracion")) { %>
                    <div style="color: red;"><%=errores.get("duracion")%></div>
                <% } %>
            </div>
            <div>
                <input type="submit" value=
                '<%=curso.getId() != null && curso.getId() > 0 ? "Editar" : "Crear" %>'>
            </div>
            <input type="hidden" name="id" value="<%=curso.getId()%>">
        </form>
    </body>
</html>
