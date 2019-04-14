import Behaviors.BeberVikingo;
import Behaviors.OrinarEspartano;
import Behaviors.OrinarVikingo;
import Models.Espartano;
import Models.Humano;
import Models.Vikingo;

import java.util.Objects;

import java.util.Arrays;
import java.util.List;

import java.util.Comparator;
import java.util.stream.Collectors;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        // write your code here

        Humano ganadorGeneral = null;
        Humano ganadorEnfrentamiento = null;
        Humano dueñoTaberna = new Humano("Dueño Taberna Copenhage", 50, 120, new BeberVikingo(), new OrinarEspartano());

        //Creo jugadores
        List<Humano> vikingos = CrearVikingos();
        List<Humano> espartanos = CrearEspartanos();

        // determino el orden
        int dni = 30946338;
        boolean par = dni % 2 == 0;

        //ordeno
        vikingos = Ordenar(vikingos, par);
        espartanos = Ordenar(espartanos, par);

        //presento

        System.out.println("Equipo Vikingos:");
        System.out.println(vikingos);
        System.out.println("\nEquipo Espartanos:");
        System.out.println(espartanos);

        // tomo a cualquiera como ganador para despues no comparar contra null
        ganadorGeneral = dueñoTaberna;

        int cantidadJugadores = vikingos.size();

        for (int i = 0; i < cantidadJugadores; i++) {

            //obtengo jugador de cada equipo
            Humano vikingo = ObtenerJugador(vikingos,par);
            vikingos.remove(vikingo);

            Humano espartano = ObtenerJugador(espartanos,par);
            espartanos.remove(espartano);

            System.out.println( String.format( " \nEnfrentamiento %s: %s vs %s \n",i+1,vikingo.getNombre(),espartano.getNombre() ));
            ganadorEnfrentamiento = Enfrentar(vikingo,espartano);

            if(ganadorGeneral.getBebidaEnElCuerpo() < ganadorEnfrentamiento.getBebidaEnElCuerpo() )
            {
                ganadorGeneral = ganadorEnfrentamiento;
            }

            System.out.println( String.format( "Ganador Parcial %s: %s\n" , ganadorEnfrentamiento.getNombre(), ganadorEnfrentamiento.getBebidaEnElCuerpo() ));
        }


        System.out.println( String.format( "Ganador General %s: %s\n" , ganadorGeneral.getNombre(), ganadorGeneral.getBebidaEnElCuerpo() ));

        //enfrentamiento final

        System.out.println( String.format( " \nEnfrentamiento Final: %s vs %s \n",ganadorGeneral.getNombre(),dueñoTaberna.getNombre() ));

        ganadorGeneral.setBebidaEnElCuerpo(0);
        ganadorGeneral = Enfrentar(ganadorGeneral,dueñoTaberna);

        System.out.println( String.format( "\nGanador Final %s: %s" , ganadorGeneral.getNombre(), ganadorGeneral.getBebidaEnElCuerpo() ));


    }


    private static Humano Enfrentar(Humano h1,Humano h2)
    {

        boolean finalizado= false;
        int cantidadATomar =100;

        while (!finalizado)
        {
            // aumenta la cantidad a tomar en cada ronda
            cantidadATomar += 10;

            h1 = Jugar(h1,cantidadATomar);
            h2 = Jugar(h2,cantidadATomar);

            finalizado = h1.getOrino() && h2.getOrino() ;
        }

        System.out.println( String.format( " Total Bebido %s: %s" , h1.getNombre(), h1.getBebidaEnElCuerpo() ));
        System.out.println( String.format( " Total Bebido %s: %s" , h2.getNombre(), h2.getBebidaEnElCuerpo() ));


        if(h1.getBebidaEnElCuerpo() > h2.getBebidaEnElCuerpo())
            return  h1;
        else
            return  h2;


    }

    private static Humano Jugar(Humano h, int cantidad)
    {

        if(h instanceof Vikingo) {
           h.Beber(cantidad + ((Vikingo) h).getBebedorProfesional());
        }
        else {
            h.Beber(cantidad);
        }


        if(h instanceof Espartano) {
            h.Orinar(cantidad - ((Espartano)h).getToleranciaExtra());
        }
        else {
            h.Orinar(cantidad);
        }

        return h;
    }





    private static List<Humano> Ordenar( List<Humano> lista, boolean par)
    {
        if(par )
        {
            lista = lista.stream()
                    .sorted(Comparator.comparing((Humano e) -> e.getEdad()))
                    .collect(Collectors.toList());

        }
        else
        {
            lista = lista.stream()
                    .sorted(Comparator.comparing((Humano e) -> e.getPeso()))
                    .collect(Collectors.toList());

        }

        return lista;
    }


    private static List<Humano> CrearVikingos( )
    {
        return Arrays.asList(
                new Vikingo("Bjorn",35, 80 ,10),
                new Vikingo("Einar",30, 90,15),
                new Vikingo("Sigurd",45, 70,20),
                new Vikingo("Niels",40, 75,30)
        );
    }
    private static List<Humano> CrearEspartanos( )
    {
        return  Arrays.asList(
                new Espartano("Adonis",40,85 ,10),
                new Espartano("Cicero",35,80 ,15),
                new Espartano("Homero",30, 75,20),
                new Espartano("Maximus",45,70 ,30)
        );
    }

    private static Humano ObtenerJugador(List<Humano> lista,boolean par)
    {
        Random r = new Random();
        if(par)
            return lista.get(r.nextInt(lista.size()));
        else
            return lista.get(0);
    }


}
