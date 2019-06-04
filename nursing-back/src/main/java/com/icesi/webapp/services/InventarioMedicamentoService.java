package com.icesi.webapp.services;

import com.icesi.webapp.model.InventarioMedicamento;
import com.icesi.webapp.repositories.InventarioMedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventarioMedicamentoService {

    private InventarioMedicamentoRepository inventarioMedicamentoRepository;

    @Autowired
    public InventarioMedicamentoService(InventarioMedicamentoRepository inventarioMedicamentoRepository) {
        this.inventarioMedicamentoRepository = inventarioMedicamentoRepository;
    }

    public void saveInventario(InventarioMedicamento inventarioMedicamento) {
        inventarioMedicamentoRepository.save(inventarioMedicamento);
    }

    public InventarioMedicamento findByIdInventario(long id) {
        return inventarioMedicamentoRepository.findById(id).get();
    }

    public void removeInventario(InventarioMedicamento inventarioMedicamento) {
        inventarioMedicamentoRepository.delete(inventarioMedicamento);
    }

    public Iterable<InventarioMedicamento> findAllInventarioMedicamento() {
        return inventarioMedicamentoRepository.findAll();
    }

}
