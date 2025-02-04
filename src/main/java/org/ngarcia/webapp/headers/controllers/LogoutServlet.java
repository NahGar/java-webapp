package org.ngarcia.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import org.ngarcia.webapp.headers.services.LoginService;
import org.ngarcia.webapp.headers.services.LoginServiceImpl;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        LoginService auth = new LoginServiceImpl();
        Optional<String> username = auth.getUsername(req);
        if( username.isPresent() ) {
            Cookie usernameCookie = new Cookie("username","");
            usernameCookie.setMaxAge(0);
            resp.addCookie(usernameCookie);
            
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
    
}
