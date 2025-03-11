package org.ngarcia.webapp.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import java.sql.Connection;
import java.util.logging.Logger;
import org.ngarcia.webapp.configs.MysqlConn;
import org.ngarcia.webapp.services.ServiceJdbcException;

@TransactionalJdbc
@Interceptor
public class TransactionalInterceptor {
   
   @Inject
   @MysqlConn
   private Connection conn;
   
   @Inject
   private Logger log;
   
   @AroundInvoke
   public Object transactional(InvocationContext invocation) throws Exception {
      
      if(conn.getAutoCommit()) {
         conn.setAutoCommit(false);
      }
      
      try {
         log.info(" -*-*- Iniciando trn en " + invocation.getMethod().getName());
         Object resultado = invocation.proceed();
         conn.commit();
         log.info(" -*-*- Finalizando trn");
         return resultado;
      }
      catch(ServiceJdbcException e) {
         conn.rollback();
         throw e;
      }
   }
}
