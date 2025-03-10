package org.ngarcia.webapp.configs;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.*;
import jakarta.enterprise.inject.*;
import jakarta.enterprise.inject.spi.InjectionPoint;

import java.sql.*;
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
}