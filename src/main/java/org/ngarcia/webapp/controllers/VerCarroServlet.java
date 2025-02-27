package org.ngarcia.webapp.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/carro/ver")
public class VerCarroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        req.setAttribute("titulo","Ver carro");
        getServletContext().getRequestDispatcher("/carro.jsp").forward(req, resp);
    }
    
}
