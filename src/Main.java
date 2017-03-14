import java.util.Iterator;

public class Main {
    public static void main(String[] args) {

        Jugador jugador=new Jugador();
        Game game=new Game(jugador);
        Prompter prompter=new Prompter(jugador);
        Iterator<String> iterator;
        iterator=game.getDequeOfCards().listIterator();
        int i=0;
        boolean gameOver=false;

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
            i++;
            if (i == 9) {
                i = 0;
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
        while(!gameOver) {

            prompter.showPlayerCards();
            game.play();
            gameOver=true;
        }

    }

}
