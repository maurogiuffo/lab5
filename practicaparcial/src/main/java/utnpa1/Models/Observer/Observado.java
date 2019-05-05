package utnpa1.Models.Observer;

import java.util.Observable;

public class Observado extends Observable {

    private int valor;

    public void setValor(int valor) {
        this.valor = valor;
        setChanged();
        notifyObservers(valor);
    }




}
