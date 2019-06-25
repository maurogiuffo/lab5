package utn.laboratorio5.parcial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import utn.laboratorio5.parcial.model.Comentario;
import utn.laboratorio5.parcial.model.IComentario;
import utn.laboratorio5.parcial.model.IPublicacionDTO;
import utn.laboratorio5.parcial.model.IUsuario;
import utn.laboratorio5.parcial.model.Publicacion;
import utn.laboratorio5.parcial.model.Usuario;
import utn.laboratorio5.parcial.repository.ComentarioRepository;
import utn.laboratorio5.parcial.repository.PublicacionRepository;
import utn.laboratorio5.parcial.repository.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PublicacionService {


    @Autowired
    PublicacionRepository publicacionRepository;

    @Autowired
    UsuarioRepository usuarioRepository;


    @Autowired
    ComentarioRepository comentarioRepository;

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<List<IUsuario>> ListaUsuarios()
    {
        return CompletableFuture.completedFuture(usuarioRepository.findNombres());
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<List<IPublicacionDTO>> ListaPublicaciones()
    {
        return CompletableFuture.completedFuture(publicacionRepository.findAllWithUsuario());
    }


    @Async("threadPoolTaskExecutor")
    public CompletableFuture<List<IComentario>> ListaComentarios()
    {
        return CompletableFuture.completedFuture(comentarioRepository.findDescripcion());
    }



    // Tests ******************************

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<Integer> Count(String id)
    {
        System.out.println(String.format( "Count(%s) operation started at %s",id ,LocalDateTime.now()));

        Integer qty=0;

        for (Integer i =0 ; i<1000000; i++) {
            if(toString().contains("0"))
                qty += i.toString().split("0").length;
        }

        System.out.println(String.format( "Count(%s) operation finished at %s",id ,LocalDateTime.now()));

        return CompletableFuture.completedFuture(qty);
    }


    @Async("threadPoolTaskExecutor")
    public CompletableFuture<Integer> Sum()
    {

        System.out.println("Sum operation started at " + LocalDateTime.now());

        Integer sum = 0;
        for (Integer i =0 ; i<1000000; i++) {
            sum +=i;
        }

        System.out.println("Sum operation finished at " + LocalDateTime.now());

        return CompletableFuture.completedFuture(sum);
    }
}
