package com.icesi.webapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class AtencionUrgencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NonNull
    private LocalDate fecha;
    @NonNull
    private String descripcionAtencion;
    @NonNull
    private String procedimientoRealizado;
    private boolean remitido;
    private String lugarRemision;
    @NonNull
    private String observaciones;

    @OneToOne
    @NonNull
    private Paciente pacienteAtendido;
    @OneToMany(cascade = {CascadeType.ALL})
    @NonNull
    private List<Suministro> medicamentosSuministrados;
}
