import java.util.Scanner;

public class Prompter {
    private int mNumberOfPlayers;

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
}
