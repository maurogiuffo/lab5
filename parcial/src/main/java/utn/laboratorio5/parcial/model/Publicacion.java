package utn.laboratorio5.parcial.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Publicacion {
    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    private String titulo;


    @NotNull
    private String descripcion;

    @NotNull
    private String foto;


    private LocalDateTime fechaPublicacion;

    private Boolean liked;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JsonBackReference
    private Usuario usuario;


    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST,mappedBy = "publicacion")
    @JsonManagedReference
    private List<Comentario> comentarios;



}
