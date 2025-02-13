package org.ngarcia.webapp.services;

import org.ngarcia.webapp.models.Curso;

import java.util.List;

public interface CursoService {
   List<Curso> listar();
   List<Curso> porNombre(String nombre);
}
