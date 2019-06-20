package utn.laboratorio5.parcial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.laboratorio5.parcial.model.Comentario;
import utn.laboratorio5.parcial.model.ComentarioUsuarioDTO;

import java.util.List;

@Repository
public interface ComentarioUsuarioRepository extends JpaRepository<ComentarioUsuarioDTO,Integer> {

    String QUERY = "select id,descripcion as comentario from comentario where idusuario= :idusuario ";

    @Query(value = QUERY,nativeQuery = true)
    public List<ComentarioUsuarioDTO> findAllByIdUsuario(@Param("idusuario") Integer idusuario);
}
