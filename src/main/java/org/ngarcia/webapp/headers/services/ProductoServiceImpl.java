package org.ngarcia.webapp.headers.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.ngarcia.webapp.headers.models.Producto;

public class ProductoServiceImpl implements ProductoService {

    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1L,"notebook","computacion",175000),
                new Producto(2L,"mesa escritorio","oficina",100000),
                new Producto(3L,"mesa de cocina","cocina",90000),
                new Producto(4L,"teclado","computacion",50000));
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
}
