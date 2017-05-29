
import java.util.Iterator;
import java.util.Scanner;

public class Prompter {

    private Jugador mJugador;

    public Prompter(Jugador jugador) {
        mJugador = jugador;
    }

    public static void gamePresentation() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%nBIENVENIDO al juego de cartas UNO%n%n%n");
        System.out.printf("%nsi eres un jugador avido y no necesitas leer las reglas o instruciones presiona 0"
                + "%nde los contrario presiona cualquier otro numero y la tecla enter.%n");
        if (scanner.nextInt() != 0) {
            System.out.printf("%n%n1.El juego se inicia preguntando cuantos rivales quieres tener, se pueden tener entre 1 y 3 rivales.%n"
                    + "2.Luego de esto el juego baraja y reparte las cartas automaticamente.%n"
                    + "3.Para facilidad a la hora de tener las cartas en la pantalla se abrevio los colores:%n"
                    + "A=azul%n"
                    + "M=amarillo%n"
                    + "R=rojo%n"
                    + "V=verde%n"
                    + "Y las cartas especiales:%n"
                    + "___________________________________________________________%n"
                    + "=======|C-Color||S-Skip||E-Revers||D-Dos||B-Cuatro||=======%n"
                    + "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯%n"
                    + "4.Para jugar se debe introducir  la carta empezando por el numero 1 o una carta especial. Si se desea arrastrar%n"
                    + "se debe introducir el numero 0.%n"
                    + "5. Cuando se tengan solo 2 cartas restantes en la mano y se vaya a tirar una, se debe poner la posicion de la carta%n"
                    + "a tirar seguido de un espacio y la palabra \"UNO\"*, sin las comillas. En caso tal de no hacerlo se penalizara al jugador%n"
                    + "con 2 cartas adicionales.%n");
            System.out.printf("*El programa esta diseñado para soportar errores a la hora de ingresar los numeros y la palabra \"UNO\",%n"
                    + "sin embargo se recomienda revisar bien la entrada antes de presionar enter.");
        }

    }

    public void showPlayerCards() {
        Iterator<String> iterator;
        iterator = mJugador.getMyCards().iterator();
        System.out.println("===========================================================");
        System.out.println("SUS CARTAS:");
        while (iterator.hasNext()) {
            System.out.print(iterator.next().substring(0, 2) + " ");
        }
        System.out.println("");
        System.out.println("___________________________________________________________");
        System.out.println("=======|C-Color||S-Skip||E-Revers||D-Dos||B-Cuatro||=======");
        System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
    }

    public static void showPlayedCard(String mPlayedCard) {
        System.out.println("");
        System.out.println("La carta en juego es: " + mPlayedCard.substring(0, 2));

    }

    public static void showNewColor(char color){
        System.out.print("Nuevo color: ");
        switch(color){
            case 'A':System.out.println("Azul");
                    break;
            case 'M':System.out.println("Amarillo");
                    break;
            case 'V':System.out.println("Verde");
                    break;
            case 'R':System.out.println("Rojo");
                    break;
                                            
        }
    }
}
