package utn.lab5.tpfutbol.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class JugadorDTO
{
    private Integer id;

    @NotNull
    private String nombre;

    @NotNull
    @Min(value = 18)
    private Integer edad;

    @NotNull
    private Integer idequipo;

    private String nombreequipo;
}