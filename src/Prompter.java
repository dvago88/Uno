import java.util.Iterator;
import java.util.Scanner;

public class Prompter {

    private Jugador mJugador;
    private Game mGame;

    public Prompter(Jugador jugador, Game game) {
        mJugador = jugador;
        mGame = game;
    }

    public void gamePresentation() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%nBIENVENIDO al juego de cartas UNO%n%n%n");
        System.out.printf("%nsi eres un jugador avido y no necesitas leer las reglas o instruciones presiona 0" +
                "%nde los contrario presiona cualquier otro numero y la tecla enter.%n");
        if (scanner.nextInt() != 0) {
            System.out.printf("%n%n1.El juego se inicia preguntando cuantos rivales quieres tener, se pueden tener entre 1 y 3 rivales.%n" +
                    "2.Luego de esto el juego baraja y reparte las cartas automaticamente.%n" +
                    "3.Para facilidad a la hora de tener las cartas en la pantalla se abrevio los colores:%n" +
                    "A=azul%n" +
                    "M=amarillo%n" +
                    "R=rojo%n" +
                    "V=verde%n" +
                    "4.Para jugar se debe introducir el numero de la posicion de la carta empezando por el numero 1. Si se desea arrastrar%n" +
                    "se debe introducir el numero 0.%n" +
                    "5. Cuando se tengan solo 2 cartas restantes en la mano y se vaya a tirar una, se debe poner la posicion de la carta%n" +
                    "a tirar seguido de un espacio y la palabra \"UNO\"*, sin las comillas. En caso tal de no hacerlo se penalizara al jugador%n" +
                    "con 2 cartas adicionales.%n");
            System.out.printf("*El programa esta diseñado para soportar errores a la hora de ingresar los numeros y la palabra \"UNO\",%n" +
                    "sin embargo se recomienda revisar bien la entrada antes de presionar enter.");
        }

    }

    public void showPlayerCards() {
        Iterator<String> iterator;
        iterator = mJugador.getMyCards().listIterator();
        System.out.println("");
        System.out.println("============================");
        System.out.println("SUS CARTAS:");
        while (iterator.hasNext()) {
            System.out.print(iterator.next().substring(0,2) + " ");
        }
        System.out.println("");
        System.out.println("============================");

    }

    public void showPlayedCard() {
        System.out.println("");
        System.out.println("La carta en juego es: " + mGame.getPlayedCard().substring(0,2));
    }

}
