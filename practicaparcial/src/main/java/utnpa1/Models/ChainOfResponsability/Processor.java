package utnpa1.Models.ChainOfResponsability;

public interface Processor {

    void setNextProcessor(Processor nextProcessor);

    public String process(String text);

}
