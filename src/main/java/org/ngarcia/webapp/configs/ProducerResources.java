package org.ngarcia.webapp.configs;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.*;
import jakarta.enterprise.inject.*;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Named;
import org.ngarcia.webapp.models.LineaFactura;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.naming.*;
import javax.sql.DataSource;

@ApplicationScoped
public class ProducerResources {

   @Resource(name="jdbc/mysqlDB")
   private DataSource ds;
   
   @Produces
   @RequestScoped
   //@Named("conn")
   @MysqlConn
   private Connection beanConnection() throws NamingException, SQLException {
      //Context initContext = new InitialContext();
      //Context envContext = (Context) initContext.lookup("java:/comp/env");
      //DataSource ds = (DataSource) envContext.lookup("jdbc/mysqlDB");
      return ds.getConnection();
   }

   //en lugar del autoclose en ConexionFilter
   public void close(@Disposes @MysqlConn Connection conn) throws SQLException {
      conn.close();
   }

   @Produces
   private Logger beanLogger(InjectionPoint injectionPoint) {
      return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
   }

   @Produces
   @Named("crearLineasFactura")
   private List<LineaFactura> crearLineasFactura() {
      List<LineaFactura> lineasFactura = new ArrayList<>();
      LineaFactura lineaFactura = new LineaFactura();
      lineaFactura.setProducto("Arroz");
      lineaFactura.setPrecio(40);
      lineaFactura.setCantidad(2);
      lineasFactura.add(lineaFactura);
      lineaFactura = new LineaFactura();
      lineaFactura.setProducto("Pan");
      lineaFactura.setPrecio(30);
      lineaFactura.setCantidad(4);
      lineasFactura.add(lineaFactura);
      return lineasFactura;
   }
}