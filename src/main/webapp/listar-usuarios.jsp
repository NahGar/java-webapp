<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="layout/header.jsp"/>

<c:if test="${requestScope.username.isPresent()}">
    <p class="text-end">
        <a href="${pageContext.request.contextPath}/usuario/form"
           class="btn btn-primary">Agregar</a>
    </p>
</c:if>
<table class="table table-striped table-hover">
    <thead class="thead-dark">
        <tr>
            <c:if test="${requestScope.username.present}">
                <th>ID</th>
                <th>Username</th>
                <th>Email</th>
                <th></th>
                <th></th>
            </c:if>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${usuarios}" var="u">
            <tr>
                <c:if test="${requestScope.username.isPresent()}">
                    <td class="text-center">${u.id}
                        <input type="hidden" name="id_usuario" value="${u.id}"/>
                    </td>
                    <td>${u.username}</td>
                    <td>${u.email}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/usuario/form?id=${u.id}"
                        class="btn btn-warning btn-sm">Editar</a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/usuario/eliminar?id=${u.id}"
                           class="btn btn-danger btn-sm" 
                           onclick="return confirm('¿Está seguro que quiere eliminar?')">Eliminar</a>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </tbody>
</table>

<jsp:include page="layout/footer.jsp"/>
