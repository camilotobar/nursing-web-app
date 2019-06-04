package com.icesi.webapp.repositories;

import java.util.List;

import com.icesi.webapp.model.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, String> {

}
