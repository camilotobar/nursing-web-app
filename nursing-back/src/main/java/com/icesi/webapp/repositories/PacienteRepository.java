package com.icesi.webapp.repositories;

import com.icesi.webapp.model.Paciente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends CrudRepository<Paciente, String> {
}
