
import java.util.*;

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
    private boolean Dir; // true si el juego va hacía la derecha o false si va hacía la inzquierda
    private int Turno; // 0 es el jugador, 1, 2 y 3 son la computadora
    private int acumulado; //Acumulado de cartas +2 o +4
    private int mReversado;
    Prompter prompter;

    //constructor:
    public Game(Jugador jugador) {
        mJugador = jugador;
        mFirstRival = new TreeSet<>(new MyComparator());
        mSecondRival = new TreeSet<>(new MyComparator());
        mThirdRival = new TreeSet<>(new MyComparator());
        mPlayer = new TreeSet<>();
        mDequeOfCards = new LinkedList<>();
        gameLogic = new GameLogic();
        mPlayedCards = new LinkedList<>();
        mAllPlayers = new Vector<>();
        Dir = true;
        Turno = 0;
        acumulado = 0;
        mReversado = 0;
        prompter  = new Prompter(mJugador);
    }

    //getters:
    public String getPlayedCard() {
        return mPlayedCard;
    }
    
    public boolean isGameOver() {
        return mGameOver;
    }

    public int getTurn() {
        return Turno;
    }

    public void setTurn(int turn) {
        Turno = turn;
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
        Collections.shuffle(mDequeOfCards);
    }

    public void dealTheCards() {
        for (int j = 0; j < 7; j++) {
            mJugador.setMyCards(mDequeOfCards.poll());
        }
        mPlayedCard = mDequeOfCards.poll();
        mPlayedCards.add(mPlayedCard);

        for (TreeSet<String> treeSet : mAllPlayers) {
            for (int i = 0; i < 7; i++) {
                treeSet.add(mDequeOfCards.poll());
            }
        }
    }

    /*
    public void play() {
        String aux;
        int playerTurn = playerTurn();
        if (playerTurn >= 0) {
            mPlayedCard = mJugador.getMyCards().get(playerTurn);
            mPlayedCards.add(mPlayedCard);
            mJugador.getMyCards().remove(playerTurn);
            if (mPlayedCard.charAt(0) == 'E') {
                mReversado *= -1;
            }
        } else {
            mJugador.setMyCards(mDequeOfCards.poll());
        }
        if (mJugador.getMyCards().size() > 0) {
            int n = 1;
            int contador = 0;
            int reverser = 1;
            if (mReversado < 0) {
                contador = mNumberOfRivals - 1;
                reverser = -1;
                n = 3;
            }
            while (contador < 3 && contador >= 0) {
                aux = gameLogic.whatToPlay(mPlayedCard, mAllPlayers.get(contador));
                if (!aux.equals("sin carta")) {
                    mPlayedCard = aux;
                    mPlayedCards.add(mPlayedCard);
                    if (mAllPlayers.get(contador).size() == 1) {
                        System.out.println("AI" + n + " grito \"UNO\"");
                    }
                    System.out.printf("%nAI%d jugo: %s%n", n, mPlayedCard.substring(0, 2));
                    if (mAllPlayers.get(contador).isEmpty()) {
                        mGameOver = true;
                        System.out.println("AI" + n + " ha ganado");
                        break;
                    }
                    if (aux.charAt(0) == 'E') {
                        mReversado *= -1;
                        reverser *= -1;
                    }
                } else {
                    mAllPlayers.get(contador).add(mDequeOfCards.poll());
                    System.out.println("");
                    System.out.println("AI" + n + " arrastro carta");
                }
                n += reverser;
                contador += reverser;
            }
            if (mPlayedCards.size() > 10) {
                refillTheCards(mPlayedCards);
            }
        } else {
            mGameOver = true;
            System.out.println("WOOOOOOOOOOOW increible!! le has ganado a la maquina");
            System.out.println("NOTA: has ganado porque eres un genio no porque el sistema merezca menos de 5");
        }
    }*/
    //TODO: como los strings de las cartas tienen 3 valores hay que modificar este metodo para que lea las cartas asi, pero solo muestre el numero y la letra.
    public void play() {
        Prompter.showPlayedCard(mPlayedCard);
        String aux;
        if (Turno == 0) {
            prompter.showPlayerCards();
            String playerTurn = playerTurn();
            if (!playerTurn.equalsIgnoreCase("Arrastro")) {
                if (playerTurn.charAt(0) == 'S') {

                }
                mPlayedCard = playerTurn;
                mPlayedCards.add(mPlayedCard);
            } else {
                mJugador.setMyCards(mDequeOfCards.poll());
            }
        }
        if(mJugador.getMyCards().size() > 0){
            
        }

        if (mJugador.getMyCards().size() > 0) {
            int n = 1;
            for (TreeSet<String> treeSet : mAllPlayers) {
                aux = gameLogic.whatToPlay(mPlayedCard, treeSet);
                if (!aux.equals("sin carta")) {
                    mPlayedCard = aux;
                    mPlayedCards.add(mPlayedCard);
                    if (treeSet.size() == 1) {
                        System.out.println("AI" + n + " grito \"UNO\"");
                    }
                    System.out.printf("%nAI%d jugo: %s%n", n, mPlayedCard.substring(0, 2));
                    if (treeSet.isEmpty()) {
                        mGameOver = true;
                        System.out.println("AI" + n + " ha ganado");
                        break;
                    }
                } else {
                    treeSet.add(mDequeOfCards.poll());
                    System.out.println("");
                    System.out.println("AI" + n + " arrastro carta");
                }
                n++;
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

    public boolean cardCanPlay(String playedCard, String jPlayedCard) {
        switch (jPlayedCard.charAt(0)) {
            case 'S': { // Skip turn
                return playedCard.charAt(0) == 'S' || (jPlayedCard.charAt(1) == playedCard.charAt(1) && acumulado == 0);//si había un skip o si tienen el mismo color y no hay +2 o +4
            }
            case 'C': { // Change color
                return acumulado == 0; //Si no hay +2 o +4
            }
            case 'E': { //Forward
                return playedCard.charAt(0) == 'E' || (jPlayedCard.charAt(1) == playedCard.charAt(1) && acumulado == 0);//si había un reverse antes o si tienen el mismo color y no hay +2 o +4
            }
            case 'D': { // +2
                if (playedCard.charAt(0) == 'D') {// Si es otro +2 va derecho
                    return true;
                } else if (playedCard.charAt(0) == 'B' && acumulado != 0) {// Si antes había un +4 para ti
                    return false;
                } else if (playedCard.charAt(1) == jPlayedCard.charAt(1)) {// si tienen el mismo color
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
                int aux = Character.getNumericValue(playedCard.charAt(0));
                if ((aux >= 0 && aux < 10) && acumulado == 0) {// Si el valor está entre 0 y 9 y no hay +2 o +4 
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

    public void siguienteTurno() {
        if (Dir) { // Si va hacía la derecha
            Turno = (Turno + 1) % mNumberOfRivals + 1;
        } else if (!Dir) { // Si va hacía la izquierda
            if (Turno == 0) {
                Turno = mNumberOfRivals;
            } else {
                Turno = Turno - 1;
            }
        }
    }

    public boolean playCard(String jPlayedCard) {
        Scanner scanner = new Scanner(System.in);
        switch (jPlayedCard.charAt(0)) {
            case 'S': { // Skip turn
                siguienteTurno();// Avanza 2 turnos
                siguienteTurno();
                mPlayedCard = jPlayedCard; // Actualiza carta jugada
            }
            case 'C': {  // Change color
                String aux;
                System.out.println("Ingrese el color que desea A = Azul, R = Rojo, V = Verde, M = Amarillo");
                aux = scanner.next();
                //Debe ingresar un color valido
                while (aux.charAt(0) != 'A' && aux.charAt(0) != 'V' && aux.charAt(0) != 'M' && aux.charAt(0) != 'R') {
                    System.out.println("Ingrese el color que desea A = Azul, R = Rojo, V = Verde, M = Amarillo");
                    aux = scanner.next();
                }
                mPlayedCard = "C" + aux;// Actualiza carta jugada
                System.out.println("Nuevo color " + aux);
            }
            case 'E': { // Forward
                Dir = !Dir;
                mPlayedCard = jPlayedCard;
            }
            case 'D': { // +2
                acumulado = acumulado + 2;
                mPlayedCard = jPlayedCard;
            }
            case 'B': {
                acumulado = acumulado + 4;
                String aux;
                System.out.println("Ingrese el color que desea A = Azul, R = Rojo, V = Verde, M = Amarillo");
                aux = scanner.next();
                //Debe ingresar un color valido
                while (aux.charAt(0) != 'A' && aux.charAt(0) != 'V' && aux.charAt(0) != 'M' && aux.charAt(0) != 'R') {
                    System.out.println("Ingrese el color que desea A = Azul, R = Rojo, V = Verde, M = Amarillo");
                    aux = scanner.next();
                }
                mPlayedCard = "B" + aux;// Actualiza carta jugada
                System.out.println("Nuevo color " + aux);
            }
            default: {
                mPlayedCard = jPlayedCard;
            }
        }
        return false;
    }

    public String playerTurn() {
        Scanner scanner = new Scanner(System.in);
        boolean uno;
        String aux;
        do {
            uno = false;
            
            if (acumulado != 0) { // Si tiene +2 o +4 pendiente
                System.out.println("Introduzca la carta o 0 para arrastrar " + String.valueOf(acumulado) + " cartas");
            } else {
                System.out.println("Introduzca la carta o 0 si no tiene carta o desea arrastrar");
            }
            
            aux = scanner.nextLine().trim(); // Por qué .trim?
            if(aux.length()==1  && aux.equals("0")){//Si introducen el cero
                if(acumulado != 0){
                    for(int i = 0; i<acumulado; i++){ // Da las cartas acumuladas
                        mJugador.setMyCards(mDequeOfCards.poll()); 
                    }
                    acumulado = 0;
                }else{
                    mJugador.setMyCards(mDequeOfCards.poll());
                }
                return "Arrastro";
            }
            
            if (cardCanPlay(mPlayedCard, aux.substring(0, 2))) {// Si la carta que tiró se puede jugar
                if (mJugador.getMyCards().size() == 2) {
                    if (aux.length() == 5 && aux.substring(2, 5).compareToIgnoreCase("UNO") == 0) {// Si la carta viene acompañada de la palabra UNo
                        if (mJugador.getMyCards().remove(aux.substring(0, 2) + "a") || mJugador.getMyCards().remove(aux.substring(0, 2) + "b")) { // si la carta está en el maso
                            uno = true;
                        } else {
                            uno = false;
                        }
                    } else { // si no dice uno
                        System.out.println("No dijiste \\\"UNO\\\" arrastra 2 cartas, introduce cualquier numero para continuar");
                        scanner.next();
                        mJugador.setMyCards(mDequeOfCards.poll());
                        mJugador.setMyCards(mDequeOfCards.poll());
                        return ("Arrastro");
                    }
                } else if(aux.length() == 2) {
                    if (mJugador.getMyCards().remove(aux.substring(0, 2) + "a") || mJugador.getMyCards().remove(aux.substring(0, 2) + "b")) { // si la carta está en el maso
                        return aux;
                    }
                }
            }
       
        } while (!uno);
        return aux;
    }

}
