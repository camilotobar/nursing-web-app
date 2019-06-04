package com.icesi.webapp.services;

import com.icesi.webapp.model.Paciente;
import com.icesi.webapp.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PacienteService {
    private PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @PostConstruct
    public void createPatients(){

        Paciente paciente = new Paciente("1144102623", "Juan Camilo", "Tobar");
        paciente.setProgramaAcademico("Software Engineering");
        paciente.setDependenciaAcademica("Engineering");
        pacienteRepository.save(paciente);

        paciente = new Paciente("1144143224", "Maria del Mar", "García");
        paciente.setProgramaAcademico("Marketing and Advertising");
        paciente.setDependenciaAcademica("Administrative Sciences");
        pacienteRepository.save(paciente);

        paciente = new Paciente("1012102721", "Juan Fernando", "Jaramillo");
        paciente.setProgramaAcademico("Software Engineering");
        paciente.setDependenciaAcademica("Engineering");
        pacienteRepository.save(paciente);

        paciente = new Paciente("1219401817", "Nathaly", "Ruiz");
        paciente.setProgramaAcademico("Pharmaceutical Chemistry");
        paciente.setDependenciaAcademica("Natural Sciences");
        pacienteRepository.save(paciente);

        paciente = new Paciente("1002142326", "Álvaro David", "Montenegro");
        paciente.setProgramaAcademico("Industrial Engineering");
        paciente.setDependenciaAcademica("Engineering");
        pacienteRepository.save(paciente);

        paciente = new Paciente("1239407712", "Laura Sofía", "Ochoa");
        paciente.setProgramaAcademico("Pharmaceutical Chemistry");
        paciente.setDependenciaAcademica("Natural Sciences");
        pacienteRepository.save(paciente);

        paciente = new Paciente("1112142386", "Manuela", "Rodríguez");
        paciente.setProgramaAcademico("Business Administration");
        paciente.setDependenciaAcademica("Administrative Sciences");
        pacienteRepository.save(paciente);

        paciente = new Paciente("1142132387", "Juan Esteban", "Quinayás");
        paciente.setProgramaAcademico("Telematic Engineering");
        paciente.setDependenciaAcademica("Engineering");
        pacienteRepository.save(paciente);
    }

    public void savePaciente(Paciente paciente) {
        pacienteRepository.save(paciente);
    }
    public Paciente findByIdPaciente(String documento) {
        return pacienteRepository.findById(documento).get();
    }
    public void removePaciente(Paciente paciente) {
        pacienteRepository.delete(paciente);
    }
    public Iterable<Paciente> findAllPacientes() {
        return pacienteRepository.findAll();
    }
}
