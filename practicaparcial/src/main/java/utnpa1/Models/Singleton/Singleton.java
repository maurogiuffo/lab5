package utnpa1.Models.Singleton;

public class Singleton {
    private static Singleton instance = new Singleton();

    private Singleton(){}

    public static Singleton getInstance() { return instance; }
}
