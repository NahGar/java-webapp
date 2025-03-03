package org.ngarcia.webapp.repositories;

import org.ngarcia.webapp.models.Usuario;

import java.sql.SQLException;

public interface UsuarioRepository extends Repository<Usuario> {

   Usuario porUsername(String username) throws SQLException;

}
