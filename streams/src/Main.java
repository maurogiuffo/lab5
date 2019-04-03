import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {


    public static void main (String[] args)
    {

        List<Persona> personasList = new ArrayList<>();

        personasList = Arrays.asList(
                new Persona("mauro1",10000001,16),
                new Persona("mauro2",10000002,17),
                new Persona("mauro3",10000003,21),
                new Persona("mauro4",10000004,22),
                new Persona("mauro5",20000001,18),
                new Persona("mauro6",20000002,22)
        );


        // edad > 21
        System.out.println(String.format("Mayores a 21: %s",personasList.stream()
                .filter( p -> p.getEdad() > 21)
                .collect(Collectors.toList())
        ));

        // edad < 18
        System.out.println(String.format("Menores a 18: %s",personasList.stream()
                .filter( p -> p.getEdad() < 18)
                .collect(Collectors.toList())
        ));

        // edad > 21 y DNI > 20.000.000
        System.out.println(String.format("Mayores a 21 y DNI mayor a 20.000.000: %s",personasList.stream()
                .filter( p -> p.getEdad() > 21)
                .filter( p -> p.getDNI() > 20000000)
                .collect(Collectors.toList())
        ));
    }

}
