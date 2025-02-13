package org.ngarcia.webapp.models;

public class Curso {

   private Integer id;
   private String nombre;
   private String descripcion;
   private String instructor;
   private float duracion;

   public Curso() {
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getNombre() {
      return nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
   }

   public String getInstructor() {
      return instructor;
   }

   public void setInstructor(String instructor) {
      this.instructor = instructor;
   }

   public float getDuracion() {
      return duracion;
   }

   public void setDuracion(float duracion) {
      this.duracion = duracion;
   }

   @Override
   public String toString() {
      return "Curso:{id:"+id+",nombre="+nombre+"}";
   }
}
