package com.icesi.webapp.business_delegates;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icesi.webapp.LocalDateAdapter;
import com.icesi.webapp.model.InventarioMedicamento;
import com.icesi.webapp.model.Medicamento;
import com.icesi.webapp.request.RequestModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MedicamentoBusinessDelegate {

    @Autowired
    private RequestModule requestModule;

    public void saveMedicamento(Medicamento paciente) throws IOException {
        String plusPath = "save-patient";
        requestModule.GETRequest(plusPath);
    }

    public Medicamento findByIdMedicamento(long id) throws IOException {
        String plusPath = "get-medicine/" + id;
        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        return gson.fromJson(requestModule.GETRequest(plusPath), Medicamento.class);
    }

    public void removeMedicamento(Medicamento paciente) throws IOException {
        String plusPath = "remove-patient";
        requestModule.GETRequest(plusPath);
    }

    public Iterable<Medicamento> findAllMedicamentos() throws IOException {
        String plusPath = "medicines";
        return (Iterable<Medicamento>) new Gson().fromJson(requestModule.GETRequest(plusPath), ArrayList.class);
    }

    public Iterable<Medicamento> filterMedicamentosWithoutExpiring(LocalDate date) throws IOException {
        String plusPath = "get-medicines-without-expiring?date=" + date.getYear() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth();
        Iterable<Medicamento> medicamentos = (Iterable<Medicamento>) new Gson().fromJson(requestModule.GETRequest(plusPath), ArrayList.class);

//        for(Medicamento medicamento: medicamentos)
//            System.out.println(medicamento.getNombre() + " inv: " + medicamento.getInventarioMedicamento().size());
        return medicamentos;
    }

    public List<String> getNombreMedicamentos() throws IOException {
        Iterable<Medicamento> medicamentos = findAllMedicamentos();
        List<String> names = new ArrayList<>();
        for (Medicamento medicamento : medicamentos)
            names.add(medicamento.getNombre());
        return names;
    }
}
