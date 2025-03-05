package org.ngarcia.webapp.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.ngarcia.webapp.models.*;
import org.ngarcia.webapp.services.*;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/usuario/eliminar")
public class UsuarioEliminarServlet extends HttpServlet {

   @Inject
   private UsuarioService service;

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp)
           throws ServletException, IOException {

      //Connection conn = (Connection) req.getAttribute("conn");
      //UsuarioService service = new UsuarioServiceImpl(conn);

      Long id;
      try {
         id = Long.valueOf(req.getParameter("id"));
      } catch (NumberFormatException e) {
         id = 0L;
      }

      if(id > 0) {
         Optional<Usuario> opt = service.porId(id);
         if(opt.isPresent()) {
            service.eliminar(id);
            resp.sendRedirect(req.getContextPath() + "/usuario/listar");
         }
         else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,"No existe el usuario id " + id);
         }
      }
      else {
         resp.sendError(HttpServletResponse.SC_NOT_FOUND,"Error al eliminar usuario");
      }
   }
}
