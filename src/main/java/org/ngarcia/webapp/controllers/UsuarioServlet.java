package org.ngarcia.webapp.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.ngarcia.webapp.models.*;
import org.ngarcia.webapp.services.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet({"/usuario/listar"})
public class UsuarioServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Connection conn = (Connection)  req.getAttribute("conn");
        UsuarioService service = new UsuarioServiceImpl(conn);
        List<Usuario> usuarios = service.listar();

        //Con sesion
        LoginService serviceLogin = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = serviceLogin.getUsername(req);

        req.setAttribute("usuarios",usuarios);
        req.setAttribute("username",usernameOptional);
        req.setAttribute("titulo","Lista de usuarios");

        getServletContext().getRequestDispatcher("/listar-usuarios.jsp").forward(req,resp);
    }
}
