package utnpa1.Models.Strategy;

import utnpa1.Models.Strategy.IStrategy;

public class ServicioTres {
    IStrategy Calculadora;

    public void setCalculadora(IStrategy calculadora) {
        Calculadora = calculadora;
    }

    public int CalcularResultado()
    {
        return Calculadora.Calculo();
    }
}
