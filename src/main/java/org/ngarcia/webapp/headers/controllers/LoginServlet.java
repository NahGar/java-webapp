package org.ngarcia.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;
import org.ngarcia.webapp.headers.services.LoginService;
import org.ngarcia.webapp.headers.services.LoginServiceImpl;

@WebServlet({"/login","/login.html"})
public class LoginServlet extends HttpServlet {

    final static String USERNAME = "admin";
    final static String PASSWORD = "12345";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        //para evitar nullpointerexception
        //Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];
        //Optional<String> cookieOptional = Arrays.stream(cookies)
        //        .filter( c -> c.getName().equals("username"))
        //        .map( c -> c.getValue())
        //        .findAny();
        
        LoginService service = new LoginServiceImpl();
        Optional<String> cookieOptional = service.getUsername(req);
        
        if(cookieOptional.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("  <head>");
                out.println("    <meta charset=\"UTF-8\">");
                out.println("    <title>Hola " + cookieOptional.get() + "</title>");
                out.println("  </head>");
                out.println("  <body>");
                out.println("    <h1>Hola " + cookieOptional.get() + "</h1>");
                out.println("    <p><a href='" + req.getContextPath() + "/index.html'>Volver</p>");
                out.println("    <p><a href='" + req.getContextPath() + "/logout'>Logout</p>");
                out.println("  </body>");
                out.println("</html>");
            }
        }
        else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        if(USERNAME.equals(username) && PASSWORD.equals(password)) {
            
            Cookie usernameCookie = new Cookie("username", username);
            resp.addCookie(usernameCookie);
            
            //se ejecuta el doGet de esta clase
            resp.sendRedirect(req.getContextPath() + "/login.html");
        }
        else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED,"No estás autorizado man");
        }
    }
}
