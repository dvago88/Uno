import java.util.Iterator;
import java.util.Scanner;

public class Prompter {
    private int mNumberOfPlayers;
    private Jugador mJugador;

    public Prompter(Jugador jugador) {
        mJugador = jugador;
    }

    public void howManyPlayers() {
        Scanner scanner = new Scanner(System.in);
        int jugadores;
        do {
            System.out.println("Cuantos rivales?");
            jugadores = scanner.nextInt();
        } while (jugadores < 1 || jugadores > 3);
        mNumberOfPlayers=jugadores;
    }

    public int getNumberOfPlayers() {
        return mNumberOfPlayers;
    }

    public void showPlayerCards(){
        Iterator<String> iterator;
        iterator=mJugador.getMyCards().listIterator();
        System.out.println("");
        System.out.println("============================");
        while(iterator.hasNext()){
            System.out.print(iterator.next()+" ");
        }
        System.out.println("");
        System.out.println("============================");

    }
}
