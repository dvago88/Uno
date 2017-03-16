import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class Game {
    private String[] mRawCards = new String[72];
    private Queue<String> mDequeOfCards=new ArrayDeque<>();
    private LinkedList<String> mFirstRival = new LinkedList<>();
    private LinkedList<String> mSecondRival = new LinkedList<>();
    private LinkedList<String> mThirdRival = new LinkedList<>();
    private LinkedList<String> mPlayer = new LinkedList<>();
    private String[] mColores = {"R", "M", "V", "A"};
    private Jugador mJugador;
    private Prompter prompter;

    //constructor:
    //TODO fix the constructor, you shouldn't call methods from here
    public Game(Jugador jugador) {
        prompter = new Prompter(jugador);
        mJugador = jugador;
        fillCards();
        shuffleCards(mRawCards);
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

    public String[] getDequeOfCards() {
        return mRawCards;
    }


    private void fillCards() {
        int j = 0;
        for (int i = 1; i <= 72; i++) {
            mRawCards[i] = i + mColores[j];
            j++;
            if (j == 4) {
                j = 0;
            }
        }
        shuffleCards(mRawCards);

        for(String i:mRawCards){
            mDequeOfCards.add(i);
        }
    }

    public void shuffleCards(String[] cards) {
    }

    public void dealTheCards() {
        int numberOfRivals = prompter.getNumberOfPlayers();
        for (int j = 0; j < 7; j++) {
            mPlayer.add(mDequeOfCards.poll());
        }
        mJugador.setMyCards(mPlayer);
        switch (numberOfRivals) {
            case 1:
                for (int i = 0; i < 7; i++) {
                    mFirstRival.add(mDequeOfCards.poll());
                }
                break;
            case 2:
                for (int i = 0; i < 7; i++) {
                    mFirstRival.add(mDequeOfCards.poll());
                }
                for (int i = 0; i < 7; i++) {
                    mSecondRival.add(mDequeOfCards.poll());
                }
                break;
            case 3:
                for (int i = 0; i < 7; i++) {
                    mFirstRival.add(mDequeOfCards.poll());
                }
                for (int i = 0; i < 7; i++) {
                    mSecondRival.add(mDequeOfCards.poll());
                }
                for (int i = 0; i < 7; i++) {
                    mThirdRival.add(mDequeOfCards.poll());
                }
                break;
        }

    }

    public void play() {
        int playedCard = prompter.playerTurn();

    }


}
