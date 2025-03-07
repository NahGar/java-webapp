package org.ngarcia.webapp.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.*;
import org.ngarcia.webapp.configs.MysqlConn;
import org.ngarcia.webapp.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProductoRepositoryJdbcImpl implements ProductoRepository {

   @Inject
   //@Named("conn")
   @MysqlConn
   private Connection conn;

   //public ProductoRepositoryJdbcImpl(Connection conn) {
   //   this.conn = conn;
   //}

   @Override
   public List listar() throws SQLException {
      List<Producto> productos = new ArrayList<>();
      String sql = "SELECT p.*, c.nombre as categoria from productos as p " +
              "inner join categorias as c ON (p.categoria_id=c.id) ORDER BY p.id ASC";

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
      String sql;
      boolean isUpdate = false;
      if(p.getId() != null && p.getId() > 0) {
         isUpdate = true;
         sql = "UPDATE productos set nombre=?, precio=?, categoria_id=?, sku=? where id=?";
      }
      else {
         sql = "INSERT INTO productos (nombre,precio,categoria_id,sku,fecha_registro) VALUES (?,?,?,?,?)";
      }

      try(PreparedStatement stmt = conn.prepareStatement(sql)) {
         stmt.setString(1,p.getNombre());
         stmt.setInt(2,p.getPrecio());
         stmt.setLong(3,p.getCategoria().getId());
         stmt.setString(4,p.getSku());
         if(isUpdate) {
            stmt.setLong(5,p.getId());
         }
         else {
            stmt.setDate(5,Date.valueOf(p.getFechaRegistro()));
         }
         stmt.executeUpdate();
      }
   }

   @Override
   public void eliminar(Long id) throws SQLException {

      String sql = "DELETE FROM productos WHERE id=?";
      try(PreparedStatement stmt = conn.prepareStatement(sql)) {
         stmt.setLong(1,id);
         stmt.executeUpdate();
      }
   }

   @Override
   public List porNombre(String nombre) throws SQLException {
      List<Producto> productos = new ArrayList<>();
      String sql = "SELECT p.*, c.nombre as categoria from productos as p " +
              "inner join categorias as c ON (p.categoria_id=c.id) " +
              "WHERE p.nombre like ? ORDER BY p.id ASC";

      try(PreparedStatement stmt = conn.prepareStatement(sql) ) {
         stmt.setString(1,'%'+nombre+'%');
         ResultSet rs = stmt.executeQuery();
         while (rs.next()) {
            Producto p = getProducto(rs);
            productos.add(p);
         }
      }
      return productos;
   }

   @Override
   public Producto porSku(String sku) throws SQLException {
      Producto producto = null;
      String sql = "SELECT p.*, c.nombre as categoria from productos as p " +
              "inner join categorias as c ON (p.categoria_id=c.id) " +
              "WHERE p.sku=?";

      try(PreparedStatement stmt = conn.prepareStatement(sql) ) {
         stmt.setString(1,sku);

         try(ResultSet rs = stmt.executeQuery();) {

            if (rs.next()) {
               producto = getProducto(rs);
            }
         }
      }
      return producto;
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
