package org.ngarcia.webapp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/parametros/url-get")
public class ParametrosGetServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        
        String saludo = req.getParameter("saludo");
        String nombre = req.getParameter("nombre");
        
                
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>Parámetros Get de la url</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Parámetros Get de la url</h1>");
        if(saludo != null) {
            out.println("<h2>Saludo: " + saludo + "</h2>");
        }
        if(nombre != null) {
            out.println("<h2>Nombre: " + nombre + "</h2>");
        }
        if(req.getParameter("codigo") != null) {
            Integer codigo = Integer.parseInt(req.getParameter("codigo"));
            out.println("<h2>Codigo: " + codigo + "</h2>");
        }
        
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
    
    
}
