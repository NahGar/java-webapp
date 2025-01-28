<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<% 
List<String> errores = (List<String>) request.getAttribute("errores");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Formulario de usuarios</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h3>Formulario de usuarios</h3>
        <% if(errores != null && errores.size() > 0) { %>
        <ul>
            <% for(String error: errores) { %>
            <li><% out.print(error); %></li>
            <li><%=error %></li>
            <% } %>
        </ul>
        
        <% } %>
        <form action="/webapp/registro" method="post">
            <div>
                <label for="username">Usuario:</label>
                <div><input type="text" name="username" id="username"></div>
            </div>
            <div>
                <label for="password">Contraseña:</label>
                <div><input type="password" name="password" id="password"></div>
            </div>
            <div>
                <label for="email">Email</label>
                <div><input type="text" name="email" id="email"></div>
            </div>
            <div>
                <label for="pais">País</label>
                <div>
                    <select name="pais" id="pais">
                        <option value="">-- seleccionar --</option>
                        <option value="UY" selected="">Uruguay</option>
                        <option value="CH">Chile</option>
                        <option value="AR">Argentina</option>
                        <option value="BR">Brasil</option>
                        <option value="CO">Colombia</option>
                        <option value="VE">Venezuela</option>
                    </select>
                </div>
            </div>
            
            <div>
                <label for="lenguajes">Lenguajes de programación</label>
                <div>
                    <select name="lenguajes" id="lenguajes" multiple>
                        <option value="java" selected>Java SE</option>
                        <option value="jakartaee">Jakarta EE9</option>
                        <option value="spring">Spring Boot</option>
                        <option value="js">Javascript</option>
                        <option value="angular">Angular</option>
                        <option value="react" selected>React</option>
                    </select>
                </div>
            </div>
            
            <div>
                <label for="roles">Roles</label>
                <div>
                    <input type="checkbox" name="roles" value="ROLE_ADMIN">
                    <label>Administrador</label>
                </div>
                <div>
                    <input type="checkbox" name="roles" value="ROLE_USER" checked>
                    <label>Usuario</label>
                </div>
                <div>
                    <input type="checkbox" name="roles" value="ROLE_MODERATOR">
                    <label>Moderador</label>
                </div>
            </div>
            
            <div>
                <label>Idiomas</label>
                <div>
                    <input type="radio" name="idioma" value="es">
                    <label>Español</label>
                </div>
                <div>
                    <input type="radio" name="idioma" value="en">
                    <label>Inglés</label>
                </div>
                <div>
                    <input type="radio" name="idioma" value="fr">
                    <label>Francés</label>
                </div>
                <div>
                    <input type="radio" name="idioma" value="ch">
                    <label>Chino</label>
                </div>
                <div>
                    <input type="radio" name="idioma" value="po">
                    <label>Portugués</label>
                </div>
            </div>
            
            <div>
                <label for="habilitar">Habilitar</label>
                <div>
                    <input type="checkbox" name="habilitar" id="habilitar" checked>
                </div>
            </div>
            
            <div>
                <input type="submit" name="Enviar">
            </div>
        </form>
    </body>
</html>
