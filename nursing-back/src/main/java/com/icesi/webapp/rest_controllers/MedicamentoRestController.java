package com.icesi.webapp.rest_controllers;

import com.icesi.webapp.model.Medicamento;
import com.icesi.webapp.services.MedicamentoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class MedicamentoRestController {

    @Autowired private MedicamentoService medicamentoService;

    @GetMapping("/get-medicine/{id}")
    public Medicamento GetMedicine(@PathVariable("id") long id){
        Medicamento medicamento = medicamentoService.findByIdMedicamento(id);
        return medicamento;
    }

    @GetMapping("/get-medicine")
    public Iterable<Medicamento> GetMedicines(){
        Iterable<Medicamento> medicamentos = medicamentoService.findAllMedicamentos();
        return medicamentos;
    }

    @GetMapping("/get-medicines-without-expiring")
    public Iterable<Medicamento> GetMedicinesWithoutExpiring(@RequestParam(name = "date") String stringDate){
        Iterable<Medicamento> medicamentos = medicamentoService.filterMedicamentosWithoutExpiring(stringDate);
        return medicamentos;
    }
}
