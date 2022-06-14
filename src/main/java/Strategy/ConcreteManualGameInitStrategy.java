package Strategy;

import Builder.GameBuilder;
import Validators.ValidatorFacade;
import model.Connector;
import model.ConnectorType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class ConcreteManualGameInitStrategy implements GameInitStrategy{
    HashMap<Integer, Connector> snakes = new HashMap<>();
    HashMap<Integer, Connector> ladders = new HashMap<>();

    @Override
    public GameBuilder initializeGame(GameBuilder gameBuilder, ValidatorFacade validatorFacade) {

        gameBuilder.setLadders(initializeLadders(gameBuilder, validatorFacade));
        gameBuilder.setSnakes(initializeSnakes(gameBuilder, validatorFacade));

        return gameBuilder;
    }


    private HashMap<Integer, Connector> initializeSnakes(GameBuilder gameBuilder, ValidatorFacade validatorFacade) {

        Scanner sc = new Scanner(System.in);
        /*
        Assuming boardSize = 100
        Snakes: start-> (2,99) ; end-> (1, start-1)
        Ladders: start-> (1,99) ; end-> (start+1,100)
         */
        int start, end;
        int snakeMinStart = 2;

        for (int i = 0; i < gameBuilder.getNumSnakes(); i++) {

            while(true) {
                start = sc.nextInt();

                if (start < snakeMinStart || start >= gameBuilder.getBoardSize())
                    System.out.println("Out of Range");
                else
                    break;
            }

            while(true) {
                end = sc.nextInt();

                if (end < 1 || end >= start)
                    System.out.println("Out of Range");
                else
                    break;
            }

            Connector snake = new Connector(start, end, ConnectorType.SNAKE);
            if (validatorFacade.getFirstValidator().validate(snake, this.snakes, this.ladders))
                snakes.put(snake.getStart(), snake);
            else
            {
                System.out.println("Invalid Snake");
                i--;
            }
        }
        sc.close();
        return snakes;
    }


    private HashMap<Integer, Connector> initializeLadders(GameBuilder gameBuilder, ValidatorFacade validatorFacade) {

        Scanner sc = new Scanner(System.in);
        /*
        Assuming boardSize = 100
        Snakes: start-> (2,99) ; end-> (1, start-1)
        Ladders: start-> (1,99) ; end-> (start+1,100)
         */

        HashSet<Integer> startPositions = new HashSet<>();
        HashSet<Integer> endPositions = new HashSet<>();

        int start, end;

        for (int i = 0; i < gameBuilder.getNumLadders(); i++) {

            while(true) {
                start = sc.nextInt();

                if (start < 1 || start >= gameBuilder.getBoardSize())
                    System.out.println("Out of Range");
                else
                    break;
            }

            while(true) {
                end = sc.nextInt();

                if (end <= start || end > gameBuilder.getBoardSize())
                    System.out.println("Out of Range");
                else
                    break;
            }

            Connector ladder = new Connector(start, end, ConnectorType.LADDER);
            if (validatorFacade.getFirstValidator().validate(ladder, this.snakes, this.ladders))
                ladders.put(ladder.getStart(), ladder);
            else
            {
                System.out.println("Invalid Ladder");
                i--;
            }
        }
        sc.close();
        return ladders;
    }
}
