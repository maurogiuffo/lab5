package utn.lab5.tpfutbol;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import utn.lab5.tpfutbol.model.Jugador;
import utn.lab5.tpfutbol.model.JugadorDTO;

@Component
public class JugadoresModelMapper {


    //ModelMapper converter

    private ModelMapper modelMapper() {
        ModelMapper mapper =new ModelMapper();

        mapper.addMappings(new PropertyMap<Jugador, JugadorDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setNombre(source.getNombre());
                map().setIdequipo(source.getEquipo().getId());
                map().setNombreequipo(source.getEquipo().getNombre());
            }
        });
        return mapper;
    }


    public JugadorDTO convertToDto(Jugador j) {
        JugadorDTO jDTO = modelMapper().map(j, JugadorDTO.class);
        //jDTO.setSubmissionDate(post.getSubmissionDate(),
        //        userService.getCurrentUser().getPreference().getTimezone());
        return jDTO;
    }
}
