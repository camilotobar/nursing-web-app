package com.icesi.webapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @NonNull private String login;
    @NonNull private String nombre;
    @NonNull private String apellidos;
    @NonNull private String contrasenha;
    private boolean activo;
}
