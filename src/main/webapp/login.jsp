<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="layout/header.jsp"/>
    
<form action="${pageContext.request.contextPath}/login" method="post">
    <div style="display: flex; align-items: center; margin-bottom: 10px; gap: 10px;">
        <label for="username" class="form-label" style="width: 100px;">Username</label>
        <input type="text" name="username" id="username" class="form-control">
    </div>
    <div style="display: flex; align-items: center; margin-bottom: 10px; gap: 10px;">
        <label for="password" class="form-label" style="width: 100px;">Password</label>
        <input type="password" name="password" id="password" class="form-control">
    </div>
    <input type="submit" value="Login" class="btn btn-primary">
    <c:if test="${error != null}">
        <div class="text-danger mt-2">${error}</div>
    </c:if>
</form>

<jsp:include page="layout/footer.jsp"/>