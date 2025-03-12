package org.ngarcia.webapp.models;

public class LineaFactura {

   private Integer precio;
   private Integer cantidad;
   private String producto;


   public Integer getPrecio() {
      return precio;
   }

   public void setPrecio(Integer precio) {
      this.precio = precio;
   }

   public Integer getCantidad() {
      return cantidad;
   }

   public void setCantidad(Integer cantidad) {
      this.cantidad = cantidad;
   }

   public String getProducto() {
      return producto;
   }

   public void setProducto(String producto) {
      this.producto = producto;
   }

   public Integer getTotal() {
      return this.cantidad * this.precio;
   }
}
