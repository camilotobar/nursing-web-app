package com.icesi.webapp.rest_controllers;

import com.icesi.webapp.model.Paciente;
import com.icesi.webapp.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
public class PacienteRestController {

    @Autowired private PacienteService pacienteService;

    @GetMapping("/patients")
    public Iterable<Paciente> GetPatients(){
        Iterable<Paciente> pacientes = pacienteService.findAllPacientes();
        return pacientes;
    }

    @GetMapping("/get-patient/{documento}")
    public Paciente GetPatient(@PathVariable("documento") String documento){
        Paciente paciente = pacienteService.findByIdPaciente(documento);
        return paciente;
    }
}
