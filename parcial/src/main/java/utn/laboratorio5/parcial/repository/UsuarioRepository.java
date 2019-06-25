package utn.laboratorio5.parcial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utn.laboratorio5.parcial.model.IPublicacionDTO;
import utn.laboratorio5.parcial.model.IUsuario;
import utn.laboratorio5.parcial.model.Usuario;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    String NATIVE_QUERY = "select nombre from usuario ";

    @Query(value = NATIVE_QUERY , nativeQuery = true)
    public List<IUsuario> findNombres();
}
