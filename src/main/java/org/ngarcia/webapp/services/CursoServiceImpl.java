package org.ngarcia.webapp.services;

import org.ngarcia.webapp.models.Curso;
import org.ngarcia.webapp.repositories.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CursoServiceImpl implements CursoService {

   private CursoRepositoryImpl repositoryJdbc;

   public CursoServiceImpl(Connection conn) {
      this.repositoryJdbc = new CursoRepositoryImpl(conn);
   }

   @Override
   public List<Curso> listar() {
      try {
         return repositoryJdbc.listar();
      }
      catch (SQLException e) {
         //se maneja en ConexionFilter
         throw new ServiceJdbcException(e.getMessage(),e.getCause());
      }
   }

   @Override
   public List<Curso> porNombre(String nombre) {
      try {
         return repositoryJdbc.porNombre(nombre);
      }
      catch (SQLException e) {
         //se maneja en ConexionFilter
         throw new ServiceJdbcException(e.getMessage(),e.getCause());
      }
   }
}
