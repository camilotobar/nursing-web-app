package com.icesi.webapp.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @NonNull private String documento;
    @NonNull private String nombres;
    @NonNull private String apellidos;
    private boolean activo;
    private String programaAcademico;
    private String dependenciaAcademica;
}
