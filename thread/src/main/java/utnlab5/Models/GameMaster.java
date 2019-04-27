package utnlab5.Models;

public class GameMaster implements Runnable{

    private Game game;

    public GameMaster(Game game) {
        this.game = game;
    }

    public void run() {
        AdminGameTurns();
    }

    private void AdminGameTurns() {
        while (!game.Finished()) {
            game.NextTurn();
        }
    }

}
