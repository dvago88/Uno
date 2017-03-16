import java.util.*;

public class Game {
    private String[] mRawCards = new String[72];
    private Queue<String> mDequeOfCards;
    private LinkedList<String> mFirstRival;
    private LinkedList<String> mSecondRival;
    private LinkedList<String> mThirdRival;
    private LinkedList<String> mPlayer;
    private String[] mColores = {"R", "M", "V", "A"};
    private Jugador mJugador;
    private int mNumberOfRivals;

    //constructor:
    //TODO fix the constructor, you shouldn't call methods from here
    public Game(Jugador jugador) {
        mJugador = jugador;
        mFirstRival = new LinkedList<>();
        mSecondRival = new LinkedList<>();
        mThirdRival = new LinkedList<>();
        mPlayer = new LinkedList<>();
        mDequeOfCards = new ArrayDeque<>();

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

    public void fillCards() {
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

        for (int j = 0; j < 7; j++) {
            mPlayer.add(mDequeOfCards.poll());
        }
        mJugador.setMyCards(mPlayer);
        switch (mNumberOfRivals) {
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


    }

    public void howManyPlayers() {
        Scanner scanner=new Scanner(System.in);
        int jugadores;
        do {
            System.out.println("Cuantos rivales?");
            jugadores = scanner.nextInt();
        } while (jugadores < 1 || jugadores > 3);
        mNumberOfRivals =jugadores;
    }


}
