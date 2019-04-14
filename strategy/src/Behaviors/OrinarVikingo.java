package Behaviors;

import Interfaces.IOrinar;

import java.util.Random;

public class OrinarVikingo implements IOrinar {
    @Override
    public boolean Orinar(int cantidad) {

        Random r = new Random();

        // Vikingos tienen doble posibilidad de orinar
        return r.nextInt(cantidad) < 50 || r.nextInt(cantidad) < 50;
    }
}