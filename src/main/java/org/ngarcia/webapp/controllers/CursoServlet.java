package org.ngarcia.webapp.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.ngarcia.webapp.models.*;
import org.ngarcia.webapp.services.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/curso/listar")
public class CursoServlet extends HttpServlet {

   private static final Logger logger = Logger.getLogger(CursoServlet.class.getName());

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      Connection conn = (Connection)  req.getAttribute("conn");
      CursoService service = new CursoServiceImpl(conn);

      List<Curso> cursos = service.listar();

      req.setAttribute("cursos",cursos);

      getServletContext().getRequestDispatcher("/listar-cursos.jsp").forward(req,resp);

   }
}
