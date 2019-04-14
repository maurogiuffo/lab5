package Models;

import Interfaces.IBeber;
import Interfaces.IOrinar;


public class Humano {

    private String Nombre;
    private int Edad;
    private int Peso;

    private IBeber iBeber;
    private IOrinar iOrinar;

    private int BebidaEnElCuerpo;
    private boolean Orino;

    public Humano(String nombre, int edad, int peso, IBeber beber, IOrinar orinar) {
        Nombre = nombre;
        Edad = edad;
        Peso = peso;
        iBeber = beber;
        iOrinar = orinar;
        BebidaEnElCuerpo = 0;
        Orino = false;
    }


    public String getNombre() {
        return Nombre;
    }

    public int getEdad() { return Edad;}

    public int getPeso() {  return Peso; }

    public int getBebidaEnElCuerpo() {
        return BebidaEnElCuerpo;
    }

    public void preparar() {
        BebidaEnElCuerpo = 0;
        Orino = false;
    }

    public boolean getOrino() {
        return Orino;
    }


    public void setBeber(IBeber beber) {
        iBeber = beber;
    }

    public void setOrinar(IOrinar orinar) {
        iOrinar = orinar;
    }


    public void Beber(int cantidad)
    {
        BebidaEnElCuerpo += iBeber.Beber(cantidad );
    }

    public void Orinar(int cantidad)
    {
        if(!Orino)
            Orino = iOrinar.Orinar(cantidad);
    }
}
