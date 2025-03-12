<jsp:include page="layout/header.jsp"/>

<ul class="list-group">
    <li class="list-group-item active">Menú de opciones</li>
    <li class="list-group-item"><a href="/webapp/parametros/url-get?saludo=Hola que tal&nombre=Carlitos&codigo=12548">Enviando parámetros</a></li>
    <li class="list-group-item"><a href="/webapp/form-user.jsp">Fomulario de usuarios</a></li>
    <li class="list-group-item"><a href="/webapp/cabeceras-request">Cabeceras http</a></li>
    <li class="list-group-item"><a href="/webapp/productos.html">Listado de productos</a></li>
    <li class="list-group-item"><a href="/webapp/hora-actualizada">Hora actualizada</a></li>
    <li class="list-group-item"><a href="/webapp/redirigir">Utiliza header Location para redirigir a productos</a></li>
    <li class="list-group-item"><a href="/webapp/despachar">Utiliza dispatch para abrir cabeceras-request (conserva url y request)</a></li>
    <li class="list-group-item"><a href="/webapp/login">Login</a></li>
    <li class="list-group-item"><a href="/webapp/logout">Logout</a></li>
    <li class="list-group-item"><a href="/webapp/productos">Listado productos con permiso</a></li>
    <li class="list-group-item"><a href="/webapp/buscar-producto.html">Buscar producto</a></li>
    <li class="list-group-item"><a href="/webapp/carro/ver">Ver carro</a></li>
</ul>

<ul class="list-group mt-3">
    <li class="list-group-item active">Tareas</li>
    <li class="list-group-item"><a href="/webapp/tarea-cambia-color.jsp">Cambia color</a></li>
    <li class="list-group-item"><a href="/webapp/tarea-nombre.jsp">Nombre en sesión</a></li>
    <li class="list-group-item"><a href="/webapp/curso/listar">Listar cursos</a></li>
    <li class="list-group-item"><a href="/webapp/buscar-curso.html">Buscar curso</a></li>
    <li class="list-group-item"><a href="/webapp/factura/ver">Factura</a></li>
</ul>

<jsp:include page="layout/footer.jsp"/>