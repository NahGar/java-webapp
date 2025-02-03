package org.ngarcia.webapp.headers.services;

import java.util.List;
import java.util.Optional;
import org.ngarcia.webapp.headers.models.Producto;

public interface ProductoService {
    List<Producto> listar();
    Optional<Producto> findOne(String name);
    List<Producto> findAll(String name);
}
