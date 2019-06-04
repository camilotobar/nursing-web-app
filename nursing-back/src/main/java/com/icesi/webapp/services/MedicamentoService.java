package com.icesi.webapp.services;

import com.icesi.webapp.model.InventarioMedicamento;
import com.icesi.webapp.model.Medicamento;
import com.icesi.webapp.repositories.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class MedicamentoService {

    private MedicamentoRepository medicamentoRepository;

    @Autowired
    public MedicamentoService(MedicamentoRepository medicamentoRepository) {
        this.medicamentoRepository = medicamentoRepository;
    }

    @PostConstruct
    public void createMedicines() {

        Medicamento medicamento = new Medicamento("Naproxeno", "Naproxeno", "Tecnoquimicas", Medicamento.VIAS_ORAL);
        medicamento.setIndicaciones("1 tablet / 12 hours");
        InventarioMedicamento inventario1 = new InventarioMedicamento(50, "Lote A", LocalDate.of(2019, 12, 1), medicamento);
        InventarioMedicamento inventario2 = new InventarioMedicamento(12, "Lote B", LocalDate.of(2020, 5, 1), medicamento);
        InventarioMedicamento inventario3 = new InventarioMedicamento(28, "Lote C", LocalDate.of(2020, 7, 1), medicamento);
        medicamento.setInventarioMedicamento(new ArrayList<>());
        medicamento.getInventarioMedicamento().add(inventario1);
        medicamento.getInventarioMedicamento().add(inventario2);
        medicamento.getInventarioMedicamento().add(inventario3);
        medicamentoRepository.save(medicamento);

        medicamento = new Medicamento("Diclofenac", "Diclofenac", "Pfizer", Medicamento.VIAS_ORAL);
        inventario1 = new InventarioMedicamento(60, "Lote D", LocalDate.of(2019, 5, 1), medicamento);
        inventario2 = new InventarioMedicamento(100, "Lote E", LocalDate.of(2021, 1, 1), medicamento);
        medicamento.setIndicaciones("2 tablets / 4 hours");
        medicamento.setInventarioMedicamento(new ArrayList<>());
        medicamento.getInventarioMedicamento().add(inventario1);
        medicamento.getInventarioMedicamento().add(inventario2);
        medicamentoRepository.save(medicamento);

        medicamento = new Medicamento("Ranitidina", "Ranitidina", "Tecnoquimicas", Medicamento.VIAS_TOPICA);
        inventario1 = new InventarioMedicamento(5, "Lote F", LocalDate.of(2019, 10, 1), medicamento);
        inventario2 = new InventarioMedicamento(7, "Lote G", LocalDate.of(2021, 1, 1), medicamento);
        inventario3 = new InventarioMedicamento(13, "Lote H", LocalDate.of(2021, 2, 1), medicamento);
        medicamento.setIndicaciones("3 grms / night");
        medicamento.setInventarioMedicamento(new ArrayList<>());
        medicamento.getInventarioMedicamento().add(inventario1);
        medicamento.getInventarioMedicamento().add(inventario2);
        medicamento.getInventarioMedicamento().add(inventario3);
        medicamentoRepository.save(medicamento);

        medicamento = new Medicamento("Dipirona", "Dipirona", "Accord Healthcare", Medicamento.VIAS_INTRAVENOSA);
        medicamento.setIndicaciones("1 mlt / day");
        inventario1 = new InventarioMedicamento(500, "Lote I", LocalDate.of(2022, 2, 1), medicamento);
        medicamento.setInventarioMedicamento(new ArrayList<>());
        medicamento.getInventarioMedicamento().add(inventario1);
        medicamentoRepository.save(medicamento);

        medicamento = new Medicamento("Acetaminofem", "Acetaminofem", "Pfizer", Medicamento.VIAS_ORAL);
        medicamento.setIndicaciones("2 tablets / 8 hours");
        inventario1 = new InventarioMedicamento(370, "Lote J", LocalDate.of(2019, 9, 1), medicamento);
        medicamento.setInventarioMedicamento(new ArrayList<>());
        medicamento.getInventarioMedicamento().add(inventario1);
        medicamentoRepository.save(medicamento);
    }

    public void saveMedicamento(Medicamento paciente) {
        medicamentoRepository.save(paciente);
    }

    public Medicamento findByIdMedicamento(long id) {
        return medicamentoRepository.findById(id).get();
    }

    public void removeMedicamento(Medicamento paciente) {
        medicamentoRepository.delete(paciente);
    }

    public Iterable<Medicamento> findAllMedicamentos() {
        return medicamentoRepository.findAll();
    }

    public List<Medicamento> filterMedicamentosWithoutExpiring(String stringDate) {
        String[] dateElements = stringDate.split("-");
        LocalDate date = LocalDate.of(Integer.parseInt(dateElements[0]), Integer.parseInt(dateElements[1]), Integer.parseInt(dateElements[2]));

        Iterable<Medicamento> medicamentos = medicamentoRepository.findAll();
        List<Medicamento> filter = new ArrayList<>();
        for(Medicamento medicamento : medicamentos){
            boolean pasa = false;
            for (InventarioMedicamento inventario : medicamento.getInventarioMedicamento())
                if (inventario.getFechaExpiracion().isAfter(date))
                    pasa = true;

            if (pasa)
                filter.add(medicamento);
        }

        return filter;
    }

    public List<String> getNombreMedicamentos(){
        Iterable<Medicamento> medicamentos = medicamentoRepository.findAll();
        List<String> names = new ArrayList<>();
        for(Medicamento medicamento : medicamentos)
            names.add(medicamento.getNombre());
        return names;
    }
}
