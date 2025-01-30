package org.ngarcia.webapp.tareas.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

//@WebServlet("/")
@WebServlet("/para que no se dispare solo")
public class TareaIndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        Date fechaActual = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMM, yyyy");
        
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        
        String nombre = req.getParameter("nombre");
        String apellidos = req.getParameter("apellidos");
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>Tarea1: Servlet y envío de parámetros</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Tarea1: Servlet y envío de parámetros</h1>");
        if(nombre != null && apellidos != null) {
            out.println("<h2>Nomrbe y apellidos: " + nombre + " " + apellidos + "</h2>");
        }
        else {
            out.println("<h2>Falta indicar nombre y apellidos en la url</h2>");
        }
        
        out.println("<h2>" + sdf.format(fechaActual) + "</h2>");
        
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
