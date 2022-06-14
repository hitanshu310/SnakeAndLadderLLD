import Builder.GameBuilder;
import Strategy.ConcreteAutoGameInitStrategy;
import Strategy.ConcreteEndGameWhenOnePlayerFinishes;
import Strategy.GameCompletionStrategy;
import Strategy.GameInitStrategy;
import Validators.BasicSnakeLadderValidator;
import Validators.IValidate;
import Validators.NoMultipleConnectorsAtOneSpotValidator;
import Validators.ValidatorFacade;
import model.*;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class GameLoop {

    private GameInitStrategy gameInitStrategy;
    private Game game;
    private GameCompletionStrategy gameCompletionStrategy;

    public void setupGame() {
        GameBuilder builder = new GameBuilder();
        builder.setGameBoard(new Board(100));
        builder.setNumberOfPlayers(2);
        // Add avalidation where in atleast 2 players are required to play a game
        builder.setDice(new Dice(1));
        gameInitStrategy = new ConcreteAutoGameInitStrategy();
        ArrayList<IValidate> validators = new ArrayList<>();
        validators.add(new NoMultipleConnectorsAtOneSpotValidator());
        validators.add(new BasicSnakeLadderValidator());
        validators.add(new NoMultipleConnectorsAtOneSpotValidator());
        ValidatorFacade validatorFacade = new ValidatorFacade(validators);
        Player p1 = new Player("A");
        Player p2 = new Player("B");
        Queue<Player> playerQueue = new LinkedBlockingQueue<>();
        playerQueue.add(p1);
        playerQueue.add(p2);
        builder.setPlayerQueue(playerQueue);
        builder = gameInitStrategy.initializeGame(builder, validatorFacade);
        gameCompletionStrategy = new ConcreteEndGameWhenOnePlayerFinishes();
        game = builder.getGameInstance();
    }

    public void playGame(Game game) {
        Queue<Player> playerQueue = game.getPlayerQueue();
        // While game is not finished, set GameState.FINISHED to stop game
        while (game.getGameState().equals(GameState.RUNNING)) {

            /* Remove player who has a turn from queue,
            we assume the player hasn't completed the game yet,
            so we need to check */

            Player currentPlayer = playerQueue.poll();

            /* Play the turn */
            currentPlayer.setLocation(currentPlayer.getLocation() + game.getDice().roll());

            /* Check if the currentPlayer has reached the finsihing point if
            so they have finished the game */

            if (currentPlayer.getLocation() == game.getGameBoard().getSize()) {
                currentPlayer.setFinished(true);
                System.out.println("Player " + currentPlayer.getPlayerName() + "finishes the game");
            }

            /* Checking game ending condition based on game Ending strategy,
            If game has ended set GameState as Finished so the Game loop will end */

            if (gameCompletionStrategy.isGameComplete(currentPlayer, playerQueue))
                game.setGameState(GameState.FINISHED);

            if (!currentPlayer.getFinished())
                playerQueue.add(currentPlayer);

        }
    }

}
