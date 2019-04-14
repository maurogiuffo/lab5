package Models;

import Behaviors.BeberVikingo;
import Behaviors.OrinarVikingo;

public class Vikingo extends Humano{

    public int getBebedorProfesional() {
        return BebedorProfesional;
    }

    private int BebedorProfesional;

    public Vikingo(String nombre, int edad, int peso, int bebedorProfesional) {
        super(nombre, edad, peso, new BeberVikingo(), new OrinarVikingo());
        BebedorProfesional = bebedorProfesional;
    }

    public String toString()
    {
        return getNombre();
    }

}
