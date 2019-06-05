package utn.lab5.tpfutbol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import utn.lab5.tpfutbol.model.Equipo;
import utn.lab5.tpfutbol.repository.EquiposRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("equipos")
public class EquiposController {

    @Autowired
    EquiposRepository equiposRepository;

    @GetMapping("")
    public List<Equipo> list(@RequestParam(required = false) final String nombre )
    {
        if(Objects.isNull(nombre) || nombre.isEmpty())
            return equiposRepository.findAll();
        else
            return equiposRepository.findByName(nombre);
    }

    @GetMapping("/{id}")
    public Equipo retrieve(@PathVariable final Integer id)
    {
        return equiposRepository.findById(id).orElseThrow( ()-> new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Equipo not found."));
    }

    @PostMapping("")
    public void create(@RequestBody @Valid final Equipo equipo)
    {
        equiposRepository.save(equipo);
    }


    @PutMapping("/{id}")
    public void update(@PathVariable final Integer id, @RequestBody @Valid final Equipo pe)
    {
        Equipo e = equiposRepository.findById(id).orElseThrow( ()-> new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Equipo not found."));
        e.setNombre(pe.getNombre());
        equiposRepository.save(e);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Integer id)
    {
        equiposRepository.deleteById(id);
    }

}
