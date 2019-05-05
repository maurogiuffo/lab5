package utnpa1.Models.Thread;

public class Caja {
    private boolean empty;
    private int value;

    public Caja() {
        this.empty = true;
        this.value = -1;
    }

    public synchronized int Read()
    {
        while (empty) {
            try{
                wait();
            }
            catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }

        int rValue= value;
        value =0;
        empty = true;

        notifyAll();

        return rValue;
    }

    public synchronized void Write(int pValue)
    {
        while (!empty) {
            try{
                wait();
            }
            catch (InterruptedException e){
                Thread.currentThread().interrupt();

            }
        }

        empty = false;
        value = pValue;
        notifyAll();
    }

}
