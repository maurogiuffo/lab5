package utnpa1.Models.Thread;

public class ThreadDos implements Runnable{

    private Caja box;

    public ThreadDos(Caja pbox)
    {
        box = pbox;
    }

    public void run()
    {
        for (int i=0; i< 10; i++ ) {
            System.out.print( box.Read());
        }
    }

}
