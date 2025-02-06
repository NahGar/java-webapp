package org.ngarcia.webapp.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/cambiar-color")
public class CambiarColorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String color = req.getParameter("color");
        Cookie cookie = new Cookie("color", color);
        resp.addCookie(cookie);
            
        //se ejecuta el doGet de esta clase
        resp.sendRedirect(req.getContextPath() + "/tarea-cambia-color.jsp");
    }
}
