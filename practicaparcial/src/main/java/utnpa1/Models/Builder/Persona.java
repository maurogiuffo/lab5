package utnpa1.Models.Builder;

public class Persona {

    private String nombre;
    private int edad;

    public Persona(Builder builder)
    {
        nombre = builder.getNombre();
        edad = builder.getEdad();
    }


    public static Builder builder() { return new Builder();}

    public static class Builder
    {

        private String nombre;
        private int edad;

        public String getNombre() {return nombre;}

        public int getEdad() { return edad;}

        public Builder nombre(String nombre)
        {
            nombre = nombre;
            return this;
        }

        public Builder edad(int edad)
        {
            edad = edad;
            return this;
        }

        public Persona build()
        {
            return new Persona(this);
        }

    }
}
