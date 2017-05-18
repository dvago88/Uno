import java.util.Iterator;
import java.util.TreeSet;

public class GameLogic {
    private String mPlayedCard;
    private TreeSet<String> mCards;
    private TreeSet otro;

    public String whatToPlay(String card, TreeSet<String> cards) {
        mPlayedCard = card;
        mCards = cards;
        String whatToPlay;
        char color;
        Iterator<String> iterator;

        String x = String.valueOf(mPlayedCard.charAt(1));
        //Esto crea un subSet solo con el color de la carta a comparar:
        otro = (TreeSet) mCards.subSet("0" + x + "0", true, "Z" + x + "9", true);

        if (!otro.isEmpty()) {
            return (String) otro.pollFirst();
        }

      /*  este aun no funciona, pero la idea es que haga lo mismo pero con los numeros en vez de las letras, por ahora solo itera como antes.
        String z = String.valueOf(mPlayedCard.charAt(0));
        otro=(TreeSet) mCards.subSet(z+x.matches("[A-Z]")+"0",true,z+x.matches("[A-Z]")+"9",true);*/


        color = mPlayedCard.charAt(0);
        iterator = mCards.iterator();
        while (iterator.hasNext()) {
            char aux;
            whatToPlay = iterator.next();
            aux = whatToPlay.charAt(0);
            if (color == aux) {
                iterator.remove();
                return whatToPlay;
            }
        }

        return "sin carta";
    }

}

