import java.util.*;

/*
    * R=rojo
    * V=verde
    * A=azul
    * M=amarillo
    * CC=cambio de color
    * MU= cambio de color y arrastra 4
    * S=skip
    * D= arrastra 2
    * E=Reversa
 */

public class Game {
    private String[] mRawCards = new String[72];
    //TODO: cambiar a lista enlazada esta cola para poder barajar
    private LinkedList<String> mDequeOfCards;
    //TODO: Cambiar a treeset las listas enlazadas de los jugadores
    //TODO: Meter los treeset de los jugadores en un arreglo o en una lista enlazada
    private LinkedList<String> mFirstRival;
    private LinkedList<String> mSecondRival;
    private LinkedList<String> mThirdRival;
    private LinkedList<String> mPlayer;
    private String[] mColores = {"R", "M", "V", "A", "Ra", "Ma", "Va", "Aa"};
    private Jugador mJugador;
    private int mNumberOfRivals;
    private GameLogic gameLogic;
    private String mPlayedCard;
    private boolean mGameOver = false;
    private LinkedList<String> mPlayedCards;

    //constructor:
    public Game(Jugador jugador) {
        mJugador = jugador;
        mFirstRival = new LinkedList<>();
        mSecondRival = new LinkedList<>();
        mThirdRival = new LinkedList<>();
        mPlayer = new LinkedList<>();
        mDequeOfCards = new LinkedList<>();
        gameLogic = new GameLogic();
        mPlayedCards = new LinkedList<>();
    }

    //getters:


    public String getPlayedCard() {
        return mPlayedCard;
    }

    public boolean isGameOver() {
        return mGameOver;
    }

    public void fillCards() {
        int j = 0;
        int k = 1;
        int n = 1;
        int m = 1;
        for (int i = 0; i < 72; i++) {
            mDequeOfCards.add(k + mColores[j]);
            k++;
            j++;
            if (j == 8) {
                j = 0;
            }
            if (k == 10) {
                mDequeOfCards.add("S" + mColores[j]);
                mDequeOfCards.add("D" + mColores[j]);
                mDequeOfCards.add("E" + mColores[j]);
                if (n > 0) {
                    mDequeOfCards.add("CC" + m);
                    mDequeOfCards.add("MU" + m);
                    m++;
                }
                n *= -1;
                k = 1;
            }
        }
        Collections.shuffle(mDequeOfCards);
    }

    public void shuffleCards(String[] rawCards) {
        int i;
        String aux;
        Random random = new Random();
        for (int j = rawCards.length - 1; j > 0; j--) {
            i = random.nextInt(j + 1);
            aux = rawCards[i];
            rawCards[i] = rawCards[j];
            rawCards[j] = aux;
        }
    }

