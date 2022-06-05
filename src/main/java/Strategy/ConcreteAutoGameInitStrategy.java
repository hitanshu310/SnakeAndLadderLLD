package Strategy;

import Builder.GameBuilder;
import Validators.ValidatorFacade;
import model.Connector;
import model.ConnectorType;

import java.util.HashMap;
import java.util.HashSet;

public class ConcreteAutoGameInitStrategy implements GameInitStrategy {
    @Override
    public GameBuilder initializeGame(GameBuilder gameBuilder, ValidatorFacade validatorFacade) {

        gameBuilder.setLadders(initializeLadders(gameBuilder, validatorFacade));
        gameBuilder.setSnakes(initializeSnakes(gameBuilder, validatorFacade));

        return gameBuilder;
    }

    @Override
    public HashMap<Integer, Connector> initializeSnakes(GameBuilder gameBuilder, ValidatorFacade validatorFacade) {

        HashMap<Integer, Connector> snakes = new HashMap<>();

        /*
        Assuming boardSize = 100
        Snakes: start-> (2,99) ; end-> (1, start-1)
        Ladders: start-> (1,99) ; end-> (start+1,100)
         */

        HashSet<Integer> startPositions = new HashSet<>();
        HashSet<Integer> endPositions = new HashSet<>();

        int start, end;
        int snakeMinStart = 2;

        for (int i = 0; i < gameBuilder.getNumSnakes(); i++) {

            start = (int) (Math.random() * (gameBuilder.getBoardSize() - snakeMinStart) + snakeMinStart);
            while (startPositions.contains(start) || endPositions.contains(start))
                start = (int) (Math.random() * (gameBuilder.getBoardSize() - snakeMinStart) + snakeMinStart);

            startPositions.add(start);

            end = (int) (Math.random() * (start - 1) + 1);
            while (startPositions.contains(end))
                end = (int) (Math.random() * (start - 1) + 1);

            endPositions.add(end);


            Connector snake = new Connector(start, end, ConnectorType.SNAKE);
            snakes.put(start, snake);
        }

        return snakes;
    }

    @Override
    public HashMap<Integer, Connector> initializeLadders(GameBuilder gameBuilder, ValidatorFacade validatorFacade) {

        HashMap<Integer, Connector> ladders = new HashMap<>();

        /*
        Assuming boardSize = 100
        Snakes: start-> (2,99) ; end-> (1, start-1)
        Ladders: start-> (1,99) ; end-> (start+1,100)
         */

        HashSet<Integer> startPositions = new HashSet<>();
        HashSet<Integer> endPositions = new HashSet<>();

        int start, end;

        for (int i = 0; i < gameBuilder.getNumLadders(); i++) {

            start = (int) (Math.random() * (gameBuilder.getBoardSize() - 1) + 1);
            while (startPositions.contains(start) || endPositions.contains(start))
                start = (int) (Math.random() * (gameBuilder.getBoardSize() - 1) + 1);

            startPositions.add(start);

            end = (int) (Math.random() * (gameBuilder.getBoardSize() - start) + start + 1);
            while (startPositions.contains(end))
                end = (int) (Math.random() * (gameBuilder.getBoardSize() - start) + start + 1);

            endPositions.add(end);


            Connector ladder = new Connector(start, end, ConnectorType.LADDER);
            ladders.put(start, ladder);
        }

        return ladders;
    }
}
