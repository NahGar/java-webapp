package org.ngarcia.webapp.controllers;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Optional;
import org.ngarcia.webapp.services.*;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Inject
    //@Named("loginSeviceCookie")
    @Named("loginDefault")
    private LoginService auth;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        //Con cookie
        //LoginService auth = new LoginServiceImpl();
        //Optional<String> username = auth.getUsername(req);

        //Con sesion
        //LoginService auth = new LoginServiceSessionImpl();
        Optional<String> username = auth.getUsername(req);

        if( username.isPresent() ) {
            //Borra cookie
            //Cookie usernameCookie = new Cookie("username","");
            //usernameCookie.setMaxAge(0);
            //resp.addCookie(usernameCookie);

            //elimina atributo
            //req.getSession().removeAttribute("username");
            //elimina sesion
            req.getSession().invalidate();
        }
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
