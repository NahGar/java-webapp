package org.ngarcia.webapp.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ngarcia.webapp.services.LoginService;
import org.ngarcia.webapp.services.LoginServiceSessionImpl;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.Optional;

@WebFilter("/carro/*")
public class LoginFiltro implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        LoginService service = new LoginServiceSessionImpl();
        Optional<String> username = service.getUsername((HttpServletRequest) servletRequest);
        //si está logueado continúa el request
        if(username.isPresent()) {
            filterChain.doFilter(servletRequest,servletResponse);
        }
        else {
            ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Debe loguearse para acceder al recurso");
        }
    }
}
