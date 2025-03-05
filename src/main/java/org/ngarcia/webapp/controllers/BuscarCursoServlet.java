package org.ngarcia.webapp.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.ngarcia.webapp.models.Curso;
import org.ngarcia.webapp.services.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/curso/buscar")
public class BuscarCursoServlet extends HttpServlet {

   @Inject
   private CursoService service;

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      //Connection conn = (Connection)  req.getAttribute("conn");
      //CursoService service = new CursoServiceImpl(conn);

      String nombre = req.getParameter("curso");

      List<Curso> cursos = service.porNombre(nombre);

      req.setAttribute("cursos",cursos);

      getServletContext().getRequestDispatcher("/listar-cursos.jsp").forward(req,resp);

   }
}

