package org.ngarcia.webapp.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

//@ApplicationScoped
//@Named("loginServiceCookie")
public class LoginServiceImpl implements LoginService {

    @Override
    public Optional<String> getUsername(HttpServletRequest req) {

        Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];
        return Arrays.stream(cookies)
                .filter( c -> c.getName().equals("username"))
                .map( c -> c.getValue())
                .findAny();
    }
    
}
