package org.ngarcia.webapp.repositories;

import org.ngarcia.webapp.models.Producto;

import java.sql.SQLException;

public interface ProductoRepository extends Repository<Producto> {

   Producto porSku(String sku) throws SQLException;
}
