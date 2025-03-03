package org.ngarcia.webapp.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.ngarcia.webapp.models.*;
import org.ngarcia.webapp.services.*;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.*;
import java.util.*;

@WebServlet("/productos/form")
public class ProductoFormServlet extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      Connection conn = (Connection) req.getAttribute("conn");
      ProductoService service = new ProductoServiceJdbcImpl(conn);

      //Al editar producto viene Id en query
      long id;
      try {
         id = Long.parseLong(req.getParameter("id"));
      }
      catch (NumberFormatException e) {
         id = 0L;
      }

      Producto producto = new Producto();
      //para que no sea null la categoría cuando es un producto nuevo y se caiga en la vista
      producto.setCategoria(new Categoria());
      if(id != 0) {
         Optional<Producto> opt = service.findById(id);
         if(opt.isPresent()) {
            producto = opt.get();
         }
      }

      req.setAttribute("categorias",service.listarCategoria());
      req.setAttribute("producto",producto);
      req.setAttribute("titulo","Formulario de producto");

      getServletContext().getRequestDispatcher("/formulario-producto.jsp").forward(req, resp);
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      Connection conn = (Connection) req.getAttribute("conn");
      ProductoService service = new ProductoServiceJdbcImpl(conn);

      String nombre = req.getParameter("nombre");
      Integer precio;
      try {
         precio = Integer.parseInt(req.getParameter("precio"));
      } catch (NumberFormatException e) {
         precio = 0;
      }
      String sku = req.getParameter("sku");
      String fechaStr = req.getParameter("fecha_registro");
      Long categoriaId;
      try {
         categoriaId = Long.valueOf(req.getParameter("categoria"));
      } catch (NumberFormatException e) {
         categoriaId = 0L;
      }
      
      long id;
      try {
         id = Long.parseLong(req.getParameter("id"));
      } catch (NumberFormatException e) {
         id = 0L;
      }

      Map<String,String> errores = new HashMap<>();
      if(nombre == null || nombre.isBlank()) {
         errores.put("nombre","Falta indicar nombre");
      }
      if(sku == null || sku.isBlank()) {
         errores.put("sku","Falta indicar sku");
      }
      else if (sku.length() > 10) {
         errores.put("sku","Sku no debe superar los 10 caracteres");
      }
      else {
         if(id == 0) { //si es un producto nuevo
            Optional<Producto> prodPorSku = service.findBySku(sku);
            if(prodPorSku.isPresent()) {
                errores.put("sku","El producto "+ prodPorSku.get().getNombre()+" tiene ese sku");
            }
         }
      }
      if(fechaStr == null || fechaStr.isBlank()) {
         errores.put("fecha_registro","Falta indicar fecha de registro");
      }
      if(precio.equals(0)) {
         errores.put("precio","Falta indicar precio");
      }
      if(categoriaId.equals(0L)) {
         errores.put("categoria","Falta indicar categoría");
      }

      LocalDate fecha_registro;
      try {
         fecha_registro = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
      } catch (DateTimeParseException e) {
         fecha_registro = null;
      }

      Producto producto = new Producto();
      producto.setId(id);
      producto.setNombre(nombre);
      producto.setPrecio(precio);
      producto.setSku(sku);
      producto.setFechaRegistro(fecha_registro);

      Categoria categoria = new Categoria();
      categoria.setId(categoriaId);
      producto.setCategoria(categoria);

      if(errores.isEmpty()) {

         service.guardar(producto);

         resp.sendRedirect(req.getContextPath() + "/productos");
      }
      else {
         req.setAttribute("errores", errores);
         //doGet(req, resp);

         req.setAttribute("producto", producto);
         req.setAttribute("categorias",service.listarCategoria());
         req.setAttribute("titulo","Formulario de producto");
         getServletContext().getRequestDispatcher("/formulario-producto.jsp").forward(req, resp);
      }
   }
}
