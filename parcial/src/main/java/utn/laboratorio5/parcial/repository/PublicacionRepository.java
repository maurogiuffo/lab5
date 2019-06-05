package utn.laboratorio5.parcial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.laboratorio5.parcial.model.Publicacion;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion,Integer> {
}
