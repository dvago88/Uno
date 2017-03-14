import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class Game {
    private LinkedList<String> mDequeOfCards = new LinkedList<>();
    private LinkedList<String> mFirstRival = new LinkedList<>();
    private LinkedList<String> mSecondRival = new LinkedList<>();
    private LinkedList<String> mThirdRival = new LinkedList<>();
    private LinkedList<String> mPlayer = new LinkedList<>();
    private String[] mColores = {"R", "M", "V", "A", "R", "M", "V", "A"};
    private Jugador mJugador;
    private Prompter prompter;

    //constructor:
    public Game(Jugador jugador) {
        prompter=new Prompter(jugador);
        mJugador=jugador;
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


    private void fillCards() {
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
        Iterator<String> iterator = mDequeOfCards.listIterator();
        for(int j=0;j<7;j++){
            mPlayer.add(iterator.next());
        }
        mJugador.setMyCards(mPlayer);
        switch (numberOfRivals) {
            case 1:
                for(int i=0;i<7;i++){
                    mFirstRival.add(iterator.next());
                }
                break;
            case 2:
                for(int i=0;i<7;i++){
                    mFirstRival.add(iterator.next());
                }
                for(int i=0;i<7;i++){
                    mSecondRival.add(iterator.next());
                }
                break;
            case 3:
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

    public void play(){
        int playedCard=prompter.playerTurn();

    }


}
