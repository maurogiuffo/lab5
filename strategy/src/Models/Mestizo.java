package Models;

import Behaviors.BeberVikingo;
import Behaviors.OrinarEspartano;
import Behaviors.OrinarVikingo;

public class Mestizo extends Humano{

    public Mestizo(String nombre, int edad, int peso) {
        super(nombre, edad, peso, new BeberVikingo(), new OrinarEspartano());
    }

    public String toString()
    {
        return getNombre();
    }

}
