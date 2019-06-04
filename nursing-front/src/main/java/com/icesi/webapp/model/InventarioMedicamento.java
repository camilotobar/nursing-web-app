package com.icesi.webapp.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class InventarioMedicamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int cantidadDisponible;
    @NonNull
    private String ubicacion;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NonNull
    private LocalDate fechaExpiracion;
    @NonNull
    @ManyToOne
    @JsonBackReference
    Medicamento medicamento;
}
