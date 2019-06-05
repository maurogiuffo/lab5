package utn.lab5.tpfutbol.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class JugadorDTO
{
    private Integer id;

    @NotNull
    private String nombre;

    @NotNull
    private Integer idequipo;

    private String nombreequipo;
}