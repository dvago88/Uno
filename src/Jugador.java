import java.util.LinkedList;

public class Jugador {
    private LinkedList<String> mMyCards;
    private String mName;

    public Jugador() {
        mMyCards = new LinkedList<>();
    }

    public LinkedList<String> getMyCards() {
        return mMyCards;
    }

    public void setMyCards(String myCards) {
        mMyCards.add(myCards);
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }


}
