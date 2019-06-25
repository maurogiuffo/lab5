package utn.laboratorio5.parcial.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class PublicacionDueñoComentarios {
    @Id
    Integer id;
    String publicacion;
    String dueño;
    Integer cantidadcomentarios;
}
