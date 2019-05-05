package utnpa1.Models.ChainOfResponsability;

public class ProcessorUno implements Processor{

    private Processor chain;

    public void setNextProcessor(Processor nextProcessor) {
        this.chain=nextProcessor;
    }

    @Override
    public String process(String text) {
        if (text.indexOf("textoUno") == -1) {
            text+=" textoUno";
        }
        if (chain != null) {
            return chain.process(text);
        }
        else {
            return text;
        }

    }
}
