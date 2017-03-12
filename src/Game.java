import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Game {
    private LinkedList<String> mDequeOfCards = new LinkedList<>();
    private String[] mColores = {"R", "M", "V", "A", "R", "M", "V", "A"};



    public Game() {
        fillCards();
        shuffleCards(mDequeOfCards);
    }

    public void fillCards() {
        for (int i = 0; i < 8; i++) {
            int counter = 1;
            for (int j = 0; j < 9; j++) {
                mDequeOfCards.add(counter + mColores[i]);
            }
        }
    }

    public void shuffleCards(LinkedList<String> list){
        Collections.shuffle(list);
    }


}
