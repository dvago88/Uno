import java.util.LinkedList;

public class Jugador {
    private LinkedList<String> mMyCards;
    //TODO agregar metodo que lea el nombre del jugador en el prompter
    private String mName;

    public Jugador() {

    }

    public LinkedList<String> getMyCards() {
        return mMyCards;
    }

    public void setMyCards(LinkedList<String> myCards) {
        mMyCards = myCards;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }


}
