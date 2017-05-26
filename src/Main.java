public class Main {
    public static void main(String[] args) {
        Jugador jugador = new Jugador();
        Game game = new Game(jugador);
        Prompter.gamePresentation();

        game.howManyPlayers();
        game.fillCards();
        game.dealTheCards();

        while (!game.isGameOver()) {
            game.play();

        }

    }

}
