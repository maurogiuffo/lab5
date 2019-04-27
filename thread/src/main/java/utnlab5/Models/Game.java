package utnlab5.Models;

public class Game {


    private String WordToFind;
    private String Mask;
    private String UsedLetters;
    private boolean CanPlay;

    public String getUsedLetters() {
        return UsedLetters;
    }

    public String getWordToFind() {
        return WordToFind;
    }

    public Game(String word) {
        WordToFind = word;
        Mask = "";
        UsedLetters = "";

        for (int i = 0; i < word.length(); i++) {
            Mask += '_';
        }

        CanPlay = false;

        System.out.println("Iniciando Juego: Ahorcado.");
        System.out.println(String.format("Palabra a encontrar: %s (%s letras)", Mask.toString(), Mask.length()));

    }

    public boolean Finished()
    {
        return Mask.compareTo(WordToFind) == 0;
    }



    public synchronized boolean PlayTurn(String nombre, char letra){


        System.out.print(String.format("\n%s esta esperando...", nombre));

        while (!CanPlay) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(String.format("Thread interrupted %s", e));
            }
        }
        System.out.print(String.format("\n%s puede jugar...", nombre));

        boolean success = false;

        //si otro jugador no finalizo el juego
        if(!Finished()) {

            CanPlay = false;

            System.out.print(String.format("\n%s juega con la letra '%s'. ", nombre, letra));

            //se testea la letra
            success = TestLetter(letra);

            if (success) {
                System.out.print(String.format("Letra '%s' encontrada: %s", letra, Mask));

            } else {
                System.out.print(String.format("Letra '%s' no encontrada.", letra));
            }

            //CanPlay = true;


        }

        notifyAll();

        return success;

    }



    public synchronized void NextTurn(){

        while (CanPlay) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(String.format("Thread interrupted %s", e));
            }
        }

        CanPlay = true;

        System.out.print(String.format("\nEl GameMaster habilita al siguiente jugador..."));

        notifyAll();

    }


    private boolean TestLetter(char l)
    {

        UsedLetters +=l;

        boolean success=false;

        int index = -1;

        do
        {
            index = WordToFind.indexOf(l,index+1);

            if(index > -1)
            {
                // se encontro la letra
                success = true;

                //reemplaza la letra en la mascara
                StringBuilder str = new StringBuilder(Mask);
                str.setCharAt(index, l);

                Mask = str.toString();

            }
        }
        while (index> -1);

        return success;
    }


}
