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

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioDTO {

    private Integer id;

    @NotNull
    private Integer idusuario;

    @NotNull
    private Integer idpublicacion;

    @NotNull
    private String descripcion;

    private LocalDateTime fecha;

}
