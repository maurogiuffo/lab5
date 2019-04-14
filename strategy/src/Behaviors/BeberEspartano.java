package Behaviors;

import Interfaces.IBeber;

import java.util.Random;

public class BeberEspartano implements IBeber {
    @Override
    public int Beber(int cantidad) {

        int vasos = 4;
        int totalbebido = 0;

        Random r = new Random();

        for (int i =0 ; i<=vasos;i+=1){
            totalbebido +=  r.nextInt(cantidad/vasos);
        }

        return totalbebido;
    }
}
