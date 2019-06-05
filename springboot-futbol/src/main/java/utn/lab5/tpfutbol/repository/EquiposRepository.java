package utn.lab5.tpfutbol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.lab5.tpfutbol.model.Equipo;

import java.util.List;

@Repository
public interface EquiposRepository extends JpaRepository<Equipo,Integer> {

    @Query(value = "SELECT * FROM equipo WHERE equipo.nombre like CONCAT('%',:nombre,'%')", nativeQuery = true)
    List<Equipo> findByName(@Param("nombre") String nombre);
}
