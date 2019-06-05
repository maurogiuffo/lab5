package utn.laboratorio5.parcial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import utn.laboratorio5.parcial.model.Comentario;
import utn.laboratorio5.parcial.model.Publicacion;
import utn.laboratorio5.parcial.model.Usuario;
import utn.laboratorio5.parcial.repository.ComentarioRepository;
import utn.laboratorio5.parcial.repository.PublicacionRepository;
import utn.laboratorio5.parcial.repository.UsuarioRepository;

import javax.validation.Valid;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
@PropertySource(value="classpath:application.properties")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PublicacionRepository publicacionRepository;

    @Autowired
    ComentarioRepository comentarioRepository;


    @GetMapping("/usuarios")
    public List<Usuario> getUsuarios()
    {
        return usuarioRepository.findAll();
    }


    @GetMapping("/usuarios/{id}")
    public Usuario retrieve(@PathVariable final Integer id)
    {
        return usuarioRepository.findById(id)
                .orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND,"usuario not found"));
    }

    @PostMapping("/usuarios")
    public void create(@RequestHeader(value="User-Agent", defaultValue="foo") String userAgent,
                       @RequestBody @Valid final Usuario pu)
    {

        pu.setBrowser(userAgent);
        usuarioRepository.save(pu);
    }

    @PutMapping("/usuarios/{id}")
    public void update(@PathVariable final Integer id,@RequestBody @Valid final Usuario pu)
    {
       // Usuario u = usuarioRepository.findById(id)
       //         .orElseThrow( ()-> new HttpClientErrorException(HttpStatus.BAD_REQUEST,"usuario not found"));

        usuarioRepository.save(pu);
    }

    @DeleteMapping("/usuarios/{id}")
    public void delete(@PathVariable final Integer id)
    {
        usuarioRepository.deleteById(id);
    }



    //  publicaciones

    @GetMapping("/publicaciones")
    public List<Publicacion> getPublicaciones() {
        return publicacionRepository.findAll();
    }

    @PostMapping("/publicaciones")
    public void createPublicacion(@RequestBody @Valid final Publicacion p)
    {
        publicacionRepository.save(p);
    }
    //comentarios

    @PostMapping("/comentarios")
    public void createComentario(@RequestBody @Valid final Comentario pc)
    {
        comentarioRepository.save(pc);

    }

    @DeleteMapping("/comentarios/{id}")
    public void deleteCometario(@PathVariable final Integer id)
    {
        comentarioRepository.deleteById(id);
    }


    // autodelete comentarios
    @Value("${autdeletecomentariostime}")
    private Integer seconds;

    @Scheduled(fixedRate = 5000)
    public void autoDeleteCometarios()
    {

        LocalDateTime limitTime = LocalDateTime.now().minus(Duration.ofSeconds(seconds));

        List<Comentario> comentarios = comentarioRepository.findAll()
                .stream().filter(p -> p.getFecha().compareTo(limitTime) == -1 )
                .collect(Collectors.toList());

        if(comentarios.size()>0) {
           // System.out.println(String.format(  "(%s) %s  older than %s will be deleted :" ,LocalDateTime.now(),comentarios.size(), limitTime));

            comentarios.forEach(p ->
            {
               // System.out.println(p.getTime());
                comentarioRepository.delete(p);
            });
        }
    }

}
