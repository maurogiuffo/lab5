package Behaviors;

import Interfaces.IBeber;

import java.util.Random;

public class BeberVikingo implements IBeber {
    @Override
    public int Beber(int cantidad) {
        Random r = new Random();
        return r.nextInt(cantidad);
    }
}
