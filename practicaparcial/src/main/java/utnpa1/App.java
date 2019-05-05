package utnpa1;

import utnpa1.Models.Builder.Persona;

import utnpa1.Models.ChainOfResponsability.ProcessorDos;
import utnpa1.Models.ChainOfResponsability.ProcessorUno;
import utnpa1.Models.Facade.Facade;
import utnpa1.Models.Observer.Observado;
import utnpa1.Models.Observer.Observador;
import utnpa1.Models.Singleton.Singleton;
import utnpa1.Models.Strategy.ServicioTres;
import utnpa1.Models.Strategy.StrategyBehaviorDos;
import utnpa1.Models.Thread.Caja;
import utnpa1.Models.Thread.ThreadUno;
import utnpa1.Models.Thread.ThreadDos;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        //Singleton
        Singleton.getInstance();

        //Builder
        Persona john = Persona.builder()
                .nombre("John")
                .edad(25)
                .build();

        //Facade
        System.out.println(Facade.Calcular());

        //Strategy
        ServicioTres serv3 = new ServicioTres();

        serv3.setCalculadora(new StrategyBehaviorDos());

        System.out.println(serv3.CalcularResultado());

        //Observer
        Observado observado = new Observado();

        Observador observador = new Observador(observado);

        observado.setValor(10);
        System.out.println(observador.getValor());


        //Thread
        Caja box = new Caja();

        Thread t1= new Thread( new ThreadUno(box));
        Thread t2= new Thread( new ThreadDos(box));

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        }
        catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }

        //ChainOfResponsability

        ProcessorUno p1 = new ProcessorUno();
        ProcessorDos p2 = new ProcessorDos();
        p1.setNextProcessor(p2);

        System.out.println("\n" + p1.process("test"));


    }
}
