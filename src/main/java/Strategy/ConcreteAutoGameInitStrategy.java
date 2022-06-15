package Strategy;

import Builder.GameBuilder;
import Validators.ValidatorFacade;
import model.Connector;
import model.ConnectorType;

import java.util.HashMap;
import java.util.HashSet;

public class ConcreteAutoGameInitStrategy implements GameInitStrategy {

    HashMap<Integer, Connector> snakes = new HashMap<>();
    HashMap<Integer, Connector> ladders = new HashMap<>();
    @Override
    public GameBuilder initializeGame(GameBuilder gameBuilder, ValidatorFacade validatorFacade) {

        initializeLadders(gameBuilder, validatorFacade);
        gameBuilder.setLadders(this.ladders);
        initializeSnakes(gameBuilder, validatorFacade);
        gameBuilder.setSnakes(this.snakes);
        return gameBuilder;
    }


    private void initializeSnakes(GameBuilder gameBuilder, ValidatorFacade validatorFacade) {
        /*
        Assuming boardSize = 100
        Snakes: start-> (2,99) ; end-> (1, start-1)
        Ladders: start-> (1,99) ; end-> (start+1,100)
         */
        int start, end;
        int snakeMinStart = 2;

        for (int i = 0; i < gameBuilder.getNumSnakes(); i++) {

            start = (int) (Math.random() * (gameBuilder.getBoardSize() - snakeMinStart) + snakeMinStart);
            end = (int) (Math.random() * (start - 1) + 1);
            Connector snake = new Connector(start, end, ConnectorType.SNAKE);
            if (validatorFacade.getFirstValidator().validate(snake, this.snakes, this.ladders))
                snakes.put(snake.getStart(), snake);
            else
            {
                System.out.println("Snake start " + snake.getStart() + "Snake End " + snake.getEnd());
                System.out.println("Invalid Snake");
                i--;
            }
        }
    }

    private void initializeLadders(GameBuilder gameBuilder, ValidatorFacade validatorFacade) {
        /*
        Assuming boardSize = 100
        Snakes: start-> (2,99) ; end-> (1, start-1)
        Ladders: start-> (1,99) ; end-> (start+1,100)
         */
        int start, end;

        for (int i = 0; i < gameBuilder.getNumLadders(); i++) {

            start = (int) (Math.random() * (gameBuilder.getBoardSize() - 1) + 1);
            end = (int) (Math.random() * (gameBuilder.getBoardSize() - start) + start + 1);
            Connector ladder = new Connector(start, end, ConnectorType.LADDER);
            if (validatorFacade.getFirstValidator().validate(ladder, this.snakes, this.ladders))
                ladders.put(ladder.getStart(), ladder);
            else
            {
                System.out.println("Ladder start " + ladder.getStart() + "Ladder End " + ladder.getEnd());
                System.out.println("Invalid Ladder");
                i--;
            }
        }
    }
}
