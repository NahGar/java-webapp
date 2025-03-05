package org.ngarcia.webapp.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.ngarcia.webapp.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UsuarioRepositoryImpl implements UsuarioRepository {

   @Inject
   @Named("conn")
   private Connection conn;

   //public UsuarioRepositoryImpl(Connection conn) {
   //   this.conn = conn;
   //}

   @Override
   public Usuario porUsername(String username) throws SQLException {
      Usuario usuario = null;
      String sql = "SELECT * from usuarios " +
              "WHERE username=?";

      try(PreparedStatement stmt = conn.prepareStatement(sql) ) {
         stmt.setString(1,username);

         try(ResultSet rs = stmt.executeQuery();) {

            if (rs.next()) {
               usuario = getUsuario(rs);
            }
         }
      }
      return usuario;
   }

   @Override
   public List<Usuario> listar() throws SQLException {
      List<Usuario> usuarios = new ArrayList<>();
      String sql = "SELECT * from usuarios " +
              " ORDER BY id ASC";

      try(Statement stmt = conn.createStatement() ) {
         ResultSet rs = stmt.executeQuery(sql);
         while (rs.next()) {
            Usuario u = getUsuario(rs);
            usuarios.add(u);
         }
      }
      return usuarios;
   }

   @Override
   public Usuario porId(Long id) throws SQLException {
      Usuario usuario = null;
      String sql = "SELECT * from usuarios " +
              "WHERE id=?";

      try(PreparedStatement stmt = conn.prepareStatement(sql) ) {
         stmt.setLong(1,id);

         try(ResultSet rs = stmt.executeQuery();) {

            if (rs.next()) {
               usuario = getUsuario(rs);
            }
         }
      }
      return usuario;
   }

   @Override
   public List<Usuario> porNombre(String nombre) throws SQLException {
      return List.of();
   }

   @Override
   public void guardar(Usuario u) throws SQLException {
      String sql;
      boolean isUpdate = false;
      if(u.getId() != null && u.getId() > 0) {
         isUpdate = true;
         sql = "UPDATE usuarios set username=?, password=?, email=? WHERE id=?";
      }
      else {
         sql = "INSERT INTO usuarios (username,password,email) VALUES (?,?,?)";
      }

      try(PreparedStatement stmt = conn.prepareStatement(sql)) {
         stmt.setString(1,u.getUsername());
         stmt.setString(2,u.getPassword());
         stmt.setString(3,u.getEmail());
         if(isUpdate) {
            stmt.setLong(4,u.getId());
         }
         stmt.executeUpdate();
      }
   }

   @Override
   public void eliminar(Long id) throws SQLException {
      String sql = "DELETE FROM usuarios WHERE id=?";
      try(PreparedStatement stmt = conn.prepareStatement(sql)) {
         stmt.setLong(1,id);
         stmt.executeUpdate();
      }
   }

   private static Usuario getUsuario(ResultSet rs) throws SQLException {
      Usuario u = new Usuario();
      u.setId(rs.getLong("id"));
      u.setUsername(rs.getString("username"));
      u.setPassword(rs.getString("password"));
      u.setEmail(rs.getString("email"));
      return u;
   }
}
