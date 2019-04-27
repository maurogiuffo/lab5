package utnlab5;

import utnlab5.Models.Game;
import utnlab5.Models.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        Game game = new Game( Database.JDBC.getInstance().getWord());

       // Thread master = new Thread(new GameMaster());

        List<Player> playerList = Arrays.asList(
                (new Player( "mauro",5, game )),
                (new Player( "pedro",5, game )),
                (new Player( "juan",5, game ))
        );

        List<Thread> ThreadList = new ArrayList<Thread>();

        for(int i=0; i< playerList.size();i++)
        {
            ThreadList.add(new Thread( playerList.get(i)));
        }

        for(int i=0; i< playerList.size();i++)
        {
            ThreadList.get(i).start();
        }

        // espera a que finalicen todos
        try {
            System.out.println("Waiting for threads to finish.");

            for(int i=0; i< ThreadList.size();i++)
            {
                ThreadList.get(i).join();
            }

        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }

        System.out.print(String.format("\nFin del juego."));

        if(game.Finished()) {

            Player ganador =  playerList.get(0);

            for(int i=1; i< playerList.size();i++)
            {
                Player aux = playerList.get(0);
                if(aux.getAciertos() > ganador.getAciertos())
                    ganador = aux;

            }

            System.out.print(String.format("\nEl Ganador fue %s con %s aciertos",ganador.getNombre(),ganador.getAciertos()));

            Database.JDBC.getInstance().insertResults(ganador.getNombre(),game.getPalabraAEncontrar());

        }
        else
        {
            System.out.print(String.format("\nTodos los jugadores perdieron. La palabra era %s",game.getPalabraAEncontrar()));

        }



    }


}
