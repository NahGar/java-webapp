package org.ngarcia.webapp.repositories;

import org.ngarcia.webapp.models.Curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoRepositoryImpl implements Repository<Curso> {

   private Connection conn;

   public CursoRepositoryImpl(Connection conn) {
      this.conn = conn;
   }

   @Override
   public List<Curso> listar() throws SQLException {
      List<Curso> cursos = new ArrayList<>();
      String sql = "SELECT * from cursos";

      try(Statement stmt = conn.createStatement() ) {
         ResultSet rs = stmt.executeQuery(sql);
         while (rs.next()) {
            Curso c = getCurso(rs);
            cursos.add(c);
         }
      }
      return cursos;
   }

   @Override
   public Curso porId(Long id) throws SQLException {
      Curso curso = null;
      String sql = "SELECT * from cursos WHERE id=?";

      try(PreparedStatement stmt = conn.prepareStatement(sql) ) {
         stmt.setLong(1,id);

         try(ResultSet rs = stmt.executeQuery();) {

            if (rs.next()) {
               curso = getCurso(rs);
            }
         }
      }
      return curso;
   }

   @Override
   public List<Curso> porNombre(String nombre) throws SQLException {
      List<Curso> cursos = new ArrayList<>();
      String sql = "SELECT * FROM cursos WHERE nombre like ?";

      try(PreparedStatement stmt = conn.prepareStatement(sql) ) {
         stmt.setString(1,"%" + nombre + "%");
         ResultSet rs = stmt.executeQuery();
         while (rs.next()) {
            Curso c = getCurso(rs);
            cursos.add(c);
         }
      }
      return cursos;
   }

   @Override
   public void guardar(Curso c) throws SQLException {
      String sql;
      boolean isUpdate = false;
      if(c.getId() != null && c.getId() > 0) {
         isUpdate = true;
         sql = "UPDATE cursos set nombre=?, descripcion=?, instructor=?, duracion=? where id=?";
      }
      else {
         sql = "INSERT INTO cursos (nombre,descripcion,instructor,duracion) VALUES (?,?,?,?)";
      }

      try(PreparedStatement stmt = conn.prepareStatement(sql)) {
         stmt.setString(1,c.getNombre());
         stmt.setString(2,c.getDescripcion());
         stmt.setString(3,c.getInstructor());
         stmt.setFloat(4,c.getDuracion());
         if(isUpdate) {
            stmt.setLong(5,c.getId());
         }
         stmt.executeUpdate();
      }
   }

   @Override
   public void eliminar(Long id) throws SQLException {
      String sql = "DELETE FROM cursos WHERE id=?";
      try(PreparedStatement stmt = conn.prepareStatement(sql)) {
         stmt.setLong(1,id);
         stmt.executeUpdate();
      }
   }

   @Override
   public Curso porSku(String sku) throws SQLException {
      return null;
   }

   private static Curso getCurso(ResultSet rs) throws SQLException {
      Curso c = new Curso();
      c.setId(rs.getInt("id"));
      c.setNombre(rs.getString("nombre"));
      c.setDescripcion(rs.getString("descripcion"));
      c.setInstructor(rs.getString("instructor"));
      c.setDuracion(rs.getFloat("duracion"));
      return c;
   }
}
