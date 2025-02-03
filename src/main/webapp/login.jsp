<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Formulario login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Iniciar sesión</h1>
        <form action="/webapp/login" method="post">
            <div>
                <label for="username">Username</label>
                <div>
                    <input type="text" name="username" id="username">
                </div>
            </div>
            <div>
                <label for="password">Password</label>
                <div>
                    <input type="password" name="password" id="password">
                </div>
            </div>
            <input type="submit" value="Login">
        </form>
    </body>
</html>