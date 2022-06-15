package Strategy;

import Builder.GameBuilder;
import Validators.BasicSnakeLadderValidator;
import Validators.IValidate;
import Validators.NoMultipleConnectorsAtOneSpotValidator;
import Validators.ValidatorFacade;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class ConcreteAutoGameInitStrategyTest {

    private GameInitStrategy gameInitStrategy;

    private GameBuilder setupGameBuilder() {
        GameBuilder builder = new GameBuilder();
        builder.setGameBoard(new Board(100));
        builder.setNumberOfPlayers(2);
        builder.setDice(new Dice(1));
        builder.setNumberOfSnakes(5);
        builder.setNumberOfLadders(5);
        Player p1 = new Player("A");
        Player p2 = new Player("B");
        Queue<Player> playerQueue = new LinkedBlockingQueue<>();
        playerQueue.add(p1);
        playerQueue.add(p2);
        builder.setPlayerQueue(playerQueue);
        return builder;
    }

    @Test
    @DisplayName("Running multiple times")
    public void testAutoInitGameStrategy() {
        for (int i = 0; i < 50; i++) {
            gameInitStrategy = new ConcreteAutoGameInitStrategy();
            ArrayList<IValidate> validators = new ArrayList<>();
            validators.add(new NoMultipleConnectorsAtOneSpotValidator());
            validators.add(new BasicSnakeLadderValidator());
            validators.add(new NoMultipleConnectorsAtOneSpotValidator());
            ValidatorFacade validatorFacade = new ValidatorFacade(validators);
            GameBuilder builder = setupGameBuilder();
            Game game = gameInitStrategy.initializeGame(builder, validatorFacade).getGameInstance();
            HashMap<Integer, Connector> snakes = game.getSnakes();
            HashMap<Integer, Connector> ladders = game.getLadders();
            assertTrue(snakes.size() == game.getNumSnakes());
            assertTrue(ladders.size() == game.getNumLadders());
            Connector snake1 = (Connector) snakes.values().toArray()[0];
            snakes.remove(snake1.getStart());
            assertTrue(validatorFacade.getFirstValidator().validate(snake1, snakes, ladders));
            Connector ladder1 = (Connector) ladders.values().toArray()[0];
            ladders.remove(ladder1.getStart());
            assertTrue(validatorFacade.getFirstValidator().validate(ladder1, snakes, ladders));
        }
    }

}