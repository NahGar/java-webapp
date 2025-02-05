package org.ngarcia.webapp.tareas.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/guardar-sesion")
public class GuardarNombreSessionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nombre = req.getParameter("nombre");
        if(nombre != null) {
            req.getSession().setAttribute("nombre", nombre);

            resp.sendRedirect(req.getContextPath() + "/perfil-usuario");
        }
    }
}
