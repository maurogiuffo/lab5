package utn.lab5.tpfutbol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.lab5.tpfutbol.model.Jugador;

@Repository
public interface JugadoresRepository extends JpaRepository<Jugador,Integer> {
}
