package org.ngarcia.webapp.filters;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import org.ngarcia.webapp.services.*;

import java.io.IOException;
import java.util.Optional;

@WebFilter({"/carro/*","/productos/form/*","/productos/eliminar/*"})
public class LoginFiltro implements Filter {

    @Inject
    @Named("loginDefault")
    private LoginService loginService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //LoginService service = new LoginServiceSessionImpl();
        Optional<String> username = loginService.getUsername((HttpServletRequest) servletRequest);
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
