import java.util.Iterator;

public class Main {
    public static void main(String[] args) {

        Game game=new Game();
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

    }

}
