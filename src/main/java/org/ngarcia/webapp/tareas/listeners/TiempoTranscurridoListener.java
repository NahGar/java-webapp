package org.ngarcia.webapp.tareas.listeners;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServletRequest;

import java.time.*;
import java.util.logging.Logger;

@WebListener
public class TiempoTranscurridoListener implements ServletRequestListener {

   private Instant inicio, fin;
   private static final Logger logger = Logger.getLogger(TiempoTranscurridoListener.class.getName());
   private String recurso;

   @Override
   public void requestInitialized(ServletRequestEvent sre) {

      inicio = Instant.now();

      // Obtener el HttpServletRequest desde el evento
      HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();

      recurso = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
   }

   @Override
   public void requestDestroyed(ServletRequestEvent sre) {
      fin = Instant.now();
      long tiempoTranscurrido = Duration.between(inicio, fin).toMillis();

      //para que no joda
      //logger.info("[[" + recurso + "]] tiempo transcurrido: " + tiempoTranscurrido + " ms");
   }

}
