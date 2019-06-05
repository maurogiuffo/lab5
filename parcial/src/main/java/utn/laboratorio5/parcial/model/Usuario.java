package utn.laboratorio5.parcial.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    private String nombre;

    @NotNull
    private String apellido;

    @NotNull
    private String browser;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST,mappedBy = "usuario")
    @JsonManagedReference
    private List<Publicacion> publicaciones;

}
