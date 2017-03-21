import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

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
    private GameLogic gameLogic;
    private String mPlayedCard;
    private boolean mGameOver = false;

    //constructor:
    public Game(Jugador jugador) {
        mJugador = jugador;
        mFirstRival = new LinkedList<>();
        mSecondRival = new LinkedList<>();
        mThirdRival = new LinkedList<>();
        mPlayer = new LinkedList<>();
        mDequeOfCards = new ArrayDeque<>();
        gameLogic = new GameLogic();
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

    public String getPlayedCard() {
        return mPlayedCard;
    }

    public boolean isGameOver() {
        return mGameOver;
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
            mJugador.setMyCards(mDequeOfCards.poll());
        }
        mPlayedCard = mDequeOfCards.poll();
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
        String aux;
        int playerTurn = playerTurn();
        if (playerTurn >= 0) {
            mPlayedCard = mJugador.getMyCards().get(playerTurn);
            mJugador.getMyCards().remove(playerTurn);
        } else {
            mJugador.setMyCards(mDequeOfCards.poll());
        }
        switch (mNumberOfRivals) {
            case 1:
                aux = gameLogic.whatToPlay(mPlayedCard, mFirstRival);
                if (mFirstRival.isEmpty()) {
                    mGameOver = true;
                }
                if (!aux.equals("sin carta")) {
                    mPlayedCard = aux;
                    System.out.printf("%nAI1 jugo: %s%n", mPlayedCard);
                } else {
                    mFirstRival.add(mDequeOfCards.poll());
                    System.out.println("");
                    System.out.println("AI1 arrastro carta");
                }
                break;
            case 2:
                aux = gameLogic.whatToPlay(mPlayedCard, mFirstRival);
                if (mFirstRival.isEmpty()) {
                    mGameOver = true;
                }

                if (!aux.equals("sin carta")) {
                    mPlayedCard = aux;
                    System.out.printf("%nAI1 jugo: %s%n", mPlayedCard);
                } else {
                    mFirstRival.add(mDequeOfCards.poll());
                    System.out.println("");
                    System.out.println("AI1 arrastro carta");
                }
                aux = gameLogic.whatToPlay(mPlayedCard, mSecondRival);
                if (mSecondRival.isEmpty()) {
                    mGameOver = true;
                }
                if (!aux.equals("sin carta")) {
                    mPlayedCard = aux;
                    System.out.printf("%nAI2 jugo: %s%n", mPlayedCard);
                } else {
                    mSecondRival.add(mDequeOfCards.poll());
                    System.out.println("");
                    System.out.println("AI2 arrastro carta");
                }
                break;
            case 3:
                aux = gameLogic.whatToPlay(mPlayedCard, mFirstRival);
                if (mFirstRival.isEmpty()) {
                    mGameOver = true;
                }

                if (!aux.equals("sin carta")) {
                    mPlayedCard = aux;
                    System.out.printf("%nAI1 jugo: %s%n", mPlayedCard);
                } else {
                    mFirstRival.add(mDequeOfCards.poll());
                    System.out.println("");
                    System.out.println("AI1 arrastro carta");
                }
                aux = gameLogic.whatToPlay(mPlayedCard, mSecondRival);
                if (mSecondRival.isEmpty()) {
                    mGameOver = true;
                }
                if (!aux.equals("sin carta")) {
                    mPlayedCard = aux;
                    System.out.printf("%nAI2 jugo: %s%n", mPlayedCard);
                } else {
                    mSecondRival.add(mDequeOfCards.poll());
                    System.out.println("");
                    System.out.println("AI2 arrastro carta");
                }
                aux = gameLogic.whatToPlay(mPlayedCard, mThirdRival);
                if (mThirdRival.isEmpty()) {
                    mGameOver = true;
                }
                if (!aux.equals("sin carta")) {
                    mPlayedCard = aux;
                    System.out.printf("%nAI3 jugo: %s%n", mPlayedCard);
                } else {
                    mThirdRival.add(mDequeOfCards.poll());
                    System.out.println("");
                    System.out.println("AI3 arrastro carta");
                }
                break;
        }
    }

    public void howManyPlayers() {
        Scanner scanner = new Scanner(System.in);
        int jugadores;
        do {
            System.out.println("Cuantos rivales?");
            jugadores = scanner.nextInt();
        } while (jugadores < 1 || jugadores > 3);
        mNumberOfRivals = jugadores;
    }

    public int playerTurn() {
        Scanner scanner = new Scanner(System.in);
        int i;
        boolean sameNumber;
        boolean sameLetter;
        do {
            System.out.println("Introduzca el numero de la posicion de su carta o 0 si no tiene carta o desea arrastrar");
            i = scanner.nextInt() - 1;
            sameLetter=false;
            sameNumber=false;
            if(i!=-1){
                sameNumber = mJugador.getMyCards().get(i).charAt(0) != mPlayedCard.charAt(0);
                sameLetter=mJugador.getMyCards().get(i).charAt(1) != mPlayedCard.charAt(1);
            }
        } while (i >= mJugador.getMyCards().size() || (sameNumber && sameLetter));
        return i;
    }


}
