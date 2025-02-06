<%@page contentType="text/html" pageEncoding="UTF-8" import="org.ngarcia.webapp.models.*"%>
<% 
    Carro carro = (Carro) session.getAttribute("carro"); 
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carro de compras</title>
        <style>
        /* Selecciona todas las celdas (td) que sean la tercera en su fila */
        table td:nth-child(3),table td:nth-child(4),table td:nth-child(5) {
          text-align: right;
        }
      </style>
    </head>
    <body>
        <h1>Carro de compras</h1>
        <% if(carro == null || carro.getItems().isEmpty()) { %>
            <p>No hay productos en el carro de compras</p>
        <% } else { %>
        <form name="formCarro" action="/webapp/actualizar-carro" method="post">
            <table>
                <tr>
                    <th>id</th>
                    <th>nombre</th>
                    <th>precio</th>
                    <th>cantidad</th>
                    <th>total</th>
                    <th>borrar</th>
                </tr>
                <% for(ItemCarro item: carro.getItems()) { %>
                    <tr>
                        <td><%=item.getProducto().getId()%>
                            <input type="hidden" name="id_producto" 
                             value="<%=item.getProducto().getId()%>"/></td>
                        <td><%=item.getProducto().getNombre()%></td>
                        <td><%=item.getProducto().getPrecio()%></td>
                        <td><input type="text" size="4" name="cant_1" value="<%=item.getCantidad()%>"/></td>
                        <td><%=item.getImporte()%></td>
                        <td><input type="checkbox" name="delete_producto" value="<%=item.getProducto().getId()%>"/></td>
                    </tr>
                <% } %>
                <tr>
                    <td colspan="4" style="text-align: right">Total:</td>
                    <td><%=carro.getTotal()%></td>
                </tr>
            </table>
                <input type="submit" value="Guardar cambios" />
        </form>
        <% } %>
        <p><a href="<%=request.getContextPath()%>/productos1.html">Seguir comprando</a></p>
        <p><a href="<%=request.getContextPath()%>/index.html">Volver</a></p>
    </body>
</html>
