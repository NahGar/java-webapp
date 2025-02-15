package org.ngarcia.webapp.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.ngarcia.webapp.models.Producto;

public class ProductoServiceImpl implements ProductoService {

    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1L,"notebook",175000),
                new Producto(2L,"mesa escritorio",100000),
                new Producto(3L,"mesa de cocina",90000),
                new Producto(4L,"teclado",50000));
    }

    @Override
    public Optional<Producto> findOne(String name) {
        return  listar().stream()
                .filter( p -> {
                    return name==null || name.isBlank() ? 
                            false : p.getNombre().contains(name);
                }).findFirst();
    }

    @Override
    public List<Producto> findAll(String name) {
        return  listar().stream()
                .filter( p -> {
                    return name==null || name.isBlank() ? 
                            false : p.getNombre().contains(name);
                }).toList();
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return  listar().stream()
                .filter( p -> p.getId().equals(id)).findFirst();
    }
}
