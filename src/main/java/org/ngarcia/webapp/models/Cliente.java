package org.ngarcia.webapp.models;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.ArrayList;

@RequestScoped
@Named
public class Cliente {
   private String nombre;
   private String apellidos;
   private String email;

   /*
   public Cliente(String nombre, String apellidos) {
      this.nombre = nombre;
      this.apellidos = apellidos;
   }
   */

   @PostConstruct
   public void inicializar() {
      this.nombre = "Carlos";
      this.apellidos = "Gutierrez";
      this.email = "cg@abc.com";
   }

   public String getNombre() {
      return nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public String getApellidos() {
      return apellidos;
   }

   public void setApellidos(String apellidos) {
      this.apellidos = apellidos;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   @Override
   public String toString() {
      return "Cliente{" +
              "nombre='" + nombre + '\'' +
              ", apellidos='" + apellidos + '\'' +
              ", email='" + email + '\'' +
              '}';
   }
}
