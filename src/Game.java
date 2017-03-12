
public class Game {
    private int[][] mCards =new int[8][9];

    public Game() {

    }

    public int[][] getCards() {
        return mCards;
    }

    public void setCards(int[][] cards) {
        mCards = cards;
    }

    public void fillCards(){
        for(int i=0;i<8;i++){
            int counter=0;
            for(int j=0;j<9;j++){
                mCards[i][j]=counter;
                counter++;
            }
        }
    }


}
