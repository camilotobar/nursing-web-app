package com.icesi.webapp.business_delegates;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icesi.webapp.LocalDateAdapter;
import com.icesi.webapp.model.AtencionUrgencia;
import com.icesi.webapp.request.RequestModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AtencionUrgenciaBusinessDelegate {

    @Autowired private RequestModule requestModule;

    public void saveAtencion(AtencionUrgencia atencion) throws IOException {
        String plusPath = "save-assist";
        requestModule.POSTRequest(plusPath, atencion);
    }

    public AtencionUrgencia findByIdAtencion(String id) throws IOException{
        String plusPath = "get-assist/" + id;
        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        return gson.fromJson(requestModule.GETRequest(plusPath), AtencionUrgencia.class);
    }

    public void removeAtencion(AtencionUrgencia paciente) throws IOException{
        String plusPath = "delete-assist/" + paciente.getId();
        requestModule.GETRequest(plusPath);
    }

    public Iterable<AtencionUrgencia> findAllAtencionesUrgencia() throws IOException{
        String plusPath = "assists";
        return (Iterable<AtencionUrgencia>) new Gson().fromJson(requestModule.GETRequest(plusPath), ArrayList.class);
    }

    public Iterable<AtencionUrgencia> filterAtencionesByDate(LocalDate date) throws IOException {
        String plusPath = "get-assists-by-date?date=" + date.getYear() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth();
        return (Iterable<AtencionUrgencia>)new Gson().fromJson(requestModule.GETRequest(plusPath), ArrayList.class);
    }
}
