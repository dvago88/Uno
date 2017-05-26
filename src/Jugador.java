import java.util.TreeSet;
public class Jugador {
    
    private TreeSet<String> mMyCards;
    private String mName;

    public Jugador() {
        mMyCards = new TreeSet<>(new MyComparator());
    }
    
    public TreeSet<String> getMyCards() {
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
