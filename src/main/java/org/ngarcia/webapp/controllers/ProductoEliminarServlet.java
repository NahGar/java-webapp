package org.ngarcia.webapp.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ngarcia.webapp.services.ProductoService;
import org.ngarcia.webapp.services.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/productos/eliminar")
public class ProductoEliminarServlet extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      Connection conn = (Connection) req.getAttribute("conn");
      ProductoService service = new ProductoServiceJdbcImpl(conn);

      Long id;
      try {
         id = Long.valueOf(req.getParameter("id"));
      } catch (NumberFormatException e) {
         id = 0L;
      }

      if(id > 0) {
         service.eliminar(id);
      }

      resp.sendRedirect(req.getContextPath() + "/productos1.html");
   }
}
