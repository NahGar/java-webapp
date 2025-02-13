package org.ngarcia.webapp.services;

import java.util.List;
import java.util.Optional;
import org.ngarcia.webapp.models.Producto;

public interface ProductoService {
    List<Producto> listar();
    Optional<Producto> findOne(String name);
    List<Producto> findAll(String name);
    Optional<Producto> findById(Long id);
}
