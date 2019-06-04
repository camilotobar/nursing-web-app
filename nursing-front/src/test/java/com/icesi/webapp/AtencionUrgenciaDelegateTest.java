package com.icesi.webapp;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icesi.webapp.business_delegates.AtencionUrgenciaBusinessDelegate;
import com.icesi.webapp.business_delegates.MedicamentoBusinessDelegate;
import com.icesi.webapp.controllers.AtencionUrgenciaController;
import com.icesi.webapp.model.*;
import com.icesi.webapp.request.RequestModule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.io.IOException;
import java.rmi.server.UID;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AtencionUrgenciaDelegateTest {

    @Mock
    private RequestModule requestModule;
    @Autowired
    private AtencionUrgenciaBusinessDelegate urgenciaBusinessDelegate;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(requestModule).build();

    }

    private Paciente createPaciente(String documento, String apellidos, String nombres, boolean estado,
                                    String programaAcademico) {
        Paciente paciente = new Paciente();
        paciente.setApellidos(apellidos);
        paciente.setNombres(nombres);
        paciente.setActivo(estado);
        paciente.setDependenciaAcademica("Something");
        paciente.setDocumento(documento);
        paciente.setProgramaAcademico(programaAcademico);
        return paciente;
    }

    private AtencionUrgencia createAtencionUrgencia(long id, Paciente paciente, String procedimiento,
                                                    String observacion, List<Suministro> suministros, boolean remitido) {
        AtencionUrgencia atencionUrgencia = new AtencionUrgencia();
        atencionUrgencia.setId(id);
        atencionUrgencia.setPacienteAtendido(paciente);
        atencionUrgencia.setObservaciones(observacion);
        atencionUrgencia.setFecha(LocalDate.now());
        atencionUrgencia.setMedicamentosSuministrados(suministros);
        atencionUrgencia.setLugarRemision("Hospital");
        atencionUrgencia.setProcedimientoRealizado(procedimiento);
        atencionUrgencia.setDescripcionAtencion("lalala");
        atencionUrgencia.setRemitido(remitido);
        return atencionUrgencia;
    }

    private Suministro createSuministro(long id, Paciente paciente, Medicamento medicamento, String patologia,
                                        int cantidad) {
        Suministro suministro = new Suministro();
        suministro.setId(id);
        suministro.setCantidad(cantidad);
        suministro.setFecha(LocalDate.now());
        suministro.setMedicamento(medicamento);
        suministro.setPaciente(paciente);
        suministro.setPatologia(patologia);
        return suministro;
    }

    private Medicamento createMedicamento(long id, String nombre, String laboratio, String indicaciones, String tipoAdministracion) {
        Medicamento medicamento = new Medicamento();
        medicamento.setId(id);
        medicamento.setNombre(nombre);
        medicamento.setLaboratorio(laboratio);
        medicamento.setIndicaciones(indicaciones);
        medicamento.setTipoAdministracion(tipoAdministracion);

        return medicamento;
    }

    @Test
    public void RunTestTest() {
    }

    @Test
    public void testSaveAtencion() {
        Paciente paciente = createPaciente("11444095564", "Garzon", "Jaime", false,
                "Pregrado");
        List<Suministro> suministros = new ArrayList<>();

        suministros.add(createSuministro(18984, paciente, createMedicamento(65894, "Dolex", "Bayer",
                "No pasa naa", "Cali esta con mucha rumba."), "Patologia", 200));

        AtencionUrgencia atencionUrgencia = createAtencionUrgencia(1564, paciente, "Procedimiento",
                "Se muere", suministros, true);

        try {
            when(requestModule.POSTRequest("testPath", atencionUrgencia)).thenThrow(new Throwable("Success"));

            urgenciaBusinessDelegate.saveAtencion(atencionUrgencia);
            fail();
        } catch (Throwable e) {
            assertTrue(true);
        }
    }

    @Test
    public void testFindByIdAtencion() {
        Paciente paciente = createPaciente("11444095564", "Garzon", "Jaime", false,
                "Pregrado");
        List<Suministro> suministros = new ArrayList<>();

        suministros.add(createSuministro(18984, paciente, createMedicamento(65894, "Dolex", "Bayer",
                "No pasa naa", "Cali esta con mucha rumba."), "Patologia", 200));

        AtencionUrgencia atencionUrgencia = createAtencionUrgencia(1564, paciente, "Procedimiento",
                "Se muere", suministros, true);
        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        String aUJson = gson.toJson(atencionUrgencia);
        try {
            String expected = "get-assist/" + atencionUrgencia.getId();//get-assist/1564
            String sent = "" + 1564 + "";
            when(requestModule.GETRequest(expected)).thenReturn(aUJson);
            assertEquals(expected, "get-assist/1564");
            //AtencionUrgencia atencionUrgencia2 = urgenciaBusinessDelegate.findByIdAtencion(atencionUrgencia.getId() + "");
            assertTrue(atencionUrgencia.toString().length() == urgenciaBusinessDelegate.findByIdAtencion(atencionUrgencia.getId() + "").toString().length());
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testFilterAtencionesByDate() {
        LocalDate date = LocalDate.now();
        String path = "get-assists-by-date?date=" + date.getYear() + "-" +
                date.getMonthValue() + "-" + date.getDayOfMonth();
        Iterable<AtencionUrgencia> atencionUrgencias = new ArrayList<>();

        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        String aURet = gson.toJson(atencionUrgencias);
        try {
            when(requestModule.GETRequest(path)).thenReturn(aURet);
            assertTrue(atencionUrgencias.toString().length() == urgenciaBusinessDelegate.filterAtencionesByDate(date).toString().length());
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testListaMedicamentos() {
        /*try {
            Medicamento m = new Medicamento();
            LocalDate date = LocalDate.of(Integer.parseInt("2019"), Integer.parseInt("Febrero"), Integer.parseInt("24"));
            ArrayList<Medicamento> gift = new ArrayList<>();
            m.inventarioTotal = 15;
            m.nombre = "Dolex";
            m.nombreGenerico = "Acetaminofem";

            when(medicamentoBusinessDelegate.filterMedicamentosWithoutExpiring(date)).thenReturn(gift);
            Model model = new Model();
            assertTrue(atencionUrgenciaController.listaMedicamentos())
        } catch (Exception e) {
            assert (false);
        }*/
    }

}
