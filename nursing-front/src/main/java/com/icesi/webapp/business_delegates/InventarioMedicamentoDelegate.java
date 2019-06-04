package com.icesi.webapp.business_delegates;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icesi.webapp.LocalDateAdapter;
import com.icesi.webapp.model.AtencionUrgencia;
import com.icesi.webapp.model.InventarioMedicamento;
import com.icesi.webapp.request.RequestModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class InventarioMedicamentoDelegate {

    @Autowired
    RequestModule requestModule;

    public void saveInventory(InventarioMedicamento inventarioMedicamento) throws IOException {
        String extraPath = "save-inventory";
        requestModule.POSTRequest(extraPath, inventarioMedicamento);
    }

    public InventarioMedicamento GetInventoryByID(long id) throws IOException {
        String plusPath = "get-inventory/" + id;
        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        return gson.fromJson(requestModule.GETRequest(plusPath), InventarioMedicamento.class);
    }

    public Iterable<InventarioMedicamento> GetAllInventory() throws IOException {
        String plusPath = "get-inventory";
        return (Iterable<InventarioMedicamento>) new Gson().fromJson(requestModule.GETRequest(plusPath), ArrayList.class);
    }
}
