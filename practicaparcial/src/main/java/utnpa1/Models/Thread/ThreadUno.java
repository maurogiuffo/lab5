package utnpa1.Models.Thread;

public class ThreadUno implements Runnable{

    private Caja box;

    public ThreadUno(Caja pbox)
    {
        box = pbox;
    }

    public void run()
    {
        for (int i=0; i< 10; i++ ) {
            box.Write(i);
        }
    }

}
