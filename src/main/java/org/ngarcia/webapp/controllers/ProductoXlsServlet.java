package org.ngarcia.webapp.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import org.ngarcia.webapp.models.Producto;
import org.ngarcia.webapp.services.ProductoService;
import org.ngarcia.webapp.services.ProductoServiceImpl;

@WebServlet({"/productos.xls","/productos.html"})
public class ProductoXlsServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        ProductoService service = new ProductoServiceImpl();
        List<Producto> productos = service.listar();

        String servletPath = req.getServletPath();
        resp.setContentType("text/html;charset=UTF-8");
        if(servletPath.endsWith(".xls")) {
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-Disposition","attachment;filename=productos.xls");
        }

        try (PrintWriter out = resp.getWriter()) {

            if(servletPath.endsWith(".html")) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("  <head>");
                out.println("    <meta charset=\"UTF-8\">");
                out.println("    <title>Listado de productos</title>");
                out.println("  </head>");
                out.println("  <body>");
                out.println("    <h1>Listado de productos</h1>");
                out.println("    <p><a href=\"" + req.getContextPath() + "/productos.xls" + "\">exportar a xls</a></p>");
                out.println("    <p><a href=\"" + req.getContextPath() + "/productos.json" + "\">to json</a></p>");
            }
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
                out.println("        <td>" + p.getCategoria().getNombre() + "</td>");
                out.println("        <td>" + p.getPrecio() + "</td>");
                out.println("      </tr>");
            });
            out.println("    </table>");
            if(servletPath.endsWith(".html")) {
                out.println("  </body>");
                out.println("</html>");
            }

        }
    }
}
