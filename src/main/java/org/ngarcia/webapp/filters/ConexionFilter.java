package org.ngarcia.webapp.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.ngarcia.webapp.services.ServiceJdbcException;
import org.ngarcia.webapp.utils.*;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConexionFilter implements Filter {

   @Override
   public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

      //try(Connection conn = ConexionBaseDatos.getConnection()) {
      try(Connection conn = ConexionBaseDatosDS.getConnection()) {

         if(conn.getAutoCommit()) {
            conn.setAutoCommit(false);
         }

         try {
            servletRequest.setAttribute("conn", conn);
            filterChain.doFilter(servletRequest,servletResponse);
            conn.commit();
         }
         catch (SQLException | ServiceJdbcException e) {
            conn.rollback();
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
      catch (SQLException | NamingException e) {
         e.printStackTrace();
      }
   }
}
