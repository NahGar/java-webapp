package org.ngarcia.webapp.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.ngarcia.webapp.models.Curso;
import org.ngarcia.webapp.services.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/cursos/eliminar")
public class CursoEliminarServlet extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      Connection conn = (Connection) req.getAttribute("conn");
      CursoService service = new CursoServiceImpl(conn);

      Integer id;
      try {
         id = Integer.valueOf(req.getParameter("id"));
      } catch (NumberFormatException e) {
         id = 0;
      }

      if(id > 0) {
         Optional<Curso> opt = service.porId(id);
         if(opt.isPresent()) {
            service.eliminar(id);
            resp.sendRedirect(req.getContextPath() + "/curso/listar");
         }
         else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,"No existe el curso id " + id);
         }
      }
      else {
         resp.sendError(HttpServletResponse.SC_NOT_FOUND,"Error al eliminar curso");
      }
   }
}
