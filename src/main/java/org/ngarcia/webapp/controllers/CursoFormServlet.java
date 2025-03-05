package org.ngarcia.webapp.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.ngarcia.webapp.models.Curso;
import org.ngarcia.webapp.services.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/cursos/form")
public class CursoFormServlet extends HttpServlet {

   @Inject
   private CursoService service;

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      //Connection conn = (Connection) req.getAttribute("conn");
      //CursoService service = new CursoServiceImpl(conn);

      //Al editar producto viene Id en query
      Integer id;
      try {
         id = Integer.valueOf(req.getParameter("id"));
      }
      catch (NumberFormatException e) {
         id = 0;
      }

      Curso curso = new Curso();
      if(id != 0) {
         Optional<Curso> opt = service.porId(id);
         if(opt.isPresent()) {
            curso = opt.get();
         }
      }

      req.setAttribute("curso",curso);
      req.setAttribute("titulo","Formulario de curso");

      getServletContext().getRequestDispatcher("/formulario-curso.jsp").forward(req, resp);
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      //Connection conn = (Connection) req.getAttribute("conn");
      //CursoService service = new CursoServiceImpl(conn);

      String nombre = req.getParameter("nombre");
      String descripcion = req.getParameter("descripcion");
      String instructor = req.getParameter("instructor");

      Float duracion;
      try {
         duracion = Float.parseFloat(req.getParameter("duracion"));
      } catch (NumberFormatException e) {
         duracion = 0F;
      }

      Map<String,String> errores = new HashMap<>();
      if(nombre == null || nombre.isBlank()) {
         errores.put("nombre","Falta indicar nombre");
      }
      if(descripcion == null || descripcion.isBlank()) {
         errores.put("descripcion","Falta indicar descripción");
      }
      if(instructor == null || instructor.isBlank()) {
         errores.put("instructor","Falta indicar instructor");
      }
      if(duracion.equals(0F)) {
         errores.put("duracion","Falta indicar duración");
      }
      else {
         int horas = duracion.intValue();
         int minutos = Math.round((duracion - horas) * 100);

         if (horas < 0 || minutos < 0 || minutos >= 60) {
            errores.put("duracion", "Formato incorrecto, los minutos deben estar entre 00 y 59");
         }
      }

      int id;
      try {
         id = Integer.valueOf(req.getParameter("id"));
      } catch (NumberFormatException e) {
         id = 0;
      }

      Curso curso = new Curso();
      curso.setId(id);
      curso.setNombre(nombre);
      curso.setDescripcion(descripcion);
      curso.setInstructor(instructor);
      curso.setDuracion(duracion);

      if(errores.isEmpty()) {

         service.guardar(curso);

         resp.sendRedirect(req.getContextPath() + "/curso/listar");
      }
      else {
         req.setAttribute("errores", errores);

         req.setAttribute("curso", curso);
         req.setAttribute("titulo","Formulario de curso");

         getServletContext().getRequestDispatcher("/formulario-curso.jsp").forward(req, resp);
      }
   }
}
