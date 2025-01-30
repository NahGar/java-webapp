<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<% 
Map<String, String> errores = (Map<String,String>) request.getAttribute("errores");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Formulario de productos</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!--
        <link href="./css/bootstrap.min.css" rel="stylesheet">
        -->
        <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <h3>Formulario de productos</h3>
        
        <div class="px-5">
            <form action="/webapp/crearProducto" method="post">
                <div class="row mb-3">
                    <label for="nombre" class="col-form-label col-sm-2">Nombre:</label>
                    <div class="col-sm-4">
                        <input type="text" name="nombre" id="nombre"
                        class="form-control" value="${param.nombre}">
                    </div>
                    <%
                    if(errores != null && errores.containsKey("nombre")) {
                        out.println("<small class='alert alert-danger col-sm-4' style='color: red;'>" + errores.get("nombre") + "</small>");
                    }
                    %>
                </div>
                <div class="row mb-3">
                    <label for="precio" class="col-form-label col-sm-2">Precio:</label>
                    <div class="col-sm-4">
                        <input type="text" name="precio" id="precio" class="form-control"
                               pattern="[0-9]+" inputmode="numeric" value="${param.precio}">
                    </div>
                    <%
                    if(errores != null && errores.containsKey("precio")) {
                        out.println("<small class='alert alert-danger col-sm-4' style='color: red;'>" + errores.get("precio") + "</small>");
                    }
                    %>
                </div>
                <div class="row mb-3">
                    <label for="fabricante" class="col-form-label col-sm-2">Fabricante</label>
                    <div class="col-sm-4">
                        <input type="text" name="fabricante" id="fabricante"
                        class="form-control" value="${param.fabricante}">
                    </div>
                    <%
                    if(errores != null && errores.containsKey("fabricante")) {
                        out.println("<small class='alert alert-danger col-sm-4' style='color: red;'>" + errores.get("fabricante") + "</small>");
                    }
                    %>
                </div>
                
                <div class="row mb-3">
                    <label for="categoria" class="col-form-label col-sm-2">Categoria</label>
                    <div class="col-sm-4">
                        <select name="categoria" id="categoria" class="form-select">
                            <option value="">-- seleccionar --</option>
                            <option value="RO" ${param.pais.equals("RO") ? "selected": ""}>Ropa</option>
                            <option value="EL" ${param.pais.equals("EL") ? "selected": ""}>Electrodoméstico</option>
                            <option value="HI" ${param.pais.equals("HI") ? "selected": ""}>Higiene</option>
                            <option value="AL" ${param.pais.equals("AL") ? "selected": ""}>Alimentos</option>
                            <option value="CO" ${param.pais.equals("CO") ? "selected": ""}>Combustibles</option>
                        </select>
                    </div>
                    <%
                    if(errores != null && errores.containsKey("categoria")) {
                        out.println("<small class='alert alert-danger col-sm-4' style='color: red;'>" + errores.get("categoria") + "</small>");
                    }
                    %>
                </div>
                
                <div class="row mb-3">
                    <div>
                        <input type="submit" name="Enviar" class="btn btn-primary">
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>