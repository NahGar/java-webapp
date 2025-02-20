package org.ngarcia.webapp.services;

import org.ngarcia.webapp.models.Categoria;
import org.ngarcia.webapp.models.Producto;
import org.ngarcia.webapp.repositories.CategoriaRepositoryImpl;
import org.ngarcia.webapp.repositories.ProductoRepositoryJdbcImpl;
import org.ngarcia.webapp.repositories.*;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class ProductoServiceJdbcImpl implements ProductoService {

   private Repository<Producto> repositoryJdbc;
   private Repository<Categoria> repositoryCategoriaJdbc;

   public ProductoServiceJdbcImpl(Connection conn) {
      this.repositoryJdbc = new ProductoRepositoryJdbcImpl(conn);
      this.repositoryCategoriaJdbc = new CategoriaRepositoryImpl(conn);
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
   public Optional<Producto> findOneByName(String name) {
      return Optional.empty();
   }

   @Override
   public List<Producto> findAllByName(String name) {
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

   @Override
   public void guardar(Producto producto) {
      try {
         repositoryJdbc.guardar(producto);
      } catch (SQLException e) {
         //se maneja en ConexionFilter
         throw new ServiceJdbcException(e.getMessage(),e.getCause());
      }
   }

   @Override
   public void eliminar(Long id) {
      try {
         repositoryJdbc.eliminar(id);
      } catch (SQLException e) {
         //se maneja en ConexionFilter
         throw new ServiceJdbcException(e.getMessage(),e.getCause());
      }
   }

   @Override
   public List<Categoria> listarCategoria() {
      try {
         return repositoryCategoriaJdbc.listar();
      } catch (SQLException e) {
         //se maneja en ConexionFilter
         throw new ServiceJdbcException(e.getMessage(),e.getCause());
      }
   }

   @Override
   public Optional<Categoria> findByIdCategoria(Long id) {
      try {
         return Optional.ofNullable(repositoryCategoriaJdbc.porId(id));
      }
      catch (SQLException e) {
         //se maneja en ConexionFilter
         throw new ServiceJdbcException(e.getMessage(),e.getCause());
      }
   }

   @Override
   public Optional<Producto> findBySku(String sku) {
      try {
         return Optional.ofNullable(repositoryJdbc.porSku(sku));
      }
      catch (SQLException e) {
         //se maneja en ConexionFilter
         throw new ServiceJdbcException(e.getMessage(),e.getCause());
      }
   }
}
