<%@page contentType="text/html" pageEncoding="UTF-8"%>
       
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tarea cambia color</title>
    </head>
    <body>
        <h3 style="color: ${cookie.color.getValue()}">Texto a cambiar de color</h3>
        
        <form action="/webapp/cambiar-color" method="get">
            <select name="color" id="color">
                <option value="blue" ${cookie.color.getValue().equals('blue') ? 'selected' : ''}>Azul</option>
                <option value="red" ${cookie.color.getValue().equals('red') ? 'selected' : ''}>Rojo</option>
                <option value="green" ${cookie.color.getValue().equals('green') ? 'selected' : ''}>Verde</option>
                <option value="aqua" ${cookie.color.getValue().equals('aqua') ? 'selected' : ''}>Aqua</option>
                <option value="blueViolet" ${cookie.color.getValue().equals('blueViolet') ? 'selected' : ''}>Violet</option>
                <option value="gray" ${cookie.color.getValue().equals('gray') ? 'selected' : ''}>Gris</option>
                <option value="cyan" ${cookie.color.getValue().equals('cyan') ? 'selected' : ''}>Cyan</option>
            </select>
            <input type="submit" value="Aplicar"/>
        </form>
    </body>
</html>
