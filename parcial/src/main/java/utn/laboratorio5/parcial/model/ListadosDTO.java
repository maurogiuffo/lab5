package utn.laboratorio5.parcial.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ListadosDTO {
    List<IUsuario> usuarios;
    List<IPublicacionDTO> publicaciones;
    List<IComentario> comentarios;
}
