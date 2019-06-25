package utn.laboratorio5.parcial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utn.laboratorio5.parcial.model.Comentario;
import utn.laboratorio5.parcial.model.IComentario;
import utn.laboratorio5.parcial.model.IUsuario;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario,Integer> {
    String NATIVE_QUERY = "select descripcion from comentario ";

    @Query(value = NATIVE_QUERY , nativeQuery = true)
    public List<IComentario> findDescripcion();
}
