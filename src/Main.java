
public class Main {
    public static void main(String[] args) {

        Game game=new Game();
        for(String[] i:game.getCards()){
            for(String j:i){
                System.out.print(j+" ");
            }
            System.out.println("");
        }

    }

}
