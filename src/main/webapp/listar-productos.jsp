<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*, org.ngarcia.webapp.models.*" %>
<%
List<Producto> productos = (List<Producto>) request.getAttribute("productos");
Optional<String> username = (Optional<String>) request.getAttribute("username");

String mensajeRequest = (String) request.getAttribute("mensaje");
String mensajeApp = (String) getServletContext().getAttribute("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de productos</title>
    </head>
    <body>
        <h1>Listado de productos</h1>
        <% if(username.isPresent()) { %>
        <div>Hola <%=username.get()%> </div>
        <% } %>
        <table>
            <tr>
                <th>id</th>
                <th>nombre</th>
                <th>tipo</th>
                <% if(username.isPresent()) { %>
                <th>precio</th>
                <th>agregar</th>
                <% } %>
            </tr>

            <% for(Producto p: productos) { %>
            <tr>
                <td><%=p.getId()%>
                    <input type="hidden" name="id_producto" value="<%=p.getId()%>"/></td>
                <td><%=p.getNombre()%></td>
                <td><%=p.getCategoria().getNombre()%></td>
                <% if(username.isPresent()) { %>
                <td><%=p.getPrecio()%></td>
                <td><a href="<%=request.getContextPath()%>/carro/agregar?id=<%=p.getId()%>">agregar al carro<a/></td>
                <% } %>
            </tr>
            <% } %>
        </table>
        <p><%=mensajeApp%></p>
        <p><%=mensajeRequest%></p>
    </body>
</html>

