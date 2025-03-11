package org.ngarcia.webapp.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.ngarcia.webapp.services.ServiceJdbcException;

import java.io.IOException;

@WebFilter("/*")
public class ConexionFilter implements Filter {

   //NOTA: En la clase 477 se comienza a usar el interceptor TransactionalInterceptor que se encarga de la TRN
   //@Inject
   ////@Named("conn")
   //@MysqlConn
   //private Connection conn;

   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) 
           throws IOException, ServletException {

      ////try(Connection conn = ConexionBaseDatos.getConnection()) {
      ////try(Connection conn = ConexionBaseDatosDS.getConnection()) {
      ////try (Connection connReq = this.conn) {
      //try {

         //Connection connReq = this.conn;

         //if(connReq.getAutoCommit()) {
         //   connReq.setAutoCommit(false);
         //}

         try {
            //la conexion se pasa mediante injección de dependencia
            //servletRequest.setAttribute("conn", conn);
            filterChain.doFilter(request,response);
            //connReq.commit();
         }
         //catch (SQLException | ServiceJdbcException e) {
         catch (ServiceJdbcException e) {
            //connReq.rollback();
            //if (e.getClass().getName() == "SQLException")  {
            //   ((HttpServletResponse) servletResponse).sendError
            //           (HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ((SQLException)e).getSQLState() + " " + e.getMessage());
            //}
            //else {
               ((HttpServletResponse) response).sendError
                       (HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            //}

            e.printStackTrace();
         }
      //}
      ////catch (SQLException e) {
      ////catch (SQLException | NamingException e) {
      //catch (SQLException e) {
      //   e.printStackTrace();
      //}
   }
}
