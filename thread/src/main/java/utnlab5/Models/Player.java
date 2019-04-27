package utnlab5.Models;

import java.util.Random;


public class Player implements Runnable{

    private String Nombre;
    private int Vidas;
    private int Aciertos;
    private Game Game;
    private String Letras;

    public String getNombre() {
        return Nombre;
    }

    public int getAciertos() {
        return Aciertos;
    }

    public Player(String nombre, int vidas, utnlab5.Models.Game game) {
        Nombre = nombre;
        Vidas = vidas;
        Game = game;
        Aciertos =0;
        Letras = "abcdefghijklmnñopqrstuvwxyz";
    }

    public void run() {
        Play();
    }

    private void Play()
    {
        boolean success = false;

        System.out.print(String.format("\n%s Ingresa al juego.", Nombre));

        //mientras tenga vidas y el juego no haya finalizado
        while(Vidas > 0 && !Game.Finished())
        {
            //toma la siguiente letra
            char l = GetLetter();

            success = Game.PlayTurn(Nombre, l);

            if (!success) {
                Vidas--;
            } else {
                Aciertos++;
            }

        }

        if(Vidas ==0)
            System.out.print(String.format("\n%s se quedo sin vidas", Nombre));

    }


    //toma una letra random que no se haya usado en los turnos anteriores
    public char GetLetter()
    {
        char l = 0;

        boolean played = false;
        Random rand = new Random();

        while (!played) {

            l = Letras.toCharArray()[rand.nextInt(Letras.length())];
            //chequea que no se haya usado
            if (Game.getUsedLetters().indexOf(l) == -1) {
                played = true;
            }
        }

        return l;
    }

}
