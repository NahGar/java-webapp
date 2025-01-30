package org.ngarcia.webapp.headers.services;

import java.util.Arrays;
import java.util.List;
import org.ngarcia.webapp.headers.models.Producto;

public class ProductoServiceImpl implements ProductoService {

    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1L,"notebook","computacion",175000),
                new Producto(2L,"mesa escritorio","oficina",100000),
                new Producto(3L,"teclado","computacion",50000));
    }
}
