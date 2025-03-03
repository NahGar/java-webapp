package org.ngarcia.webapp.services;

import org.ngarcia.webapp.models.Usuario;

import java.util.*;

public interface UsuarioService {
   List<Usuario> listar();
   Optional<Usuario> porUsername(String username);
   Optional<Usuario> porId(Long id);
   void guardar(Usuario u);
   void eliminar(Long id);
   Optional<Usuario> login(String username, String password);
}
