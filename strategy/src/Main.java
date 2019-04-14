import Behaviors.BeberVikingo;
import Behaviors.OrinarEspartano;
import Database.JDBC;
import Models.Espartano;
import Models.Humano;
import Models.Mestizo;
import Models.Vikingo;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        int dni = 30946338;

        // determino el orden
        boolean par = dni % 2 == 0;

        //Creo jugadores
        List<Humano> vikingos = CrearVikingos();
        List<Humano> espartanos = CrearEspartanos();

        TorneoFrescas torneoFrescas = new TorneoFrescas();

        //ordeno
        vikingos = torneoFrescas.Ordenar(vikingos, par);
        espartanos = torneoFrescas.Ordenar(espartanos, par);

        //presento

        System.out.println("Equipo Vikingos:");
        System.out.println(vikingos);
        System.out.println("\nEquipo Espartanos:");
        System.out.println(espartanos);

        Humano ganadorGeneral = null;

        ganadorGeneral = torneoFrescas.JugarEliminatoria(vikingos,espartanos,par);

        Humano dueñoTaberna = new Mestizo("Dueño Taberna Copenhage", 50, 120);

        ganadorGeneral = torneoFrescas.JugarFinal(ganadorGeneral,dueñoTaberna);

        //Base de datos
        JDBC.getInstance().insertResults(ganadorGeneral);
        JDBC.getInstance().getResults();

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



}
