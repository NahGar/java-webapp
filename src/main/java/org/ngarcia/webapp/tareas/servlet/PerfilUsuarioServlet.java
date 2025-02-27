package org.ngarcia.webapp.tareas.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/perfil-usuario")
public class PerfilUsuarioServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = resp.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("  <head>");
            out.println("    <meta charset=\"UTF-8\">");
            out.println("    <title></title>");
            out.println("  </head>");
            out.println("  <body>");
            out.println("    <h1>El nombre es: " + req.getSession().getAttribute("nombre") + "</h1>");
            out.println("    <p><a href='" + req.getContextPath() + "/index.jsp'>Volver</p>");
            out.println("  </body>");
            out.println("</html>");
        }    
    }
}
