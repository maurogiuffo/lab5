package utnpa1.Models.Observer;

import java.util.Observer;
import java.util.Observable;

public class Observador implements Observer {

    private Observado observado ;

    private int valor;

    public int getValor() {
        return valor;
    }

    public Observador(Observado pobservado)
    {
        observado = pobservado;
        observado.addObserver(this);
    }


    public void update(Observable observable, Object object) {

        if (observable != observado)
        {
            throw new IllegalArgumentException();
        }
        valor = (int)object;
    }

}
