package org.ngarcia.webapp.services;

import org.ngarcia.webapp.models.*;
import org.ngarcia.webapp.repositories.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class UsuarioServiceImpl implements UsuarioService {

   private UsuarioRepositoryImpl repository;

   public UsuarioServiceImpl(Connection conn) {
      this.repository = new UsuarioRepositoryImpl(conn);
   }

   @Override
   public Optional<Usuario> login(String username, String password) {

      try {
         return Optional.ofNullable(repository.porUsername(username))
                 .filter( u -> u.getPassword().equals(password));
      } catch (SQLException e) {
         throw new ServiceJdbcException(e.getMessage(),e.getCause());
      }
   }

   @Override
   public List<Usuario> listar() {
      try {
         return repository.listar();
      }
      catch (SQLException e) {
         //se maneja en ConexionFilter
         throw new ServiceJdbcException(e.getMessage(),e.getCause());
      }
   }

   @Override
   public Optional<Usuario> porUsername(String username) {
      try {
         return Optional.ofNullable(repository.porUsername(username));
      }
      catch (SQLException e) {
         //se maneja en ConexionFilter
         throw new ServiceJdbcException(e.getMessage(),e.getCause());
      }
   }

   @Override
   public Optional<Usuario> porId(Long id) {
      try {
         return Optional.ofNullable(repository.porId(id));
      }
      catch (SQLException e) {
         //se maneja en ConexionFilter
         throw new ServiceJdbcException(e.getMessage(),e.getCause());
      }
   }

   @Override
   public void guardar(Usuario u) {
      try {
         repository.guardar(u);
      } catch (SQLException e) {
         //se maneja en ConexionFilter
         throw new ServiceJdbcException(e.getMessage(),e.getCause());
      }
   }

   @Override
   public void eliminar(Long id) {
      try {
         repository.eliminar(id);
      } catch (SQLException e) {
         //se maneja en ConexionFilter
         throw new ServiceJdbcException(e.getMessage(),e.getCause());
      }
   }
}
