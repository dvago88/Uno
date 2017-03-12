
public class Game {
    private String[][] mCards =new String[8][9];
    private String [] mColores={"R","M","V","A","R","M","V","A"};

    public Game() {
        fillCards();
    }

    public String[][] getCards() {
        return mCards;
    }

    public void setCards(String[][] cards) {
        mCards = cards;
    }

    public void fillCards(){
        for(int i=0;i<8;i++){
            int counter=1;
            for(int j=0;j<9;j++){
                mCards[i][j]=counter+mColores[i];
                counter++;
            }
        }
    }


}
