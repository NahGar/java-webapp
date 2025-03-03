package org.ngarcia.webapp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/registro")
public class FormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        resp.setContentType("text/html");
        
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String pais = req.getParameter("pais");
        String[] lenguajes = req.getParameterValues("lenguajes");
        String[] roles = req.getParameterValues("roles");
        String idioma = req.getParameter("idioma");
        boolean habilitar = req.getParameter("habilitar") != null && 
                  req.getParameter("habilitar").equals("on");
        String secreto = req.getParameter("secreto");
        
        Map<String,String> errores = new HashMap<>();
        if(username == null || username.isBlank()) {
            errores.put("username","Falta indicar usuario");
        }
        if(password == null || password.isBlank()) {
            errores.put("password","Falta indicar contraseña");
        }
        if(email == null || !email.contains("@") || !email.contains(".")) {
            errores.put("email","Falta indicar email o no es válido");
        }
        if(pais == null || pais.isBlank()) {
            errores.put("pais","Falta indicar país");
        }
        if(lenguajes == null || lenguajes.length == 0) {
            errores.put("lenguajes","Falta indicar lenguajes");
        }
        if(roles == null || roles.length == 0) {
            errores.put("roles","Falta indicar roles");
        }
        if(idioma == null) {
            errores.put("idioma","Falta indicar idioma");
        }
        
        if(errores.isEmpty()) {
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<title>Resultado form</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Resultado form</h1>");
                out.println("<ul>");

                out.println("<li>username: " + username + "</li>");
                out.println("<li>password: " + password + "</li>");
                out.println("<li>email: " + email + "</li>");
                out.println("<li>país: " + pais + "</li>");
                out.println("<li>Lenguajes<ul>");
                Arrays.asList(lenguajes).forEach( (lenguaje) -> out.println("<li>" + lenguaje + "</li>") );
                out.println("</ul></li>");
                out.println("<li>Roles<ul>");
                Arrays.asList(roles).forEach( (rol) -> out.println("<li>" + rol + "</li>") );
                out.println("</ul></li>");

                out.println("<li>idioma: " + idioma + "</li>");
                out.println("<li>habilitar: " + habilitar + "</li>");
                out.println("<li>secreto: " + secreto + "</li>");
                out.println("</ul>");
                out.println("</body>");
                out.println("</html>");
            }
        }
        else {
            /*
            errores.forEach( error -> out.println("<li>Error: " + error + "</li>"));
            out.println("<p><a href=\"/webapp/form-user.html\">Volver</a></p>");
            */
            req.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/form-user.jsp")
                    .forward(req, resp);
            
        }
    }
}
