package Models;

import Interfaces.IBeber;
import Interfaces.IOrinar;


public abstract class Humano {
    private String _nombre;
    private int _edad;

    private IBeber _ibeber;
    private IOrinar _iorinar;

    public void setBeber(IBeber beber){
        _ibeber = beber;
    }

    public void setOrinar(IOrinar orinar){
        _iorinar = orinar;
    }

    public void Beber(int cantidad)
    {
        _ibeber.Beber(cantidad);
    }

    public void Orinar(int cantidad)
    {
        _iorinar.Orinar(cantidad);
    }
}
