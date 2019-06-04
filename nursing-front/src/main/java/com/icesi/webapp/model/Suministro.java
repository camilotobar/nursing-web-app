package com.icesi.webapp.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Suministro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy= GenerationType.AUTO) private long id;
    @NonNull private int cantidad;
    @DateTimeFormat(pattern = "yyyy-MM-dd") @NonNull private LocalDate fecha;
    private String observacion;
    @NonNull private String patologia;

    @OneToOne(cascade = CascadeType.ALL ) @NonNull private Paciente paciente;
    @OneToOne(cascade = CascadeType.ALL) @NonNull private Medicamento medicamento;
}
