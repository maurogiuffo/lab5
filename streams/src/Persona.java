public class Persona {


    private String Nombre;
    private int DNI;
    private int Edad;

    public Persona(String nombre, int DNI, int edad) {
        this.Nombre = nombre;
        this.DNI = DNI;
        this.Edad = edad;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int edad) {
        Edad = edad;
    }

    public String toString() {
        return Nombre;
    }

}
