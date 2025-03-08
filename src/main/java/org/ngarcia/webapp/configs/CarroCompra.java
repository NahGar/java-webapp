package org.ngarcia.webapp.configs;

import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Stereotype;
import jakarta.inject.Named;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@SessionScoped
@Named
@Stereotype
@Retention(RUNTIME)
@Target(TYPE)
public @interface CarroCompra {
}
