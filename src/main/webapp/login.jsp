<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="layout/header.jsp"/>
    
<form action="/webapp/login" method="post">
    <div style="display: flex; align-items: center; margin-bottom: 10px; gap: 10px;">
        <label for="username" class="form-label" style="width: 100px;">Username</label>
        <input type="text" name="username" id="username" class="form-control">
    </div>
    <div style="display: flex; align-items: center; margin-bottom: 10px; gap: 10px;">
        <label for="password" class="form-label" style="width: 100px;">Password</label>
        <input type="password" name="password" id="password" class="form-control">
    </div>
    <input type="submit" value="Login" class="btn btn-primary">
</form>

<jsp:include page="layout/footer.jsp"/>