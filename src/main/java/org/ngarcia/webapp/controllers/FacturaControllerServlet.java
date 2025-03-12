package org.ngarcia.webapp.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.ngarcia.webapp.models.*;

import java.io.IOException;

@WebServlet("/factura/ver")
public class FacturaControllerServlet extends HttpServlet {

   @Inject
   private Factura factura;

   @Inject
   private Cliente cliente;

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      req.setAttribute("titulo","Factura");

      getServletContext().getRequestDispatcher("/tarea-factura.jsp").forward(req,resp);

   }
}
