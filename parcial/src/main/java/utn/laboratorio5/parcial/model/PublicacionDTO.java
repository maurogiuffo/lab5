package utn.laboratorio5.parcial.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicacionDTO {

    private Integer id;

    @NotNull
    private Integer idusuario;

    @NotNull
    private String titulo;

    @NotNull
    private String descripcion;

    @NotNull
    private String foto;

}