    public void dealTheCards() {
        for (int j = 0; j < 7; j++) {
            mJugador.setMyCards(mDequeOfCards.poll());
        }
        mPlayedCard = mDequeOfCards.poll();
        mPlayedCards.add(mPlayedCard);
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
            mPlayedCards.add(mPlayedCard);
            mJugador.getMyCards().remove(playerTurn);
        } else {
            mJugador.setMyCards(mDequeOfCards.poll());
        }
        if (mJugador.getMyCards().size() > 0) {
            switch (mNumberOfRivals) {
                case 1:
                    aux = gameLogic.whatToPlay(mPlayedCard, mFirstRival);
                    if (!aux.equals("sin carta")) {
                        mPlayedCard = aux;
                        mPlayedCards.add(mPlayedCard);
                        if (mFirstRival.size() == 1) {
                            System.out.println("AI1 grito \"UNO\"");
                        }
                        System.out.printf("%nAI1 jugo: %s%n", mPlayedCard);
                        if (mFirstRival.isEmpty()) {
                            mGameOver = true;
                            System.out.println("AI1 ha ganado");
                            break;
                        }
                    } else {
                        mFirstRival.add(mDequeOfCards.poll());
                        System.out.println("");
                        System.out.println("AI1 arrastro carta");
                    }
                    break;
                case 2:
                    aux = gameLogic.whatToPlay(mPlayedCard, mFirstRival);

                    if (!aux.equals("sin carta")) {
                        mPlayedCard = aux;
                        mPlayedCards.add(mPlayedCard);
                        if (mFirstRival.size() == 1) {
                            System.out.println("AI1 grito \"UNO\"");
                        }
                        System.out.printf("%nAI1 jugo: %s%n", mPlayedCard);
                        if (mFirstRival.isEmpty()) {
                            mGameOver = true;
                            System.out.println("AI1 ha ganado");
                            break;
                        }
                    } else {
                        mFirstRival.add(mDequeOfCards.poll());
                        System.out.println("");
                        System.out.println("AI1 arrastro carta");
                    }
                    aux = gameLogic.whatToPlay(mPlayedCard, mSecondRival);
                    if (!aux.equals("sin carta")) {
                        mPlayedCard = aux;
                        mPlayedCards.add(mPlayedCard);
                        if (mSecondRival.size() == 1) {
                            System.out.println("AI2 grito \"UNO\"");
                        }
                        System.out.printf("%nAI2 jugo: %s%n", mPlayedCard);
                        if (mSecondRival.isEmpty()) {
                            mGameOver = true;
                            System.out.println("AI2 ha ganado");
                            break;
                        }
                    } else {
                        mSecondRival.add(mDequeOfCards.poll());
                        System.out.println("");
                        System.out.println("AI2 arrastro carta");
                    }
                    break;
                case 3:
                    aux = gameLogic.whatToPlay(mPlayedCard, mFirstRival);

                    if (!aux.equals("sin carta")) {
                        mPlayedCard = aux;
                        mPlayedCards.add(mPlayedCard);
                        if (mFirstRival.size() == 1) {
                            System.out.println("AI1 grito \"UNO\"");
                        }
                        System.out.printf("%nAI1 jugo: %s%n", mPlayedCard);
                        if (mFirstRival.isEmpty()) {
                            mGameOver = true;
                            System.out.println("AI1 ha ganado");
                            break;
                        }
                    } else {
                        mFirstRival.add(mDequeOfCards.poll());
                        System.out.println("");
                        System.out.println("AI1 arrastro carta");
                    }
                    aux = gameLogic.whatToPlay(mPlayedCard, mSecondRival);
                    if (!aux.equals("sin carta")) {
                        mPlayedCard = aux;
                        mPlayedCards.add(mPlayedCard);
                        if (mSecondRival.size() == 1) {
                            System.out.println("AI2 grito \"UNO\"");
                        }
                        System.out.printf("%nAI2 jugo: %s%n", mPlayedCard);
                        if (mSecondRival.isEmpty()) {
                            mGameOver = true;
                            System.out.println("AI2 ha ganado");
                            break;
                        }
                    } else {
                        mSecondRival.add(mDequeOfCards.poll());
                        System.out.println("");
                        System.out.println("AI2 arrastro carta");
                    }
                    aux = gameLogic.whatToPlay(mPlayedCard, mThirdRival);
                    if (!aux.equals("sin carta")) {
                        mPlayedCard = aux;
                        mPlayedCards.add(mPlayedCard);
                        if (mThirdRival.size() == 1) {
                            System.out.println("AI3 grito \"UNO\"");
                        }
                        System.out.printf("%nAI3 jugo: %s%n", mPlayedCard);
                        if (mThirdRival.isEmpty()) {
                            mGameOver = true;
                            System.out.println("AI3 ha ganado");
                            break;
                        }
                    } else {
                        mThirdRival.add(mDequeOfCards.poll());
                        System.out.println("");
                        System.out.println("AI3 arrastro carta");
                    }
                    break;
            }
            if (mPlayedCards.size() > 10) {
                refillTheCards(mPlayedCards);
            }
        } else {
            mGameOver = true;
            System.out.println("WOOOOOOOOOOOW increible!! le has ganado a la maquina");
            System.out.println("NOTA: has ganado porque eres un genio no porque el sistema merezca menos de 5");
        }
    }

    private void refillTheCards(LinkedList<String> playedCards) {
        Iterator<String> iterator;
        Collections.shuffle(playedCards);
        iterator = playedCards.listIterator();
        while (iterator.hasNext()) {
            mDequeOfCards.add(iterator.next());
        }
        mPlayedCards.clear();
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
        String aux;
        boolean sameNumber;
        boolean sameLetter;
        do {
            System.out.println("Introduzca el numero de la posicion de su carta o 0 si no tiene carta o desea arrastrar");
            aux = scanner.nextLine().trim();
            if (aux.length() == 5 || aux.length() == 1) {
                i = Character.getNumericValue(aux.charAt(0)) - 1;//esto es necesario hacerlo para leer el UNO
            } else {
                i = (Character.getNumericValue(aux.charAt(0)) * 10 + Character.getNumericValue(aux.charAt(1))) - 1;
            }
            sameLetter = false;
            sameNumber = false;
            if (i != -1 && !(i >= mJugador.getMyCards().size())) {
                if (mJugador.getMyCards().size() == 2) {
                    if (aux.length() < 5) {
                        System.out.println("No dijiste \"UNO\" arrastra 2 cartas, introduce cualquier numero para continuar");
                        String garbage = scanner.next();
                        mJugador.setMyCards(mDequeOfCards.poll());
                        mJugador.setMyCards(mDequeOfCards.poll());
                    }
                }
                sameNumber = mJugador.getMyCards().get(i).charAt(0) != mPlayedCard.charAt(0);
                sameLetter = mJugador.getMyCards().get(i).charAt(1) != mPlayedCard.charAt(1);
            }
        } while (i >= mJugador.getMyCards().size() || (sameNumber && sameLetter));
        return i;
    }


}
