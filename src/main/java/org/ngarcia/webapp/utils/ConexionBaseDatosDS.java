package org.ngarcia.webapp.utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/*
   Utiliza POOL de conexiones de tomcat
   Requiere configurar:
    WEB-INF\web.xml
    META-INF\context.xml
*/

public class ConexionBaseDatosDS {

   public static Connection getConnection() throws SQLException, NamingException {

      Context initContext = new InitialContext();
      Context envContext = (Context) initContext.lookup("java:/comp/env");
      DataSource ds = (DataSource) envContext.lookup("jdbc/mysqlDB");
      return ds.getConnection();
   }
}
