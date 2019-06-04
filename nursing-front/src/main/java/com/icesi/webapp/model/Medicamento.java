package com.icesi.webapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Medicamento implements Serializable {

    private static final long serialVersionUID = 1L;

    public final static String VIAS_ORAL = "Oral";
    public final static String VIAS_INTRAVENOSA = "Intravenous";
    public final static String VIAS_TOPICA = "Topical";
    public final static String VIAS_SONDA = "Probe";

    @Id @GeneratedValue(strategy= GenerationType.AUTO) private long id;
    @NonNull private String nombre;
    @NonNull private String nombreGenerico;
    @NonNull private String laboratorio;
    @NonNull private String tipoAdministracion;
    private String indicaciones;
    @OneToMany(cascade = CascadeType.ALL) @JsonManagedReference private List<InventarioMedicamento> inventarioMedicamento;

    public int inventarioTotal(){
        int number = 0;
        for(InventarioMedicamento inventario : inventarioMedicamento)
            number += inventario.getCantidadDisponible();
        return number;
    }
}
