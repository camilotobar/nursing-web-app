package com.icesi.webapp.repositories;

import com.icesi.webapp.model.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface MedicamentoRepository extends CrudRepository<Medicamento, Long> {
    List<Medicamento> findByNombre(String medicamento);
}
