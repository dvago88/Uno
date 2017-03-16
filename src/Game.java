import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Game {
    private String[] mRawCards = new String[72];
    private Queue<String> mDequeOfCards = new ArrayDeque<>();
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

    public String[] getRawCards() {
        return mRawCards;
    }

    public Queue<String> getDequeOfCards() {
        return mDequeOfCards;
    }

    private void fillCards() {
        int j = 0;
        int k = 1;
        for (int i = 0; i < 72; i++) {
            mRawCards[i] = k + mColores[j];
            k++;
            j++;
            if (j == 4) {
                j = 0;
            }
            if (k == 10) {
                k = 1;
            }
        }
        shuffleCards(mRawCards);
        for (String i : mRawCards) {
            mDequeOfCards.add(i);
        }
    }

    public void shuffleCards(String[] mRawCards) {
        int i;
        String aux;
        Random random = new Random();
        for (int j = mRawCards.length - 1; j > 0; j--) {
            i = random.nextInt(j + 1);
            aux = mRawCards[i];
            mRawCards[i] = mRawCards[j];
            mRawCards[j] = aux;
        }
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
