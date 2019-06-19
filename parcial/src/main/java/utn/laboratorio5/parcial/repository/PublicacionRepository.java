package utn.laboratorio5.parcial.repository;


import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utn.laboratorio5.parcial.model.IPublicacionDTO;
import utn.laboratorio5.parcial.model.Publicacion;
import utn.laboratorio5.parcial.model.PublicacionDTO;

import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion,Integer> {

    String NATIVE_QUERY = "select p.id idpublicacion,u.nombre as usuario,titulo, count (c.id) as cantidadcomentarios " +
            "from publicacion p " +
            "inner join usuario u on u.id = p.idusuario " +
            "left join comentario c on c.idpublicacion = p.id " +
            "group by p.id, u.nombre ,titulo";

    @Query(value = NATIVE_QUERY , nativeQuery = true)
    public List<IPublicacionDTO> findAllWithUsuario();
}
