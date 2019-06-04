package com.icesi.webapp.services;

import com.icesi.webapp.model.AtencionUrgencia;
import com.icesi.webapp.repositories.AtencionUrgenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AtencionUrgenciaService {

    private AtencionUrgenciaRepository atencionUrgenciaRepository;

    @Autowired
    public AtencionUrgenciaService(AtencionUrgenciaRepository atencionUrgenciaRepository) {
        this.atencionUrgenciaRepository = atencionUrgenciaRepository;
    }

    public void saveAtencion(AtencionUrgencia paciente) {
        atencionUrgenciaRepository.save(paciente);
    }

    public AtencionUrgencia findByIdAtencion(long id) {
        return atencionUrgenciaRepository.findById(id).get();
    }

    public void removeAtencion(AtencionUrgencia paciente) {
        atencionUrgenciaRepository.delete(paciente);
    }

    public Iterable<AtencionUrgencia> findAllAtencionesUrgencia() {
        return atencionUrgenciaRepository.findAll();
    }

    public List<AtencionUrgencia> filterAtencionesByDate(LocalDate date) {
        Iterable<AtencionUrgencia> atenciones = atencionUrgenciaRepository.findAll();
        List<AtencionUrgencia> filter = new ArrayList<>();
        for (AtencionUrgencia atencion : atenciones)
            if (atencion.getFecha().isBefore(date))
                filter.add(atencion);

        return filter;
    }
}
