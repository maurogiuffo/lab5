package Models;

import Behaviors.BeberEspartano;
import Behaviors.OrinarEspartano;

public class Espartano extends Humano{

    public int getToleranciaExtra() {
        return ToleranciaExtra;
    }

    private int ToleranciaExtra;

    public Espartano(String nombre, int edad, int peso,  int toleranciaExtra) {
        super(nombre, edad, peso, new BeberEspartano(), new OrinarEspartano());
        ToleranciaExtra = toleranciaExtra;
    }

    public String toString()
    {
        return getNombre();
    }

}
