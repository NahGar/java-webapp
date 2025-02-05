<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tarea nombre sesión</title>
        <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <p>Hola <%=session.getAttribute("nombre") != null ?
        session.getAttribute("nombre"): "anónimo"%>, bienvenido a la tarea.</p>

        <form action="/webapp/guardar-sesion" method="post">
            <div class="row mb-3">
                <label for="nombre" class="col-form-label col-sm-2">Nombre:</label>
                <div class="col-sm-4">
                    <input type="text" name="nombre" id="nombre"
                    class="form-control">
                </div>

            </div>

            <input type="submit" value="Enviar"/>
        </form>
    </body>
</html>