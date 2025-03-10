package org.ngarcia.webapp.services;

import jakarta.inject.Inject;
import org.ngarcia.webapp.models.Curso;
import org.ngarcia.webapp.repositories.*;

import java.sql.*;
import java.util.*;
import org.ngarcia.webapp.configs.Service;

//@ApplicationScoped
@Service
public class CursoServiceImpl implements CursoService {

   @Inject
   private CursoRepositoryImpl repositoryJdbc;

   //public CursoServiceImpl(Connection conn) {
   //   this.repositoryJdbc = new CursoRepositoryImpl(conn);
   //}

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

   @Override
   public Optional<Curso> porId(Integer id) {
      Long idLong = Long.valueOf(id);
      try {
         return Optional.ofNullable(repositoryJdbc.porId(idLong));
      }
      catch (SQLException e) {
         //se maneja en ConexionFilter
         throw new ServiceJdbcException(e.getMessage(),e.getCause());
      }
   }

   @Override
   public void guardar(Curso curso) {
      try {
         repositoryJdbc.guardar(curso);
      } catch (SQLException e) {
         //se maneja en ConexionFilter
         throw new ServiceJdbcException(e.getMessage(),e.getCause());
      }
   }

   @Override
   public void eliminar(Integer id) {
      Long idLong = Long.valueOf(id);
      try {
         repositoryJdbc.eliminar(idLong);
      } catch (SQLException e) {
         //se maneja en ConexionFilter
         throw new ServiceJdbcException(e.getMessage(),e.getCause());
      }
   }
}
