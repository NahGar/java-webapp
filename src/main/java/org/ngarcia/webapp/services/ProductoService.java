package org.ngarcia.webapp.services;

import java.util.List;
import java.util.Optional;

import org.ngarcia.webapp.models.Categoria;
import org.ngarcia.webapp.models.Producto;

public interface ProductoService {

    List<Producto> listar();

    Optional<Producto> findOneByName(String name);

    List<Producto> findAllByName(String name);

    Optional<Producto> findById(Long id);

    void guardar(Producto producto);
    void eliminar(Long id);

    List<Categoria> listarCategoria();
    Optional<Categoria> findByIdCategoria(Long id);

    Optional<Producto> findBySku(String sku);
}
