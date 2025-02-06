package org.ngarcia.webapp.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Optional;

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
