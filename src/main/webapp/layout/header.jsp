<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Obtener el parámetro "titulo" de la URL
    String titulo = request.getParameter("titulo");
    if (titulo != null && !titulo.isEmpty()) {
        pageContext.setAttribute("titulo", titulo);
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>${titulo}</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <jsp:include page="navbar.jsp"/>
    <div class="container">
        <h3>${titulo}</h3>