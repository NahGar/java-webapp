package org.ngarcia.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import org.ngarcia.webapp.headers.models.Producto;
import org.ngarcia.webapp.headers.services.ProductoService;
import org.ngarcia.webapp.headers.services.ProductoServiceImpl;

@WebServlet("/productos.xls")
public class ProductoXlsServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        ProductoService service = new ProductoServiceImpl();
        List<Producto> productos = service.listar();
        
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("  <head>");
            out.println("    <meta charset=\"UTF-8\">");
            out.println("    <title>Listado de productos</title>");
            out.println("  </head>");
            out.println("  <body>");
            out.println("    <h1>Listado de productos</h1>");
            out.println("    <table>");
            out.println("      <tr>");
            out.println("        <th>id</th>");
            out.println("        <th>nombre</th>");
            out.println("        <th>tipo</th>");
            out.println("        <th>precio</th>");
            out.println("      </tr>");
            productos.forEach(p -> {
                out.println("      <tr>");
                out.println("        <td>" + p.getId() + "</td>");
                out.println("        <td>" + p.getNombre() + "</td>");
                out.println("        <td>" + p.getTipo() + "</td>");
                out.println("        <td>" + p.getPrecio() + "</td>");
                out.println("      </tr>");
            });
            out.println("    </table>");
            out.println("  </body>");
            out.println("</html>");
        }
    }
}
