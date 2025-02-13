package org.ngarcia.webapp.services;

import org.ngarcia.webapp.models.Producto;
import org.ngarcia.webapp.repositories.ProductoRepositoryJdbcImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceJdbcImpl implements ProductoService {

   private ProductoRepositoryJdbcImpl repositoryJdbc;

   public ProductoServiceJdbcImpl(Connection conn) {
      this.repositoryJdbc = new ProductoRepositoryJdbcImpl(conn);
   }

   @Override
   public List<Producto> listar() {
      try {
         return repositoryJdbc.listar();
      }
      catch (SQLException e) {
         //se maneja en ConexionFilter
         throw new ServiceJdbcException(e.getMessage(),e.getCause());
      }
   }

   @Override
   public Optional<Producto> findOne(String name) {
      return Optional.empty();
   }

   @Override
   public List<Producto> findAll(String name) {
      return List.of();
   }

   @Override
   public Optional<Producto> findById(Long id) {
      try {
         return Optional.ofNullable(repositoryJdbc.porId(id));
      }
      catch (SQLException e) {
         //se maneja en ConexionFilter
         throw new ServiceJdbcException(e.getMessage(),e.getCause());
      }
   }
}
