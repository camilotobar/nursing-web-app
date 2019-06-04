package com.icesi.webapp.repositories;

import com.icesi.webapp.model.InventarioMedicamento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioMedicamentoRepository extends CrudRepository<InventarioMedicamento, Long> {
}
