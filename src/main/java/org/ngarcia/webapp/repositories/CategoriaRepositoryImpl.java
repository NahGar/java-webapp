package org.ngarcia.webapp.repositories;

import jakarta.inject.Inject;
import org.ngarcia.webapp.configs.*;
import org.ngarcia.webapp.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//@ApplicationScoped
@Repositorio
public class CategoriaRepositoryImpl implements Repository<Categoria> {

   private Connection conn;

   //public CategoriaRepositoryImpl(Connection conn) {
   //   this.conn = conn;
   //}

   @Inject
   //public CategoriaRepositoryImpl(@Named("conn") Connection conn) {
   public CategoriaRepositoryImpl(@MysqlConn Connection conn) {
      this.conn = conn;
   }

   @Override
   public List<Categoria> listar() throws SQLException {
      List<Categoria> categorias = new ArrayList<>();
      String sql = "SELECT * from categorias";

      try(Statement stmt = conn.createStatement() ) {
         ResultSet rs = stmt.executeQuery(sql);
         while (rs.next()) {
            Categoria c = getCategoria(rs);
            categorias.add(c);
         }
      }
      return categorias;
   }

   @Override
   public Categoria porId(Long id) throws SQLException {
      Categoria categoria = null;
      String sql = "SELECT * from categorias " +
              "WHERE id=?";

      try(PreparedStatement stmt = conn.prepareStatement(sql) ) {
         stmt.setLong(1,id);

         try(ResultSet rs = stmt.executeQuery();) {

            if (rs.next()) {
               categoria = getCategoria(rs);
            }
         }
      }
      return categoria;
   }

   @Override
   public List<Categoria> porNombre(String nombre) throws SQLException {
      return List.of();
   }

   @Override
   public void guardar(Categoria c) throws SQLException {
      String sql;
      boolean isUpdate = false;
      if(c.getId() != null && c.getId() > 0) {
         isUpdate = true;
         sql = "UPDATE categorias set nombre=? where id=?";
      }
      else {
         sql = "INSERT INTO categorias (nombre) VALUES (?)";
      }

      try(PreparedStatement stmt = conn.prepareStatement(sql)) {
         stmt.setString(1,c.getNombre());
         if(!isUpdate) {
            stmt.setLong(2,c.getId());
         }
         stmt.executeUpdate();
      }
   }

   @Override
   public void eliminar(Long id) throws SQLException {
      String sql = "DELETE FROM categorias WHERE id=?";
      try(PreparedStatement stmt = conn.prepareStatement(sql)) {
         stmt.setLong(1,id);
         stmt.executeUpdate();
      }
   }

   private static Categoria getCategoria(ResultSet rs) throws SQLException {
      Categoria c = new Categoria();
      c.setId(rs.getLong("id"));
      c.setNombre(rs.getString("nombre"));
      return c;
   }
}
