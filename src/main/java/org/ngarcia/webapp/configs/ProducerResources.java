package org.ngarcia.webapp.configs;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import java.sql.*;

import javax.naming.*;
import javax.sql.DataSource;

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
}
