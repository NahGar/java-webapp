package org.ngarcia.webapp.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.ngarcia.webapp.models.Producto;
import org.ngarcia.webapp.services.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

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
         Optional<Producto> opt = service.findById(id);
         if(opt.isPresent()) {
            service.eliminar(id);
            resp.sendRedirect(req.getContextPath() + "/productos1.html");
         }
         else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,"No existe el producto id " + id);
         }
      }
      else {
         resp.sendError(HttpServletResponse.SC_NOT_FOUND,"Error al eliminar producto");
      }
   }
}
