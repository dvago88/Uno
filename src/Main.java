import java.util.Iterator;

public class Main {
    public static void main(String[] args) {

        Jugador jugador = new Jugador();
        Game game = new Game(jugador);
        Prompter prompter = new Prompter(jugador,game);
        Iterator<String> iterator;
        boolean gameOver = false;

        game.howManyPlayers();
        game.fillCards();
        game.dealTheCards();

        for (int j = 1; j <= 72; j++) {
            System.out.print(game.getRawCards()[j - 1] + " ");
            if (j % 9 == 0) {
                System.out.println("");
            }
        }
        System.out.println("");
        System.out.println("");
        for (int j = 1; j <= 72; j++) {
            System.out.print(game.getDequeOfCards().poll() + " ");
            if (j % 9 == 0) {
                System.out.println("");
            }
        }


        iterator = game.getFirstRival().listIterator();
        System.out.println("");
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println("");
        iterator = game.getSecondRival().listIterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println("");
        iterator = game.getThirdRival().listIterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        while (!gameOver) {

            prompter.showPlayerCards();
            game.play();
            gameOver = true;
        }

    }

}
