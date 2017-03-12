import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class Game {
    private LinkedList<String> mDequeOfCards = new LinkedList<>();
    private LinkedList<String> mFirstRival = new LinkedList<>();
    private LinkedList<String> mSecondRival = new LinkedList<>();
    private LinkedList<String> mThirdRival = new LinkedList<>();
    private String[] mColores = {"R", "M", "V", "A", "R", "M", "V", "A"};
    private Prompter prompter=new Prompter();
    private Iterator<String> iterator;

    //constructor:
    public Game() {
        fillCards();
        shuffleCards(mDequeOfCards);
        prompter.howManyPlayers();
        dealTheCards();
    }

    //getters:
    public LinkedList<String> getFirstRival() {
        return mFirstRival;
    }

    public LinkedList<String> getSecondRival() {
        return mSecondRival;
    }

    public LinkedList<String> getThirdRival() {
        return mThirdRival;
    }

    public LinkedList<String> getDequeOfCards() {
        return mDequeOfCards;
    }


    public void fillCards() {
        for (int i = 0; i < 8; i++) {
            int counter = 1;
            for (int j = 0; j < 9; j++) {
                mDequeOfCards.add(counter + mColores[i]);
                counter++;
            }
        }
    }

    public void shuffleCards(LinkedList<String> list) {
        Collections.shuffle(list);
    }

    public void dealTheCards() {
        int numberOfRivals = prompter.getNumberOfPlayers();
        switch (numberOfRivals) {
            case 1:
                iterator=mDequeOfCards.listIterator();
                for(int i=0;i<7;i++){
                    mFirstRival.add(iterator.next());
                }
                break;
            case 2:
                iterator=mDequeOfCards.listIterator();
                for(int i=0;i<7;i++){
                    mFirstRival.add(iterator.next());
                }
                for(int i=0;i<7;i++){
                    mSecondRival.add(iterator.next());
                }
                break;
            case 3:
                iterator=mDequeOfCards.listIterator();
                for(int i=0;i<7;i++){
                    mFirstRival.add(iterator.next());
                }
                for(int i=0;i<7;i++){
                    mSecondRival.add(iterator.next());
                }
                for(int i=0;i<7;i++){
                    mThirdRival.add(iterator.next());
                }
                break;
        }

    }


}
