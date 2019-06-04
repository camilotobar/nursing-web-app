package com.icesi.webapp.rest_controllers;

import com.icesi.webapp.model.AtencionUrgencia;
import com.icesi.webapp.services.AtencionUrgenciaService;
import com.icesi.webapp.services.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
public class AtencionUrgenciaRestController {

    @Autowired
    private AtencionUrgenciaService atencionUrgenciaService;

    @PostMapping("/save-assist")
    public String SaveAssist(@RequestBody AtencionUrgencia atencionUrgencia) {


        System.out.println("Atencion a guardar: " + atencionUrgencia);
        atencionUrgenciaService.saveAtencion(atencionUrgencia);
        return "";
    }

    @GetMapping("/get-assist/{id}")
    public AtencionUrgencia GetAssist(@PathVariable("id") String id) {
        long realId = (long) Double.parseDouble(id);
        AtencionUrgencia atencion = atencionUrgenciaService.findByIdAtencion(realId);
        System.out.println("Atencion pedida: " + atencion);
        return atencion;
    }

    @GetMapping("/get-assists-by-date")
    public Iterable<AtencionUrgencia> GetAssistsByDate(@RequestParam(name = "date") String stringDate) {
        String[] dateElements = stringDate.split("-");
        LocalDate formalDate = LocalDate.of(Integer.parseInt(dateElements[0]), Integer.parseInt(dateElements[1]), Integer.parseInt(dateElements[2]));
        Iterable<AtencionUrgencia> atenciones = atencionUrgenciaService.filterAtencionesByDate(formalDate);
        return atenciones;
    }
}
