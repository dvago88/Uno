import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Jugador jugador=new Jugador();
        Game game=new Game(jugador);
        Prompter prompter=new Prompter(jugador);
        Iterator<String> iterator;
        iterator=game.getDequeOfCards().listIterator();
        int i=0;
        while (iterator.hasNext()){
            System.out.print(iterator.next()+" ");
            i++;
            if (i==9){
                i=0;
                System.out.println("");
            }
        }
        iterator=game.getFirstRival().listIterator();
        System.out.println("");
        while(iterator.hasNext()){
            System.out.print(iterator.next()+" ");
        }
        System.out.println("");
        iterator=game.getSecondRival().listIterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next()+" ");
        }
        System.out.println("");
        iterator=game.getThirdRival().listIterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next()+" ");
        }

        prompter.showPlayerCards();

    }

}
