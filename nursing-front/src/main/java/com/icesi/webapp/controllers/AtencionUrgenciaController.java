package com.icesi.webapp.controllers;

import com.icesi.webapp.business_delegates.AtencionUrgenciaBusinessDelegate;
import com.icesi.webapp.business_delegates.MedicamentoBusinessDelegate;
import com.icesi.webapp.business_delegates.PacienteBusinessDelegate;
import com.icesi.webapp.model.AtencionUrgencia;
import com.icesi.webapp.model.Medicamento;
import com.icesi.webapp.model.Suministro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;


@Controller
public class AtencionUrgenciaController {

    @Autowired
    private AtencionUrgenciaBusinessDelegate atencionUrgenciaBD;

    @Autowired
    private MedicamentoBusinessDelegate medicamentoBD;

    @Autowired
    private PacienteBusinessDelegate pacienteBD;

    @GetMapping("/medicamentos")
    public String listaMedicamentos(@RequestParam(name = "date") String stringDate, Model model) throws IOException {
        String[] dateElements = stringDate.split("-");
        LocalDate formalDate = LocalDate.of(Integer.parseInt(dateElements[0]), Integer.parseInt(dateElements[1]), Integer.parseInt(dateElements[2]));
        model.addAttribute("medicines", medicamentoBD.filterMedicamentosWithoutExpiring(formalDate));
        model.addAttribute("filterDate", LocalDate.of(2020, 5, 4));
        return "Medicines/lista-medicamentos";
    }

    @GetMapping("/atenciones")
    public String listaAtenciones(@RequestParam(name = "date") String stringDate, Model model) throws IOException {
        String[] dateElements = stringDate.split("-");
        LocalDate formalDate = LocalDate.of(Integer.parseInt(dateElements[0]), Integer.parseInt(dateElements[1]), Integer.parseInt(dateElements[2]));
        model.addAttribute("assists", atencionUrgenciaBD.filterAtencionesByDate(formalDate));
        model.addAttribute("filterDate", LocalDate.of(2020, 5, 4));
        return "Assists/lista-atenciones";
    }

    @PostMapping("/atender-paciente")
    public String atenderPaciente(@ModelAttribute(name = "newAssist") @Valid AtencionUrgencia newAssist) throws IOException {
        System.out.println("Paciente Remitido: " + newAssist);
        newAssist.setPacienteAtendido(pacienteBD.findByIdPaciente(newAssist.getPacienteAtendido().getDocumento()));
        atencionUrgenciaBD.saveAtencion(newAssist);
        return "dashboard";
    }

    @GetMapping("/suministros/{id}")
    public String listaSuministros(@PathVariable("id") String id, Model model) throws IOException {
        System.out.println("Add suministros...");
        AtencionUrgencia atencionUrgencia = atencionUrgenciaBD.findByIdAtencion(id);
        return suministrosListaBasica(model, atencionUrgencia);
    }

    @PostMapping("/suministros/{id}")
    public String listaSuministros(@PathVariable("id") String id, @Valid Suministro newSupply, Model model) throws IOException {
        AtencionUrgencia atencionUrgencia = atencionUrgenciaBD.findByIdAtencion(id);
        newSupply.setMedicamento(medicamentoBD.findByIdMedicamento(newSupply.getMedicamento().getId()));
        atencionUrgencia.getMedicamentosSuministrados().add(newSupply);

        System.out.println("POST Suministro a ser guardado: " + newSupply);
        System.out.println("POST Atencion a ser guardado: " + atencionUrgencia);
        atencionUrgenciaBD.saveAtencion(atencionUrgencia);

        return suministrosListaBasica(model, atencionUrgencia);
    }

    private String suministrosListaBasica(Model model, AtencionUrgencia atencionUrgencia) throws IOException {
        Suministro suministro = new Suministro();
        suministro.setMedicamento(new Medicamento());
        suministro.setPaciente(atencionUrgencia.getPacienteAtendido());

        model.addAttribute("patient", atencionUrgencia.getPacienteAtendido());
        model.addAttribute("assist", atencionUrgencia);
        model.addAttribute("supplies", atencionUrgencia.getMedicamentosSuministrados());
        model.addAttribute("newSupply", suministro);
        model.addAttribute("medicinesAvailable", medicamentoBD.findAllMedicamentos());
        return "Assists/lista-suministros";
    }
}
