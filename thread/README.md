# TP Threads - Preguntas

## Diferencias entre Runnable y Thread

- Al Implementar Runnable no estás realmente especializando o modificando el comportamiento del Thread. Solo le estás dando algo al hilo para que se ejecute.
- Java solo admite herencia única, por lo que solo puede extender una clase.
- La creación de una interfaz proporciona una separación más clara el codigo de la clase y la implementación de subprocesos.
- La implementación de Runnable hace que tu clase sea más flexible. Si extiendes Thread, entonces la acción que estás haciendo siempre estará en un thread. 
Sin embargo, si implementas Runnable  se puede ejecutar en un hilo, o pasarlo a algún tipo de servicio de ejecutor, o simplemente pasarlo como una tarea dentro de una aplicación sin subrpocesos.


## Ciclo de vida de un thread

1. Nuevo (New): El thread ha sido creado pero no inicializado, es decir, no se ha ejecutado todavía el método start(). 
Se producirá un mensaje de error (IllegalThreadStateException) si se intenta ejecutar cualquier método de la clase Thread distinto de start().
2. Ejecutable (Runnable): El thread puede estar ejecutándose, siempre y cuando se le haya asignado un determinado tiempo de CPU.
En la práctica puede no estar siendo ejecutado en un instante determinado en beneficio de otro thread.
3. Bloqueado (Blocked o Not Runnable): El thread podría estar ejecutándose, pero hay alguna actividad interna suya que lo impide, como por ejemplo una espera producida por una operación de escritura o lectura de datos por teclado (E/S). Si un thread está en este estado, no se le asigna tiempo de CPU.
4. Muerto (Dead): La forma habitual de que un thread muera es finalizando el método run(). También puede llamarse al método stop() de la clase Thread, aunque dicho método es considerado “peligroso” y no se debe utilizar.


## Meotodos de un thread

- start() y run()
Para poner en marcha este nuevo Thread se debe llamar al método start(), heredado de  la súper-clase Thread, que se encarga de llamar a run(). 
Es importante no confundir el método start() con el método run(). El método run() contiene el código a ser ejecutado “asincrónicamente” en otro thread, mientras que el método start() es el que crea al Thread y en algún punto hace que ese Thread ejecute lo que esta en run(). Este método devuelve el control inmediatamente. Pero si mezclamos todo y ejecutamos directamente el run(), el código se ejecutara en el Thread actual.
El método start() devuelve el control inmediatamente… mientras tanto, el nuevo Thread inicia su recorrido por el método run().

- join()
Si un Thread necesita esperar a que otro termine (por ejemplo el Thread padre espera a que termine el hijo) puede usar el método join().
Crear un proceso es como una bifurcación, se abren 2 caminos, que uno espere a otro es lo contrario, una unificación.
El metodo join() hace que el programa principal espere hasta que este Thread este “muerto”(finalize su ejecucion). 
Este método puede disparar la excepción InterruptedException.

- yield()
Tiene la función de hacer que un hilo que se está ejecutando de regreso al estado en “listo para ejecutar” para permitir que otros hilos de la misma prioridad puedan ejecutarse.
Sin embargo, el funcionamiento de este método (al igual que de los hilos en general) no está garantizado, puede que después de que se establezca un hilo por medio del método yield() a su estado “listo para ejecutar”, éste vuelva a ser elegido para ejecutarse. 
El método yield() nunca causará que un hilo pase a estado de espera/bloqueado/dormido, simplemente pasa de ejecutándose(running) a  “listo para ejecutar”.

- sleep()
Simplemente le dice al Thread que duerma durante los milisegundos específicos. Se debería utilizar sleep() cuando se pretenda retrasar la ejecución del Thread, sleep() no consume recursos del sistema mientras el Thread duerme. De esta forma otros Threads pueden seguir funcionando.

## Fuentes

* https://howtodoinjava.com/java/multi-threading/java-runnable-vs-thread
* http://labojava.blogspot.com/2012/10/manejo-de-threads-metodos.html
