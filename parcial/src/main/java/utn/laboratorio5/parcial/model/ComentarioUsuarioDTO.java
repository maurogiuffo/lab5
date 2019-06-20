package utn.laboratorio5.parcial.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class ComentarioUsuarioDTO {
    //String usuario;
    @Id
    Integer id;
    String comentario;
}
