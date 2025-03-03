package org.ngarcia.webapp.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.ngarcia.webapp.models.*;
import org.ngarcia.webapp.services.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/usuario/form")
public class UsuarioFormServlet extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      Connection conn = (Connection) req.getAttribute("conn");
      UsuarioService service = new UsuarioServiceImpl(conn);

      //Al editar producto viene Id en query
      long id;
      try {
         id = Long.parseLong(req.getParameter("id"));
      }
      catch (NumberFormatException e) {
         id = 0L;
      }

      Usuario usuario = new Usuario();
      if(id != 0) {
         Optional<Usuario> opt = service.porId(id);
         if(opt.isPresent()) {
            usuario = opt.get();
         }
      }

      req.setAttribute("usuario",usuario);
      req.setAttribute("titulo","Formulario de usuario");

      getServletContext().getRequestDispatcher("/formulario-usuario.jsp").forward(req, resp);
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp)
           throws ServletException, IOException {

      Connection conn = (Connection) req.getAttribute("conn");
      UsuarioService service = new UsuarioServiceImpl(conn);

      String username = req.getParameter("username");
      String password = req.getParameter("password");
      String email = req.getParameter("email");

      long id;
      try {
         id = Long.parseLong(req.getParameter("id"));
      } catch (NumberFormatException e) {
         id = 0L;
      }

      Map<String,String> errores = new HashMap<>();
      if(username == null || username.isBlank()) {
         errores.put("username","Falta indicar Username");
      }
      if(password == null || password.isBlank()) {
         errores.put("password","Falta indicar Password");
      }
      if(email == null || email.isBlank()) {
         errores.put("email","Falta indicar Email");
      }

      Usuario usuario = new Usuario();
      usuario.setId(id);
      usuario.setUsername(username);
      usuario.setPassword(password);
      usuario.setEmail(email);

      if(errores.isEmpty()) {

         service.guardar(usuario);

         resp.sendRedirect(req.getContextPath() + "/usuario/listar");
      }
      else {
         req.setAttribute("errores", errores);
         //doGet(req, resp);

         req.setAttribute("usuario", usuario);
         req.setAttribute("titulo","Formulario de usuario");
         getServletContext().getRequestDispatcher("/formulario-usuario.jsp").forward(req, resp);
      }
   }
}
