package org.ngarcia.webapp.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.*;
import java.util.logging.Logger;

@Logging
@Interceptor
public class LoggingInterceptor {
   
   @Inject
   private Logger log;
   
   @AroundInvoke
   public Object logging(InvocationContext invocation) throws Exception {
      
      log.info(" ***** IN " + invocation.getMethod().getName() + "() " + invocation.getMethod().getDeclaringClass());
      
      Object resultado = invocation.proceed();
      
      log.info(" ***** OUT " + invocation.getMethod().getName());
      
      return resultado;
   }
}
