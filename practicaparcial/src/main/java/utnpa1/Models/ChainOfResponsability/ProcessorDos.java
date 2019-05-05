package utnpa1.Models.ChainOfResponsability;


public class ProcessorDos implements Processor{

    private Processor chain;

    public void setNextProcessor(Processor nextProcessor) {
        this.chain=nextProcessor;
    }

    @Override
    public String process(String text) {

        if (text.indexOf("textoDos") == -1) {
            text+=" textoDos";
        }

        if (chain != null) {
            return chain.process(text);
        }
        else {
            return text;
        }
    }
}
