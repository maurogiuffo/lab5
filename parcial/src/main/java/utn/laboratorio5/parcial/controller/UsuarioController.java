package utn.laboratorio5.parcial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import utn.laboratorio5.parcial.model.Usuario;
import utn.laboratorio5.parcial.repository.UsuarioRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("")
    public List<Usuario> list()
    {
        return usuarioRepository.findAll();
    }


    @GetMapping("/{id}")
    public Usuario retrieve(@PathVariable final Integer id)
    {
        return usuarioRepository.findById(id)
                .orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND,"usuario not found"));
    }

    @PostMapping(value = "", consumes = "application/json")
    public void create(@RequestBody @Valid final Usuario pu)
    {
        usuarioRepository.save(pu);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable final Integer id,@RequestBody @Valid final Usuario pu)
    {
       // Usuario u = usuarioRepository.findById(id)
       //         .orElseThrow( ()-> new HttpClientErrorException(HttpStatus.BAD_REQUEST,"usuario not found"));

        usuarioRepository.save(pu);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Integer id)
    {
        usuarioRepository.deleteById(id);
    }
}
