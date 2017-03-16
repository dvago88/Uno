import java.util.Iterator;
import java.util.Scanner;

public class Prompter {

    private Jugador mJugador;
    private Game mGame;

    public Prompter(Jugador jugador,Game game) {
        mJugador = jugador;
        mGame=game;
    }

    public void showPlayerCards(){
        Iterator<String> iterator;
        iterator=mJugador.getMyCards().listIterator();
        System.out.println("");
        System.out.println("============================");
        System.out.println("SUS CARTAS:");
        while(iterator.hasNext()){
            System.out.print(iterator.next()+" ");
        }
        System.out.println("");
        System.out.println("============================");

    }

    public int playerTurn() {
        Scanner scanner=new Scanner(System.in);
        int playedCard;
        do {
            System.out.println("Introduzca el numero de la posicion de su carta");
            playedCard = scanner.nextInt();
        }while(playedCard>=mJugador.getMyCards().size());
        return playedCard;
    }

    public String table(){

        return "";
    }
}
