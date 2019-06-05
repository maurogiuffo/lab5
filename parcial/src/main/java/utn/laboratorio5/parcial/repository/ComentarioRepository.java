package utn.laboratorio5.parcial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.laboratorio5.parcial.model.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario,Integer> {
}
