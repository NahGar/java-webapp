package org.ngarcia.webapp.controllers;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.ngarcia.webapp.configs.ProductoServicePrincipal;
import org.ngarcia.webapp.services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.ngarcia.webapp.models.Producto;

@WebServlet({"/productos"})
public class ProductoServlet extends HttpServlet{

    @Inject
    //para identificar entre esta clase y ProductoServiceImpl (ambos utilizan la misma interface)
    //se puede hacer mediante Named o utilizando la clase ProductoServicePrincial (annotation)
    //@Named("productoDefault")
    @ProductoServicePrincipal
    private ProductoService serviceProd;

    @Inject
    @Named("loginDefault")
    private LoginService loginService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Connection conn = (Connection)  req.getAttribute("conn");
        //ProductoService serviceProd = new ProductoServiceJdbcImpl(conn);
        List<Producto> productos = serviceProd.listar();

        //Con cookie
        //LoginService serviceLogin = new LoginServiceImpl();
        //Optional<String> cookieOptional = serviceLogin.getUsername(req);

        //Con sesion
        //LoginService loginService = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = loginService.getUsername(req);

        //String mensajeRequest = (String) req.getAttribute("mensaje");
        //String mensajeApp = (String) getServletContext().getAttribute("mensaje");

        /*
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
                            "/carro/agregar?id="+p.getId()+"\">agregar al carro</a></td>");
                }
                out.println("      </tr>");
            });
            out.println("    </table>");
            out.println("    <p>Request:" + mensajeRequest + "</p>");
            out.println("    <p>App:" + mensajeApp + "</p>");
            out.println("  </body>");
            out.println("</html>");
        }
        */

        req.setAttribute("productos",productos);
        req.setAttribute("username",usernameOptional);
        req.setAttribute("titulo","Lista de productos");

        getServletContext().getRequestDispatcher("/listar-productos.jsp").forward(req,resp);
    }
}
