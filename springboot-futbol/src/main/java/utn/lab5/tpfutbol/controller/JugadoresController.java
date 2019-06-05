package utn.lab5.tpfutbol.controller;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
import utn.lab5.tpfutbol.JugadoresModelMapper;
import utn.lab5.tpfutbol.model.Equipo;
import utn.lab5.tpfutbol.model.Jugador;
import utn.lab5.tpfutbol.model.JugadorDTO;
import utn.lab5.tpfutbol.repository.EquiposRepository;
import utn.lab5.tpfutbol.repository.JugadoresRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.ws.http.HTTPException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("jugadores")
public class JugadoresController {

    @Autowired
    JugadoresRepository jugadoresRepository;

    @Autowired
    EquiposRepository equiposRepository;

    @Autowired
    JugadoresModelMapper jugadoresModelMapper;

    @GetMapping("")
    public List<JugadorDTO> list(
            @RequestHeader(value="Accept") String accept,
            @RequestHeader(value="Accept-Language") String acceptLanguage,
            @RequestHeader(value="User-Agent", defaultValue="foo") String userAgent,
            HttpServletResponse response)
    {

            System.out.println("accept: " + accept);
            System.out.println("acceptLanguage: " + acceptLanguage);
            System.out.println("userAgent: " + userAgent);

        return jugadoresRepository.findAll().stream().map( p -> jugadoresModelMapper.convertToDto(p)).collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public Jugador retrieve(@PathVariable final Integer id) {

        return jugadoresRepository.findById(id).orElseThrow( ()-> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Jugador not found"));
    }

    @PostMapping("")
    public void create(@RequestBody @Valid final JugadorDTO pj) {

        Jugador j = new Jugador();
        j.setNombre(pj.getNombre());
        j.setEdad(pj.getEdad());
        Equipo e = equiposRepository.findById(pj.getIdequipo()).orElseThrow( ()-> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Equipo not found"));
        j.setEquipo(e);
        jugadoresRepository.save(j);
    }

    @PutMapping("/{id}")
    public void update (@PathVariable final Integer id, @RequestBody @Valid final JugadorDTO pj)
    {
        Jugador j = jugadoresRepository.findById(id).orElseThrow( ()-> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Jugador not found"));
        j.setNombre(pj.getNombre());
        j.setEdad(pj.getEdad());
        Equipo e = equiposRepository.findById(pj.getIdequipo()).orElseThrow( ()-> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Equipo not found"));
        j.setEquipo(e);

        jugadoresRepository.save(j);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable final Integer id)
    {
        jugadoresRepository.deleteById(id);
    }








}
