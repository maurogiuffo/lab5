package utnlab5.Models;

public class Game {


    private String PalabraAEncontrar;
    private String PalabraEnmascarada;
    private String LetrasUsadas;
    private int Turno;
    private boolean CanPlay;

    public String getLetrasUsadas() {
        return LetrasUsadas;
    }

    public String getPalabraAEncontrar() {
        return PalabraAEncontrar;
    }


    public Game(String palabra) {
        PalabraAEncontrar = palabra;
        PalabraEnmascarada = "";
        LetrasUsadas = "";

        for (int i = 0; i < palabra.length(); i++) {
            PalabraEnmascarada+= '_';
        }

        Turno = 0;
        CanPlay = true;

        System.out.println("Iniciando Juego: Ahorcado.");
        System.out.println(String.format("Palabra a encontrar: %s (%s letras)", PalabraEnmascarada.toString(),PalabraEnmascarada.length()));

    }

    public boolean Finished()
    {
        return PalabraEnmascarada.compareTo( PalabraAEncontrar ) == 0;
    }



    public synchronized boolean Play (String nombre, char letra){


        //System.out.print(String.format("\n%s esta esperando su turno. Puede jugar: %s", nombre,CanPlay));

        while (!CanPlay) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(String.format("Thread interrupted %s", e));
            }
        }

        boolean success = false;

        //si otro jugador no finalizo el juego
        if(!Finished()) {

            CanPlay = false;

            System.out.print(String.format("\n%s juega con la letra '%s'. ", nombre, letra));

            //se testea la letra
            success = ProbarLetra(letra);

            if (success) {
                System.out.print(String.format("Letra '%s' encontrada: %s", letra, PalabraEnmascarada));

            } else {
                System.out.print(String.format("Letra '%s' no encontrada.", letra));
            }

            CanPlay = true;
        }

        notifyAll();

        return success;

    }


    private boolean ProbarLetra ( char l)
    {

        LetrasUsadas+=l;

        boolean success=false;

        int index = -1;

        do
        {
            index = PalabraAEncontrar.indexOf(l,index+1);

            if(index > -1)
            {
                // se encontro la letra
                success = true;

                //reemplaza la letra en la mascara
                StringBuilder str = new StringBuilder(PalabraEnmascarada);
                str.setCharAt(index, l);

                PalabraEnmascarada = str.toString();

            }
        }
        while (index> -1);

        return success;
    }



    public synchronized void PlayTest ()
    {

        while (!CanPlay) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(String.format("Thread interrupted %s", e));
            }
        }

        CanPlay = false;

       // lo que tenga que hacer
        //

        CanPlay = true;
        notifyAll();

    }



}
