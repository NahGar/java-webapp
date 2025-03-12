package org.ngarcia.webapp.models;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.*;

import java.util.List;

@RequestScoped
@Named
public class Factura {

   @Inject
   private Cliente cliente;

   private int folio;
   private int numero;
   private String descripcion;

   @Inject
   @Named("crearLineasFactura")
   private List<LineaFactura> lineas;

   @PostConstruct
   public void inicializar() {
      this.folio = 234568;
      this.numero = 6841;
      this.descripcion = "Factura pago";
   }

   public Cliente getCliente() {
      return cliente;
   }

   public void setCliente(Cliente cliente) {
      this.cliente = cliente;
   }

   public int getFolio() {
      return folio;
   }

   public void setFolio(int folio) {
      this.folio = folio;
   }

   public int getNumero() {
      return numero;
   }

   public void setNumero(int numero) {
      this.numero = numero;
   }

   public String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
   }

   public List<LineaFactura> getLineas() {
      return lineas;
   }

   public void setLineas(List<LineaFactura> lineas) {
      this.lineas = lineas;
   }

   @Override
   public String toString() {
      return "Factura{" +
              "cliente=" + cliente +
              ", folio=" + folio +
              ", numero=" + numero +
              ", descripcion='" + descripcion + '\'' +
              ", lineas=" + lineas +
              '}';
   }
}
