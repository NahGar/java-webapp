package org.ngarcia.webapp.configs;

import jakarta.enterprise.context.*;
import jakarta.enterprise.inject.Stereotype;
import jakarta.inject.Named;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

//@ApplicationScoped
//Con RequestScoped se crea y elimina por cada request que lo utiliza
@RequestScoped
@Named
@Stereotype
@Retention(RUNTIME)
@Target(TYPE)
public @interface RepositoryCDI {
}
