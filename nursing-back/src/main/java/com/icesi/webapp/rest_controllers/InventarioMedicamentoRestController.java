package com.icesi.webapp.rest_controllers;

import com.icesi.webapp.model.InventarioMedicamento;
import com.icesi.webapp.services.InventarioMedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class InventarioMedicamentoRestController {

    @Autowired
    private InventarioMedicamentoService inventarioMedicamentoService;

    @PostMapping("/save-inventory")
    public String SaveInventory(@RequestBody InventarioMedicamento inventarioMedicamento) {
        System.out.println("Inventario a guardar: " + inventarioMedicamento);
        inventarioMedicamentoService.saveInventario(inventarioMedicamento);
        return "";
    }

    @GetMapping("/get-inventory/{id}")
    public InventarioMedicamento GetInventory(@PathVariable("id") long id) {
        InventarioMedicamento inventarioMedicamento = inventarioMedicamentoService.findByIdInventario(id);
        System.out.println("Inventario solicitado: " + inventarioMedicamento);
        return inventarioMedicamento;
    }

    @DeleteMapping("/delete-inventory")
    public void DeleteInventory(@RequestBody InventarioMedicamento inventarioMedicamento){
        System.out.println("Inventario a eliminar: " + inventarioMedicamento);
        inventarioMedicamentoService.removeInventario(inventarioMedicamento);
    }

    @GetMapping("/get-inventory")
    public Iterable<InventarioMedicamento> GetAllInventory(){
        System.out.println("Obteniendo todos los inventarios");
        return inventarioMedicamentoService.findAllInventarioMedicamento();
    }

}
