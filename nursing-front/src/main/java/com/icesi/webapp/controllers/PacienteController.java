package com.icesi.webapp.controllers;

import com.icesi.webapp.business_delegates.PacienteBusinessDelegate;
import com.icesi.webapp.model.AtencionUrgencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;

@Controller
public class PacienteController {

    @Autowired
    private PacienteBusinessDelegate pacienteBD;

    @GetMapping("/pacientes")
    public String listaPacientes(Model model) throws IOException {
        model.addAttribute("patients", pacienteBD.findAllPacientes());
        return "Patients/lista-pacientes";
    }

    @GetMapping("/atender-paciente/{documento}")
    public String atenderPacienteFormulario(@PathVariable("documento") String documento, Model model) throws IOException{
        AtencionUrgencia atencionUrgencia =  new AtencionUrgencia();
        atencionUrgencia.setPacienteAtendido(pacienteBD.findByIdPaciente(documento));
        atencionUrgencia.setMedicamentosSuministrados(new ArrayList<>());
        //System.out.println("ATENCION A REALIZAR: " + atencionUrgencia);
        model.addAttribute("newAssist", atencionUrgencia);
        model.addAttribute("pacienteAAtender", pacienteBD.findByIdPaciente(documento));
        return "Patients/atender-paciente";
    }
}
