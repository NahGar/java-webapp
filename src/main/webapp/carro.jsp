<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
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
        <c:choose>
            <c:when test="${carro == null || carro.items.isEmpty()}">
                <p>No hay productos en el carro de compras</p>
            </c:when>
            <c:otherwise>
                <form name="formCarro" action="/webapp/carro/actualizar" method="post">
                    <div class="container px-4">
                        <table class="table table-striped table-bordered table-hover">
                            <thead class="thead-dark">
                                <tr>
                                    <th>id</th>
                                    <th>nombre</th>
                                    <th>precio</th>
                                    <th>cantidad</th>
                                    <th>total</th>
                                    <th>borrar</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${carro.items}" var="item" >
                                    <tr>
                                        <td>${item.producto.id}
                                            <input type="hidden" name="id_producto" value="${item.producto.id}"/></td>
                                        <td>${item.producto.nombre}</td>
                                        <td>${item.producto.precio}</td>
                                        <td><input type="text" size="4" name="cant_1" class="form-control"
                                                   value="${item.cantidad}"/></td>
                                        <td>${item.importe}</td>
                                        <td><input type="checkbox" name="delete_producto" class="form-checkbox"
                                                   value="${item.producto.id}"/></td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td>Total:</td>
                                    <td>${carro.total}</td>
                                    <td></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <input type="submit" value="Guardar cambios" />
                </form>
            </c:otherwise>
        </c:choose>
        <p><a href="${pageContext.request.contextPath}/productos1.html">Seguir comprando</a></p>
        <p><a href="${pageContext.request.contextPath}/index.html">Volver</a></p>
    </body>
</html>
