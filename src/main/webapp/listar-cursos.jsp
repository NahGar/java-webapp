<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="org.ngarcia.webapp.models.*" %>
<%
List<Curso> cursos = (List<Curso>) request.getAttribute("cursos");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de cursos</title>
    </head>
    <body>
        <h1>Listado de cursos</h1>
        <table>
            <tr>
                <th>id</th>
                <th>nombre</th>
                <th>descripción</th>
                <th>instructor</th>
                <th>duración</th>
            </tr>

            <% for(Curso c: cursos) { %>
            <tr>
                <td><%=c.getId()%></td>
                <td><%=c.getNombre()%></td>
                <td><%=c.getDescripcion()%></td>
                <td><%=c.getInstructor()%></td>
                <td><%=c.getDuracion()%></td>
            </tr>
            <% } %>
        </table>
    </body>
</html>