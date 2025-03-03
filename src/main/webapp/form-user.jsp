<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<% 
Map<String, String> errores = (Map<String,String>) request.getAttribute("errores");
%>
<jsp:include page="layout/header.jsp"/>
    
<% if(errores != null && errores.size() > 0) { %>
<ul class="alert alert-danger mx-5 px-5">
    <% for(String error: errores.values()) { %>
    <li><% out.print(error); %></li>
    <li><%=error %></li>
    <% } %>
</ul>

<% } %>

<form action="/webapp/registro" method="post">
    <div class="row mb-3">
        <label for="username" class="col-form-label col-sm-2">Usuario:</label>
        <div class="col-sm-4">
            <input type="text" name="username" id="username"
            class="form-control" value="${param.username}">
        </div>
        <%
        if(errores != null && errores.containsKey("username")) {
            out.println("<small class='alert alert-danger col-sm-4' style='color: red;'>" + errores.get("username") + "</small>");
        }
        %>
    </div>
    <div class="row mb-3">
        <label for="password" class="col-form-label col-sm-2">Contraseña:</label>
        <div class="col-sm-4">
            <input type="password" name="password" id="password" class="form-control">
        </div>
        <%
        if(errores != null && errores.containsKey("password")) {
            out.println("<small class='alert alert-danger col-sm-4' style='color: red;'>" + errores.get("password") + "</small>");
        }
        %>
    </div>
    <div class="row mb-3">
        <label for="email" class="col-form-label col-sm-2">Email</label>
        <div class="col-sm-4">
            <input type="text" name="email" id="email"
            class="form-control" value="${param.email}">
        </div>
        <%
        if(errores != null && errores.containsKey("email")) {
            out.println("<small class='alert alert-danger col-sm-4' style='color: red;'>" + errores.get("email") + "</small>");
        }
        %>
    </div>
    <div class="row mb-3">
        <label for="pais" class="col-form-label col-sm-2">País</label>
        <div class="col-sm-4">
            <select name="pais" id="pais" class="form-select">
                <option value="">-- seleccionar --</option>
                <option value="UY" ${param.pais.equals("UY") ? "selected": ""}>Uruguay</option>
                <option value="CH" ${param.pais.equals("CH") ? "selected": ""}>Chile</option>
                <option value="AR" ${param.pais.equals("AR") ? "selected": ""}>Argentina</option>
                <option value="BR" ${param.pais.equals("BR") ? "selected": ""}>Brasil</option>
                <option value="CO" ${param.pais.equals("CO") ? "selected": ""}>Colombia</option>
                <option value="VE" ${param.pais.equals("VE") ? "selected": ""}>Venezuela</option>
            </select>
        </div>
        <%
        if(errores != null && errores.containsKey("pais")) {
            out.println("<small class='alert alert-danger col-sm-4' style='color: red;'>" + errores.get("pais") + "</small>");
        }
        %>
    </div>

    <div class="row mb-3">
        <label for="lenguajes" class="col-form-label col-sm-2">Lenguajes de programación</label>
        <div class="col-sm-4">
            <select name="lenguajes" id="lenguajes" multiple class="form-select">
                <option value="java"
                ${paramValues.lenguajes.stream().anyMatch(v->v.equals("java")).get()?"selected":""}
                >Java SE</option>
                <option value="jakartaee"
                ${paramValues.lenguajes.stream().anyMatch(v->v.equals("jakartaee")).get()?"selected":""}>Jakarta EE9</option>
                <option value="spring"
                ${paramValues.lenguajes.stream().anyMatch(v->v.equals("spring")).get()?"selected":""}>Spring Boot</option>
                <option value="js"
                ${paramValues.lenguajes.stream().anyMatch(v->v.equals("js")).get()?"selected":""}>Javascript</option>
                <option value="angular"
                ${paramValues.lenguajes.stream().anyMatch(v->v.equals("angular")).get()?"selected":""}>Angular</option>
                <option value="react"
                ${paramValues.lenguajes.stream().anyMatch(v->v.equals("react")).get()?"selected":""}>React</option>
            </select>
        </div>
        <%
        if(errores != null && errores.containsKey("lenguajes")) {
            out.println("<small class='alert alert-danger col-sm-4' style='color: red;'>" + errores.get("lenguajes") + "</small>");
        }
        %>
    </div>

    <div class="row mb-3">
        <label for="roles" class="col-form-label col-sm-2">Roles</label>
        <div class="form-check col-sm-2">
            <input type="checkbox" name="roles" value="ROLE_ADMIN" class="form-check-input"
            ${paramValues.roles.stream().anyMatch(v->v.equals("ROLE_ADMIN")).get()?"checked":""}>
            <label class="form-check-label">Administrador</label>
        </div>
        <div class="form-check col-sm-2">
            <input type="checkbox" name="roles" value="ROLE_USER" class="form-check-input"
            ${paramValues.roles.stream().anyMatch(v->v.equals("ROLE_USER")).get()?"checked":""}>
            <label class="form-check-label">Usuario</label>
        </div>
        <div class="form-check col-sm-2">
            <input type="checkbox" name="roles" value="ROLE_MODERATOR" class="form-check-input"
            ${paramValues.roles.stream().anyMatch(v->v.equals("ROLE_MODERATOR")).get()?"checked":""}>
            <label class="form-check-label">Moderador</label>
        </div>
        <%
        if(errores != null && errores.containsKey("roles")) {
            out.println("<small class='alert alert-danger col-sm-4' style='color: red;'>" + errores.get("roles") + "</small>");
        }
        %>
    </div>

    <div class="row mb-3">
        <label class="col-form-label col-sm-2">Idiomas</label>
        <div class="form-check col-sm-2">
            <input type="radio" name="idioma" value="es"
            class="form-check-input" ${param.idioma.equals("es") ? "checked": ""}>
            <label class="form-check-label">Español</label>
        </div>
        <div class="form-check col-sm-2">
            <input type="radio" name="idioma" value="en"
            class="form-check-input" ${param.idioma.equals("en") ? "checked": ""}>
            <label class="form-check-label">Inglés</label>
        </div>
        <div class="form-check col-sm-2">
            <input type="radio" name="idioma" value="fr"
            class="form-check-input" ${param.idioma.equals("fr") ? "checked": ""}>
            <label class="form-check-label">Francés</label>
        </div>
        <div class="form-check col-sm-2">
            <input type="radio" name="idioma" value="ch"
            class="form-check-input" ${param.idioma.equals("ch") ? "checked": ""}>
            <label class="form-check-label">Chino</label>
        </div>
        <div class="form-check col-sm-2">
            <input type="radio" name="idioma" value="po"
            class="form-check-input" ${param.idioma.equals("po") ? "checked": ""}>
            <label class="form-check-label">Portugués</label>
        </div>
        <%
        if(errores != null && errores.containsKey("idioma")) {
            out.println("<small class='alert alert-danger col-sm-4' style='color: red;'>" + errores.get("idioma") + "</small>");
        }
        %>
    </div>

    <div class="row mb-3">
        <label for="habilitar" class="col-form-label col-sm-2">Habilitar</label>
        <div class="form-check">
            <input type="checkbox" name="habilitar" id="habilitar" checked class="form-check-input">
        </div>
    </div>
    <div>
        <input type="hidden" name="secreto" value="secrettttt">
    </div>

    <div class="row mb-3">
        <div>
            <input type="submit" name="Enviar" class="btn btn-primary">
        </div>
    </div>
</form>
<jsp:include page="layout/footer.jsp"/>
