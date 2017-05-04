public class Main {
    public static void main(String[] args) {

        Jugador jugador = new Jugador();
        Game game = new Game(jugador);
        Prompter prompter = new Prompter(jugador, game);
        prompter.gamePresentation();

        game.howManyPlayers();
        game.fillCards();
        game.dealTheCards();
        System.out.println("ola");

        while (!game.isGameOver()) {
            prompter.showPlayedCard();
            prompter.showPlayerCards();
            game.play();

        }

    }

}
