package utn.laboratorio5.parcial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import utn.laboratorio5.parcial.model.ComentarioDTO;
import utn.laboratorio5.parcial.model.ComentarioUsuarioDTO;
import utn.laboratorio5.parcial.model.IComentario;
import utn.laboratorio5.parcial.model.IPublicacionDTO;
import utn.laboratorio5.parcial.model.IUsuario;
import utn.laboratorio5.parcial.model.ListadosDTO;
import utn.laboratorio5.parcial.model.Publicacion;
import utn.laboratorio5.parcial.model.PublicacionDTO;
import utn.laboratorio5.parcial.model.PublicacionDueñoComentarios;
import utn.laboratorio5.parcial.model.Usuario;
import utn.laboratorio5.parcial.model.UsuarioDTO;
import utn.laboratorio5.parcial.repository.ComentarioRepository;
import utn.laboratorio5.parcial.repository.ComentarioUsuarioRepository;
import utn.laboratorio5.parcial.repository.PublicacionDueñoComentariosRepository;
import utn.laboratorio5.parcial.repository.PublicacionRepository;
import utn.laboratorio5.parcial.repository.UsuarioRepository;
import utn.laboratorio5.parcial.service.PublicacionService;

import javax.validation.Valid;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
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

    @Autowired
    ComentarioUsuarioRepository comentarioUsuarioRepository;

    @Autowired
    PublicacionDueñoComentariosRepository publicacionDueñoComentariosRepository;

    @Autowired
    PublicacionService publicacionService;

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
                       @RequestBody @Valid final UsuarioDTO pu)
    {

        Usuario u = new Usuario();
        u.setNombre(pu.getNombre());
        u.setApellido(pu.getApellido());
        u.setBrowser(userAgent);
        usuarioRepository.save(u);
    }

    @PutMapping("/usuarios/{id}")
    public void update(@PathVariable final Integer id,@RequestBody @Valid final UsuarioDTO pu)
    {
       Usuario u = usuarioRepository.findById(id)
               .orElseThrow( ()-> new HttpClientErrorException(HttpStatus.BAD_REQUEST,"usuario not found"));

       u.setApellido(pu.getApellido());
       u.setNombre(pu.getNombre());
       usuarioRepository.save(u);
    }

    @DeleteMapping("/usuarios/{id}")
    public void delete(@PathVariable final Integer id)
    {
        usuarioRepository.deleteById(id);
    }





    //  publicaciones



    @PostMapping("/publicaciones")
    public void createPublicacion(@RequestBody @Valid final PublicacionDTO pdto)
    {

        Publicacion p = new Publicacion();
        p.setTitulo(pdto.getTitulo());
        p.setDescripcion(pdto.getDescripcion());
        p.setUsuario(usuarioRepository.findById(pdto.getIdusuario()).orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND,"usuario not found")));
        p.setFoto(pdto.getFoto());
        p.setFechaPublicacion(LocalDateTime.now());
        publicacionRepository.save(p);
    }


    @DeleteMapping("/publicaciones/{id}")
    public void deletePublicacion(@PathVariable final Integer id)
    {
        publicacionRepository.deleteById(id);
    }





    //comentarios

    @PostMapping("/comentarios")
    public void createComentario(@RequestBody @Valid final ComentarioDTO cDTO)
    {
        Comentario c = new Comentario();
        c.setDescripcion(cDTO.getDescripcion());
        c.setUsuario(usuarioRepository.findById(cDTO.getIdusuario()).orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND,"usuario not found")));
        c.setPublicacion(publicacionRepository.findById(cDTO.getIdpublicacion()).orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND,"publicacion not found")));
        c.setFecha(LocalDateTime.now());
        comentarioRepository.save(c);
    }

    @DeleteMapping("/comentarios/{id}")
    public void deleteCometario(@PathVariable final Integer id)
    {
        comentarioRepository.deleteById(id);
    }

    @GetMapping("/comentarios/{idusuario}")
    public List<ComentarioUsuarioDTO> listComentariosUsuario(@PathVariable final Integer idusuario)
    {
        return comentarioUsuarioRepository.findAllByIdUsuario(idusuario);
    }

    @GetMapping("/comentarios/")
    public List<ComentarioUsuarioDTO> listComentariosUsuario(@RequestBody final UsuarioDTO usuario)
    {
        return comentarioUsuarioRepository.findAllByNombreorApellido(usuario.getNombre(),usuario.getApellido());
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



    // ThreadPoolExcutor
    @GetMapping("/publicaciones/async")
    public ResponseEntity<?> async()
    {
        Integer result =0;

        CompletableFuture<Integer> result1 = publicacionService.Count("id-1");
        CompletableFuture<Integer> result2 = publicacionService.Count("id-2");
        CompletableFuture<Integer> result3 = publicacionService.Count("id-3");

        CompletableFuture<Integer> result4 = publicacionService.Sum();

        result = result1.join() + result2.join() + result3.join() + result4.join()  ;

        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }






    //*********  parcial 2  ************************************



    @GetMapping("/publicaciones")
    public List<IPublicacionDTO> getPublicaciones() {
        return publicacionRepository.findAllWithUsuario();
    }


    @GetMapping("/publicaciones2")
    public List<PublicacionDueñoComentarios> getPublicaciones2() {
        return publicacionDueñoComentariosRepository.findAllWithUsuario();
    }

    @GetMapping("/allcontent")
    public ResponseEntity<?> allcontent() {


        CompletableFuture<List<IUsuario>> ListaUsuarios = publicacionService.ListaUsuarios();
        CompletableFuture<List<IPublicacionDTO>> ListaPublicaciones = publicacionService.ListaPublicaciones();
        CompletableFuture<List<IComentario>> ListaComentarios = publicacionService.ListaComentarios();

        ListadosDTO listados= new ListadosDTO();

        listados.setUsuarios(ListaUsuarios.join());
        listados.setComentarios(ListaComentarios.join());
        listados.setPublicaciones(ListaPublicaciones.join());

        return ResponseEntity.status(HttpStatus.OK)
                .body(listados);
    }


}
