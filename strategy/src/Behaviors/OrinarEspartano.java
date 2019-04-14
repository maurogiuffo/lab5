package Behaviors;

import Interfaces.IOrinar;

import java.util.Random;

public class OrinarEspartano implements IOrinar {
    @Override
    public boolean Orinar(int cantidad) {

        Random r = new Random();

        // Espartanos tienen la mitad de posibilidades de orinar
        return r.nextInt(cantidad) > 50 && r.nextInt(cantidad) > 50;
    }
}
