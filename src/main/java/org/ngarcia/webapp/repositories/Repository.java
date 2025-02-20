package org.ngarcia.webapp.repositories;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {

   List<T> listar() throws SQLException;
   T porId(Long id) throws SQLException;
   List<T> porNombre(String nombre) throws SQLException;
   void guardar(T t) throws SQLException;
   void eliminar(Long id) throws SQLException;
   T porSku(String sku) throws SQLException;
}
