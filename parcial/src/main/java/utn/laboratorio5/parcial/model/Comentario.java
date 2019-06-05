package utn.laboratorio5.parcial.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Comentario {
    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    private String descripcion;

    @NotNull
    private LocalDateTime fecha;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JsonBackReference
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JsonBackReference
    private Publicacion publicacion;

}
