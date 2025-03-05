package org.ngarcia.webapp.controllers;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

import org.ngarcia.webapp.models.Usuario;
import org.ngarcia.webapp.services.*;

@WebServlet({"/login","/login.html"})
public class LoginServlet extends HttpServlet {

    @Inject
    private UsuarioService usuarioService;

    @Inject
    @Named("loginDefault")
    private LoginService loginService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        //para evitar nullpointerexception
        //Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];
        //Optional<String> cookieOptional = Arrays.stream(cookies)
        //        .filter( c -> c.getName().equals("username"))
        //        .map( c -> c.getValue())
        //        .findAny();

        //Con cookie
        //LoginService service = new LoginServiceImpl();
        //Optional<String> usernameOptional = service.getUsername(req);

        //Con sesion
        //LoginService service = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = loginService.getUsername(req);
        
        if(usernameOptional.isPresent()) {
            /*
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("  <head>");
                out.println("    <meta charset=\"UTF-8\">");
                out.println("    <title>Hola " + usernameOptional.get() + "</title>");
                out.println("  </head>");
                out.println("  <body>");
                out.println("    <h1>Hola " + usernameOptional.get() + "</h1>");
                out.println("    <p><a href='" + req.getContextPath() + "/index.jsp'>Volver</p>");
                out.println("    <p><a href='" + req.getContextPath() + "/logout'>Logout</p>");
                out.println("  </body>");
                out.println("</html>");
            }
            */
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        }
        else {
            req.setAttribute("titulo","Login");
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        //UsuarioServiceImpl service = new UsuarioServiceImpl((Connection) req.getAttribute("conn"));
        //UsuarioService service = new UsuarioServiceImpl(this.conn);

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if(username == null || username.isBlank() || password == null || password.isBlank()) {
            String error = "Debe indicar usuario y contraseña";
            req.setAttribute("error", error);
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }

        Optional<Usuario> opt = usuarioService.login(username,password);

        if(opt.isPresent()) {

            //Con cookies
            //Cookie usernameCookie = new Cookie("username", username);
            //resp.addCookie(usernameCookie);

            //Con sesión
            req.getSession().setAttribute("username", username);

            //se ejecuta el doGet de esta clase
            resp.sendRedirect(req.getContextPath() + "/login.html");
        }
        else {
            String error = "Usuario o contraseña incorrecta";
            req.setAttribute("error", error);
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }

    }
}
