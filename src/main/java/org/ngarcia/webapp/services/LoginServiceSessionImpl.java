package org.ngarcia.webapp.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;

@ApplicationScoped
@Named("loginDefault")
public class LoginServiceSessionImpl implements LoginService {

    @Override
    public Optional<String> getUsername(HttpServletRequest req) {

        String username = (String) req.getSession().getAttribute("username");
        if(username != null) {
            return Optional.of(username);
        }
        return Optional.empty();
    }
    
}
