package org.ngarcia.webapp.listeners;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.ngarcia.webapp.models.Carro;
import org.ngarcia.webapp.utils.LogUtil;

import java.io.File;

@WebListener
public class AplicacionListener implements ServletContextListener,
        ServletRequestListener, HttpSessionListener{

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("Inicializando la aplicación");
        servletContext = sce.getServletContext();
        servletContext.setAttribute("mensaje","algun valor global de la app");

        // Obtiene la ruta absoluta de la aplicación
        String absolutePath = sce.getServletContext().getRealPath("/");
        // Define la ruta de la carpeta logs
        String logsPath = absolutePath + File.separator + "logs";
        // Inicializa la ruta en la clase LogUtil
        LogUtil.setLogsFolder(logsPath);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servletContext.log("Destruyendo la aplicación");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("Inicializando request");
        sre.getServletRequest().setAttribute("mensaje","algun valor para request");
        sre.getServletRequest().setAttribute("titulo","Catálogo servlet");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        servletContext.log("Destruyendo request");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("Creando la sesión");
        //Carro carro = new Carro();
        //se.getSession().setAttribute("carro",carro);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("Destruyendo la sesión");
    }
}
