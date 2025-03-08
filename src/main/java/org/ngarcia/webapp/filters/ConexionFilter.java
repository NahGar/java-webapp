package org.ngarcia.webapp.filters;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.ngarcia.webapp.configs.MysqlConn;
import org.ngarcia.webapp.services.ServiceJdbcException;

import java.io.IOException;
import java.sql.*;

@WebFilter("/*")
public class ConexionFilter implements Filter {

   @Inject
   //@Named("conn")
   @MysqlConn
   private Connection conn;

   @Override
   public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

      //try(Connection conn = ConexionBaseDatos.getConnection()) {
      //try(Connection conn = ConexionBaseDatosDS.getConnection()) {
      //try (Connection connReq = this.conn) {
      try {

         Connection connReq = this.conn;

         if(connReq.getAutoCommit()) {
            connReq.setAutoCommit(false);
         }

         try {
            //la conexion se pasa mediante injección de dependencia
            //servletRequest.setAttribute("conn", conn);
            filterChain.doFilter(servletRequest,servletResponse);
            connReq.commit();
         }
         catch (SQLException | ServiceJdbcException e) {
            connReq.rollback();
            if (e.getClass().getName() == "SQLException")  {
               ((HttpServletResponse) servletResponse).sendError
                       (HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ((SQLException)e).getSQLState() + " " + e.getMessage());
            }
            else {
               ((HttpServletResponse) servletResponse).sendError
                       (HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            }

            e.printStackTrace();
         }

      }
      //catch (SQLException e) {
      //catch (SQLException | NamingException e) {
      catch (SQLException e) {
         e.printStackTrace();
      }
   }
}
