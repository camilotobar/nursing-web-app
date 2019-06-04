package com.icesi.webapp.repositories;

import com.icesi.webapp.model.AtencionUrgencia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtencionUrgenciaRepository extends CrudRepository<AtencionUrgencia, Long> {
}