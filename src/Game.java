import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Vector;

/*
    * R=rojo
    * V=verde
    * A=azul
    * M=amarillo
    * CC=cambio de color
    * BB= cambio de color y arrastra 4
    * S=skip
    * D= arrastra 2
    * E=Reversa
 */
public class Game {

    private LinkedList<String> mDequeOfCards;
    private TreeSet<String> mFirstRival;
    private TreeSet<String> mSecondRival;
    private TreeSet<String> mThirdRival;
    private TreeSet<String> mPlayer;
    private String[] mColores = {"Rb", "Mb", "Vb", "Ab", "Ra", "Ma", "Va", "Aa"};
    private Jugador mJugador;
    private int mNumberOfRivals;
    private GameLogic gameLogic;
    private String mPlayedCard;
    private boolean mGameOver = false;
    private LinkedList<String> mPlayedCards;
    private Vector<TreeSet> mAllPlayers;
    private boolean mDir;                        // true si el juego va hacía la derecha o false si va hacía la inzquierda
    private int Turno;                          // 0 es el jugador, 1, 2 y 3 son la computadora
    private int acumulado;                      //Acumulado de cartas +2 o +4
    private Prompter prompter;

    //constructor:
    public Game(Jugador jugador) {
        mJugador = jugador;
        mFirstRival = new TreeSet<>(new MyComparator());
        mSecondRival = new TreeSet<>(new MyComparator());
        mThirdRival = new TreeSet<>(new MyComparator());
        mDequeOfCards = new LinkedList<>();
        gameLogic = new GameLogic();
        mPlayedCards = new LinkedList<>();
        mAllPlayers = new Vector<>();
        mDir = true;                             //Direccion inicial a la derecha
        Turno = 0;                              //Turno inicial en cero
        acumulado = 0;
        prompter = new Prompter(mJugador);
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
                    mDequeOfCards.add("BB" + m);
                    m++;
                }
                n *= -1;
                k = 1;
            }
        }
        //TODO por que 2 shuffles?
        Collections.shuffle(mDequeOfCards);
    }

    public void howManyPlayers() {
        Scanner scanner = new Scanner(System.in);
        int jugadores;
        System.out.println("");
        do {
            System.out.println("Cuantos rivales?");
            try {
                jugadores = scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                jugadores = 0;
                scanner = new Scanner(System.in);
            }
        } while (jugadores < 1 || jugadores > 3);

        mNumberOfRivals = jugadores;
        if (jugadores == 1) {
            mAllPlayers.add(mFirstRival);
        } else if (jugadores == 2) {
            mAllPlayers.add(mFirstRival);
            mAllPlayers.add(mSecondRival);
        } else if (jugadores == 3) {
            mAllPlayers.add(mFirstRival);
            mAllPlayers.add(mSecondRival);
            mAllPlayers.add(mThirdRival);
        }
    }

    private void cartaInicial() {
        String aux;
        aux = mDequeOfCards.poll();
        while (!Character.isDigit(aux.charAt(0))) {//Mientras no encuentre un numero
            mDequeOfCards.add(aux);
            aux = mDequeOfCards.poll();
        }
        mPlayedCard = aux;
        mPlayedCards.add(mPlayedCard);
        Prompter.showPlayedCard(mPlayedCard);

    }

    public void dealTheCards() {
        //TODO volver a poner el 7 en vez del 2 aca
        for (int j = 0; j < 2; j++) {
            mJugador.setMyCards(mDequeOfCards.poll());
        }

        for (TreeSet<String> treeSet : mAllPlayers) {
            for (int i = 0; i < 7; i++) {
                treeSet.add(mDequeOfCards.poll());
            }
        }
        cartaInicial();
    }

    public void play() {
        // TODO para que mostrar este turno?
        System.out.println("_________________");
        System.out.println("Turno: " + Turno);
        String aux;
        if (Turno == 0) {
            prompter.showPlayerCards();
            String playerTurn = playerTurn();
            if (!playerTurn.equalsIgnoreCase("Arrastro")) {
                mPlayedCards.add(playerTurn);
                playCard(playerTurn);
            } else {
                if (acumulado != 0) {
                    for (int i = 0; i < acumulado; i++) { // Da las cartas acumuladas
                        mJugador.setMyCards(mDequeOfCards.poll());
                    }
                    acumulado = 0;
                } else {
                    mJugador.setMyCards(mDequeOfCards.poll());
                }
                siguienteTurno();
            }
            if (mJugador.getMyCards().size() == 0) {
                mGameOver = true;
                System.out.println("WOOOOOOOOOOOW increible!! le has ganado a la maquina");
                System.out.println("NOTA: has ganado porque eres un genio no porque el sistema merezca menos de 5");
            }

        } else {
            int turnitoAuxiliar = Turno;
            aux = gameLogic.whatToPlay(mPlayedCard, mAllPlayers.get(Turno - 1), acumulado);
            if (!aux.equals("sin carta")) {
                switch (aux.charAt(0)) {//Que ha jugado la maquina?
                    case 'S': {
                        playCard(aux);
                        mPlayedCards.add(aux);
                        break;
                    }
                    case 'E': {
                        playCard(aux);
                        mPlayedCards.add(aux);
                        break;
                    }
                    case 'D': {
                        playCard(aux);
                        mPlayedCards.add(aux);
                        break;
                    }
                    case 'C': {
                        mPlayedCard = aux;
                        mPlayedCards.add(aux.substring(0, 1) + "C" + aux.substring(2, 3));
                        siguienteTurno();
                        Prompter.showNewColor(aux.charAt(1));
                        break;
                    }
                    case 'B': {
                        mPlayedCard = aux;
                        mPlayedCards.add(aux.substring(0, 1) + "C" + aux.substring(2, 3));
                        siguienteTurno();
                        acumulado = acumulado + 4;
                        Prompter.showNewColor(aux.charAt(1));
                        break;
                    }
                    default: {
                        mPlayedCard = aux;
                        mPlayedCards.add(aux);
                        siguienteTurno();
                        break;
                    }
                }
                if (mAllPlayers.get(turnitoAuxiliar - 1).size() == 1) {
                    System.out.println("AI" + turnitoAuxiliar + " grito \"UNO\"");
                }
            } else {// Arrastro
                System.out.print("Arrastro ");
                if (acumulado != 0) {
                    System.out.println(acumulado + " Cartas");
                    for (int i = 0; i < acumulado; i++) { // Da las cartas acumuladas
                        mAllPlayers.get(Turno - 1).add(mDequeOfCards.poll());
                    }
                    acumulado = 0;
                } else {
                    System.out.println("1 Carta");
                    mAllPlayers.get(Turno - 1).add(mDequeOfCards.poll());
                }
                siguienteTurno();
            }
            if (mAllPlayers.get(turnitoAuxiliar - 1).isEmpty()) {
                mGameOver = true;
                Prompter.showPlayedCard(mPlayedCard);
                System.out.println("AI" + turnitoAuxiliar + " ha ganado");
                return;
            }
        }
        if (mPlayedCards.size() > 10) {
            refillTheCards(mPlayedCards);
        }
        Prompter.showPlayedCard(mPlayedCard);
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

    private boolean cardCanPlay(String playedCard, String jPlayedCard) {// Carta en la mesa y posible carta a jugar
        switch (jPlayedCard.charAt(0)) {
            case 'S': { // Skip turn
                return (playedCard.charAt(0) == 'S' || (jPlayedCard.charAt(1) == playedCard.charAt(1)) && acumulado == 0);//si había un skip o si tienen el mismo color y no hay +2 o +4
            }
            case 'C': { // Change color
                return acumulado == 0; //Si no hay +2 o +4
            }
            case 'E': { //Forward
                return (playedCard.charAt(0) == 'E' || (jPlayedCard.charAt(1) == playedCard.charAt(1)) && acumulado == 0);//si había un reverse antes o si tienen el mismo color y no hay +2 o +4
            }
            case 'D': { // +2
                if (playedCard.charAt(0) == 'D') {// Si es otro +2 va derecho
                    return true;
                } else if (playedCard.charAt(0) == 'B' && acumulado != 0) {// Si antes había un +4 para ti
                    return false;
                } else if (playedCard.charAt(1) == jPlayedCard.charAt(1)) {// si tienen el mismo colorW
                    return true;
                }
                return false;
            }
            case 'B': { // +4 & Change color
                if (playedCard.charAt(0) == 'B') {// Si es otro +4 va derecho
                    return true;
                } else if (playedCard.charAt(0) == 'D' && acumulado != 0) {// Si antes había un +2 para ti
                    return false;
                }
                return true;
            }
            default: {// Si es una carta numerica
                int aux = Character.getNumericValue(jPlayedCard.charAt(0));
                if ((aux > 0 && aux < 10) && acumulado == 0) {// Si el valor está entre 0 y 9 y no hay +2 o +4 
                    if (playedCard.charAt(0) == jPlayedCard.charAt(0)) {// si tienen el mismo número 
                        return true;
                    } else if (playedCard.charAt(1) == jPlayedCard.charAt(1)) {// si tienen el mismo color

                        return true;
                    }
                }
                return false;
            }
        }
    }

    private void playCard(String jPlayedCard) {
        Scanner scanner = new Scanner(System.in);
        switch (jPlayedCard.charAt(0)) {
            case 'S': { // Skip turn
                siguienteTurno();// Avanza 2 turnos
                siguienteTurno();
                mPlayedCard = jPlayedCard; // Actualiza carta jugada
                break;
            }
            case 'C': {  // Change color
                String aux;
                //Debe ingresar un color valido
                do {
                    System.out.println("Ingrese el color que desea A = Azul, R = Rojo, V = Verde, M = Amarillo");
                    aux = scanner.next();
                } while (aux.charAt(0) != 'A' && aux.charAt(0) != 'V' && aux.charAt(0) != 'M' && aux.charAt(0) != 'R');
                mPlayedCard = mPlayedCard.substring(0, 1) + aux + mPlayedCard.substring(2, 3);
                Prompter.showNewColor(aux.charAt(0));
                siguienteTurno();
                break;
            }
            case 'E': { // Forward
                mDir = !mDir;
                mPlayedCard = jPlayedCard;
                siguienteTurno();
                break;
            }
            case 'D': { // +2
                acumulado = acumulado + 2;
                mPlayedCard = jPlayedCard;
                siguienteTurno();
                break;
            }
            case 'B': {
                acumulado = acumulado + 4;
                mPlayedCard = jPlayedCard;
                String aux;
                //Debe ingresar un color valido
                do {
                    System.out.println("Ingrese el color que desea A = Azul, R = Rojo, V = Verde, M = Amarillo");
                    aux = scanner.next();
                } while (aux.charAt(0) != 'A' && aux.charAt(0) != 'V' && aux.charAt(0) != 'M' && aux.charAt(0) != 'R');

                mPlayedCard = mPlayedCard.substring(0, 1) + aux + mPlayedCard.substring(2, 3);
                Prompter.showNewColor(aux.charAt(0));
                siguienteTurno();
                break;
            }
            default: {
                mPlayedCard = jPlayedCard;
                siguienteTurno();
                break;
            }
        }
    }

    private void siguienteTurno() {
        if (mDir) { // Si va hacía la derecha
            Turno = (Turno + 1) % (mNumberOfRivals + 1);
        } else { // Si va hacía la izquierda
            if (Turno == 0) {
                Turno = mNumberOfRivals;
            } else {
                Turno = Turno - 1;
            }
        }
    }

    private String playerTurn() {
        Scanner scanner = new Scanner(System.in);
        //boolean uno;
        String aux, aux1;
        do {
            //uno = false;

            if (acumulado != 0) { // Si tiene +2 o +4 pendiente
                System.out.println("Introduzca la carta especial o 0 para arrastrar " + String.valueOf(acumulado) + " cartas");
            } else {
                System.out.println("Introduzca la carta o 0 si no tiene carta o desea arrastrar");
            }

            aux = scanner.nextLine().trim(); // .trim para que obvie espacios

            if (aux.length() == 1 && aux.equals("0")) {//Si introducen el cero
                return "Arrastro";
            }

            if ((aux.length() == 2 || aux.length() == 6) && cardCanPlay(mPlayedCard, aux.substring(0, 2))) {// Si la carta que tiró se puede jugar
                if (mJugador.getMyCards().size() == 2) {//Si solo quedan 2 cartas en el maso del jugadr
                    if (aux.length() == 6 && aux.substring(3, 6).compareToIgnoreCase("UNO") == 0) {// Si la carta viene acompañada de la palabra UNo
                        aux1 = buscarCarta(aux.substring(0, 2));
                        if (!aux1.equals("no esta")) {
                            // TODO para que este uno igual true si nunca se utiliza?
                            //uno = true;
                            return aux1;
                        } /*else {
                            uno = false;
                        }*/
                    } else { // si no dice uno
                        acumulado = acumulado + 2;
                        System.out.println("");
                        System.out.println("No dijiste \"UNO\" arrastra " + acumulado + " cartas, introduce cualquier numero para continuar");
                        scanner.next();
                        return ("Arrastro");
                    }
                } else if (aux.length() == 2) {
                    aux1 = buscarCarta(aux.substring(0, 2));
                    if (!aux1.equals("no esta")) {
                        return aux1;
                    }
                }
            }
            //TODO !uno siempre es true, para que ponerlo?
        } while (/*!uno*/true);
    }

    private String buscarCarta(String Carta) {
        if (mJugador.getMyCards().remove(Carta + "a")) {
            return Carta + "a";
        } else if (mJugador.getMyCards().remove(Carta + "b")) {
            return Carta + "b";
        }
        for (int i = 1; i < 5; i++) {
            if (mJugador.getMyCards().remove(Carta + String.valueOf(i))) {
                return Carta + String.valueOf(i);
            }
        }
        return "no esta";
    }

}