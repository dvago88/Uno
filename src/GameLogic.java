import java.util.Iterator;
import java.util.LinkedList;

public class GameLogic {
    String mPlayedCard;
    LinkedList<String> mCards;

    public String whatToPlay(String card, LinkedList<String> cards) {
        mPlayedCard = card;
        mCards = cards;
        String whatToPlay;
        char color = mPlayedCard.charAt(1);
        Iterator<String> iterator;
        iterator = mCards.listIterator();

        while (iterator.hasNext()) {
            char aux;
            whatToPlay = iterator.next();
            aux = whatToPlay.charAt(1);
            if (color == aux) {
                iterator.remove();
                return whatToPlay;
            }
        }
        color = mPlayedCard.charAt(0);
        iterator = mCards.listIterator();
        while (iterator.hasNext()) {
            char aux;
            whatToPlay = iterator.next();
            aux = whatToPlay.charAt(0);
            if (color == aux) {
                iterator.remove();
                return whatToPlay;
            }
        }

        return "sin carta";
    }

}
