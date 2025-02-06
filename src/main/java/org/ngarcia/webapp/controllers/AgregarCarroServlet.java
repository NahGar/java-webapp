package org.ngarcia.webapp.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Optional;
import org.ngarcia.webapp.models.*;
import org.ngarcia.webapp.services.*;

@WebServlet("/agregar-carro")
public class AgregarCarroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        Long id = Long.parseLong(req.getParameter("id"));
        ProductoService service = new ProductoServiceImpl();
        Optional<Producto> producto = service.findById(id);
        if(producto.isPresent()) {
            ItemCarro item = new ItemCarro(1, producto.get());
            Carro carro;
            if(req.getSession().getAttribute("carro") == null) {
                carro = new Carro();
            }
            else {
                carro = (Carro) req.getSession().getAttribute("carro");
            }
            carro.addItem(item);
            req.getSession().setAttribute("carro", carro);
        }
        resp.sendRedirect(req.getContextPath()+"/ver-carro");
    }
    
}
