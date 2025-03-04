package org.ngarcia.webapp.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;
import org.ngarcia.webapp.models.*;
import org.ngarcia.webapp.services.*;

@WebServlet("/carro/agregar")
public class AgregarCarroServlet extends HttpServlet {

    @Inject
    private Carro carro;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        Long id = Long.parseLong(req.getParameter("id"));
        //ProductoService service = new ProductoServiceImpl();
        Connection conn = (Connection)  req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImpl(conn);
        Optional<Producto> producto = service.findById(id);
        if(producto.isPresent()) {
            ItemCarro item = new ItemCarro(1, producto.get());
            //Se crea carro en AplicacionListener
            /*
            Carro carro;
            if(req.getSession().getAttribute("carro") == null) {
                carro = new Carro();
            }
            else {
                carro = (Carro) req.getSession().getAttribute("carro");
            }
            */
            //se quita por inyección dependencia
            //Carro carro = (Carro) req.getSession().getAttribute("carro");
            carro.addItem(item);
            //req.getSession().setAttribute("carro", carro);
        }
        resp.sendRedirect(req.getContextPath()+"/carro/ver");
    }
    
}
