package utnpa1.Models.Facade;

public class Facade {
    private static ServicioUno servicioUno = new ServicioUno();
    private static ServicioDos servicioDos = new ServicioDos();


    public static int Calcular() {
        // Operación muy compleja
        return servicioUno.operacionUno() +  servicioDos.operacionDos();
    }
}
