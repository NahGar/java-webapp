package org.ngarcia.webapp.tareas.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;

@WebServlet("/crearProducto")
public class RegistroProductoServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        getServletContext().getRequestDispatcher("/Producto.jsp")
                    .forward(req, resp);
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        resp.setContentType("text/html");
        
        String nombre = req.getParameter("nombre");
        String precio = req.getParameter("precio");
        String fabricante = req.getParameter("fabricante");
        String categoria = req.getParameter("categoria");
                        
        Map<String,String> errores = new HashMap<>();
        
        if(nombre == null || nombre.isBlank()) {
            errores.put("nombre","Falta indicar nombre");
        }
        if(precio == null || precio.isBlank()) {
            errores.put("precio","Falta indicar precio");
        }
        else {
            try {
                Integer precioInt = Integer.valueOf(precio);
            }
            catch(Exception ex) {
                errores.put("precio","Precio incorrecto");
            }
        }
                
        if(fabricante == null) {
            errores.put("fabricante","Falta indicar fabricante");
        }
        else if(fabricante.length() < 4 || fabricante.length() > 10) {
            errores.put("fabricante","El largo del fabricante debe estar entre 4 y 10 caracteres");
        }
        
        if(errores.isEmpty()) {
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<title>Resultado producto</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Resultado producto</h1>");
                out.println("<ul>");
                out.println("<li>nombre: " + nombre + "</li>");
                out.println("<li>precio: " + precio + "</li>");
                out.println("<li>fabricante: " + fabricante + "</li>");
                out.println("<li>categoría: " + categoria + "</li>");
                out.println("</ul>");
                out.println("</body>");
                out.println("</html>");
            }
        }
        else {
            req.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/Producto.jsp")
                    .forward(req, resp);
            
        }
    }
}
