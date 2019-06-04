package com.icesi.webapp.business_delegates;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.google.gson.Gson;
import com.icesi.webapp.model.Paciente;
import com.icesi.webapp.repositories.PacienteRepository;
import com.icesi.webapp.request.RequestModule;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

@Service
public class PacienteBusinessDelegate {

    @Autowired private RequestModule requestModule;

    public void savePaciente(Paciente paciente) throws IOException {
        String plusPath = "save-patient";
        requestModule.GETRequest(plusPath);
    }
    public Paciente findByIdPaciente(String documento) throws IOException {
        String plusPath = "get-patient/" + documento;
        return new Gson().fromJson(requestModule.GETRequest(plusPath), Paciente.class);
    }
    public void removePaciente(Paciente paciente) throws IOException {
        String plusPath = "remove-patient";
        requestModule.GETRequest(plusPath);
    }

    public Iterable<Paciente> findAllPacientes() throws IOException{
        String plusPath = "patients";
        return (Iterable<Paciente>) new Gson().fromJson(requestModule.GETRequest(plusPath), ArrayList.class);
    }
}
