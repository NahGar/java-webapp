package org.ngarcia.webapp.repositories;

import org.ngarcia.webapp.models.Categoria;
import org.ngarcia.webapp.models.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryJdbcImpl implements Repository<Producto> {

   private Connection conn;

   public ProductoRepositoryJdbcImpl(Connection conn) {
      this.conn = conn;
   }

   @Override
   public List listar() throws SQLException {
      List<Producto> productos = new ArrayList<>();
      String sql = "SELECT p.*, c.nombre as categoria from productos as p " +
              "inner join categorias as c ON (p.categoria_id=c.id)";

      try(Statement stmt = conn.createStatement() ) {
         ResultSet rs = stmt.executeQuery(sql);
         while (rs.next()) {
            Producto p = getProducto(rs);
            productos.add(p);
         }
      }
      return productos;
   }

   @Override
   public Producto porId(Long id) throws SQLException {

      Producto producto = null;
      String sql = "SELECT p.*, c.nombre as categoria from productos as p " +
              "inner join categorias as c ON (p.categoria_id=c.id) " +
              "WHERE p.id=?";

      try(PreparedStatement stmt = conn.prepareStatement(sql) ) {
         stmt.setLong(1,id);

         try(ResultSet rs = stmt.executeQuery();) {

            if (rs.next()) {
               producto = getProducto(rs);
            }
         }
      }
      return producto;
   }

   @Override
   public void guardar(Producto p) throws SQLException {

   }

   @Override
   public void eliminar(Long id) throws SQLException {

   }

   @Override
   public List<Producto> porNombre(String nombre) throws SQLException {
      return List.of();
   }

   private static Producto getProducto(ResultSet rs) throws SQLException {
      Producto p = new Producto();
      p.setId(rs.getLong("p.id"));
      p.setNombre(rs.getString("p.nombre"));
      p.setPrecio(rs.getInt("p.precio"));
      Categoria c = new Categoria(rs.getLong("p.categoria_id"),rs.getString("categoria"));
      p.setCategoria(c);
      p.setSku(rs.getString("p.sku"));
      p.setFechaRegistro(rs.getDate("p.fecha_registro").toLocalDate());
      return p;
   }
}
