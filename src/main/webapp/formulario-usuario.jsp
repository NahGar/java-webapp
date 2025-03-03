<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="layout/header.jsp"/>
        
<div class="row justify-content-center"> 
    <div class="col-md-6"> 
        <form action="${pageContext.request.contextPath}/usuario/form" method="post" class="p-4">

            <div class="mb-3 row">
                <label for="username" class="col-md-4 col-form-label">Username</label>
                <div class="col-md-8">
                    <input type="text" name="username" id="username" maxlength="20"
                           class="form-control" value="${requestScope.usuario.username}">
                    <c:if test="${errores != null && errores.containsKey('username')}">
                        <div class="text-danger mt-2">${errores.username}</div>
                    </c:if>
                </div>
            </div>

            <div class="mb-3 row">
                <label for="password" class="col-md-4 col-form-label">Password</label>
                <div class="col-md-8">
                    <input type="password" name="password" id="password" maxlength="20"
                           class="form-control" value="">
                    <c:if test="${errores != null && errores.containsKey('password')}">
                        <div class="text-danger mt-2">${errores.password}</div>
                    </c:if>
                </div>
            </div>

            <div class="mb-3 row">
                <label for="email" class="col-md-4 col-form-label">Email</label>
                <div class="col-md-8">
                    <input type="text" name="email" id="email" maxlength="100" class="form-control"
                           value="${usuario.email}">
                    <c:if test="${errores != null && not empty errores.email}">
                        <div class="text-danger mt-2">${errores.email}</div>
                    </c:if>
                </div>
            </div>

            <div style="display: flex;"> <!-- entran en una fila -->
                <a href="<%=request.getContextPath()%>/usuario/listar"
                      class="btn btn-danger w-50">Cancelar</a>
                <input type="submit" class="btn btn-primary w-50"
                       value='${usuario.id != null && usuario.id > 0 ? "Editar" : "Crear"}'>
            </div>

            <input type="hidden" name="id" value="${usuario.id}">
        </form>
    </div>
</div>

<jsp:include page="layout/footer.jsp"/>
