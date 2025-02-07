package org.ngarcia.webapp.controllers;

import org.ngarcia.webapp.services.ProductoService;
import org.ngarcia.webapp.services.ProductoServiceImpl;
import org.ngarcia.webapp.services.LoginService;
import org.ngarcia.webapp.services.LoginServiceSessionImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import org.ngarcia.webapp.models.Producto;

@WebServlet({"/productos1.html"})
public class ProductoServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        ProductoService serviceProd = new ProductoServiceImpl();
        List<Producto> productos = serviceProd.listar();

        //Con cookie
        //LoginService serviceLogin = new LoginServiceImpl();
        //Optional<String> cookieOptional = serviceLogin.getUsername(req);

        //Con sesion
        LoginService serviceLogin = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = serviceLogin.getUsername(req);

        String mensajeRequest = (String) req.getAttribute("mensaje");
        String mensajeApp = (String) req.getServletContext().getAttribute("mensaje");

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
            if(usernameOptional.isPresent()) {
                out.println("    <h2>Usuario: " + usernameOptional.get() + "</h2>");
            }
            out.println("    <table>");
            out.println("      <tr>");
            out.println("        <th>id</th>");
            out.println("        <th>nombre</th>");
            out.println("        <th>tipo</th>");
            if(usernameOptional.isPresent()) {
                out.println("        <th>precio</th>");
                out.println("        <th>agregar</th>");
            }
            out.println("      </tr>");
            productos.forEach(p -> {
                out.println("      <tr>");
                out.println("        <td>" + p.getId() + "</td>");
                out.println("        <td>" + p.getNombre() + "</td>");
                out.println("        <td>" + p.getTipo() + "</td>");
                if(usernameOptional.isPresent()) {
                    out.println("        <td>" + p.getPrecio() + "</td>");
                    out.println("        <td><a href=\""+ req.getContextPath()+
                            "/agregar-carro?id="+p.getId()+"\">agregar al carro</a></td>");
                }
                out.println("      </tr>");
            });
            out.println("    </table>");
            out.println("    <p>Request:" + mensajeRequest + "</p>");
            out.println("    <p>App:" + mensajeApp + "</p>");
            out.println("  </body>");
            out.println("</html>");
        }
    }
}
