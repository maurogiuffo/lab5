import Models.Espartano;
import Models.Humano;
import Models.Vikingo;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class TorneoFrescas {

    //public


    public List<Humano> Ordenar( List<Humano> lista, boolean par)
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

    public Humano JugarEliminatoria(List<Humano> vikingos, List<Humano> espartanos, boolean par)
    {

        Humano ganadorEnfrentamiento = null;
        Humano ganadorEliminatoria = null;

        // tomo a cualquiera como ganador para despues no comparar contra null
        ganadorEliminatoria = vikingos.get(0);

        int cantidadJugadores = vikingos.size();

        for (int i = 0; i < cantidadJugadores; i++) {

            //obtengo jugador de cada equipo
            Humano vikingo = ObtenerJugador(vikingos,par);
            vikingos.remove(vikingo);

            Humano espartano = ObtenerJugador(espartanos,par);
            espartanos.remove(espartano);

            System.out.println( String.format( " \nEnfrentamiento %s: %s vs %s \n",i+1,vikingo.getNombre(),espartano.getNombre() ));
            ganadorEnfrentamiento = Enfrentar(vikingo,espartano);

            if(ganadorEliminatoria.getBebidaEnElCuerpo() < ganadorEnfrentamiento.getBebidaEnElCuerpo() )
            {
                ganadorEliminatoria = ganadorEnfrentamiento;
            }

            System.out.println( String.format( "Ganador Enfrentamiento %s: %s\n" , ganadorEnfrentamiento.getNombre(), ganadorEnfrentamiento.getBebidaEnElCuerpo() ));
        }

        System.out.println( String.format( "Ganador Eliminatoria %s: %s\n" , ganadorEliminatoria.getNombre(), ganadorEliminatoria.getBebidaEnElCuerpo() ));

        return ganadorEliminatoria;
    }

    public Humano JugarFinal(Humano h1, Humano h2)
    {
        System.out.println( String.format( " \nEnfrentamiento Final: %s vs %s \n",h1.getNombre(),h2.getNombre() ));

        h1.setBebidaEnElCuerpo(0);
        h2.setBebidaEnElCuerpo(0);
        Humano ganador  = Enfrentar(h1,h2);

        System.out.println( String.format( "\nGanador Final %s: %s" , ganador.getNombre(), ganador.getBebidaEnElCuerpo() ));

        return ganador;
    }

    //private
    private Humano Enfrentar(Humano h1,Humano h2)
    {
        h1.setBebidaEnElCuerpo(0);
        h2.setBebidaEnElCuerpo(0);

        boolean finalizado= false;
        int cantidadATomar =50;

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
            if(h1.getBebidaEnElCuerpo() < h2.getBebidaEnElCuerpo())
                return  h2;
            else
            {

                System.out.println( String.format( "Empate: Compiten nuevamente. "));
                return Enfrentar(h1, h2);
            }


    }

    private Humano Jugar(Humano h, int cantidad)
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

    private Humano ObtenerJugador(List<Humano> lista,boolean par)
    {
        Random r = new Random();
        if(par)
            return lista.get(r.nextInt(lista.size()));
        else
            return lista.get(0);
    }

}
