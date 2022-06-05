package Strategy;

import Builder.GameBuilder;
import Validators.ValidatorFacade;
import model.Connector;
import model.ConnectorType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class ConcreteManualGameInitStrategy implements GameInitStrategy{

    @Override
    public GameBuilder initializeGame(GameBuilder gameBuilder, ValidatorFacade validatorFacade) {

        gameBuilder.setLadders(initializeLadders(gameBuilder, validatorFacade));
        gameBuilder.setSnakes(initializeSnakes(gameBuilder, validatorFacade));

        return gameBuilder;
    }

    @Override
    public HashMap<Integer, Connector> initializeSnakes(GameBuilder gameBuilder, ValidatorFacade validatorFacade) {

        Scanner sc = new Scanner(System.in);
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

            while(true) {
                start = sc.nextInt();

                if (start < snakeMinStart || start >= gameBuilder.getBoardSize())
                    System.out.println("Out of Range");
                else if (startPositions.contains(start) || endPositions.contains(start))
                    System.out.println("Value Already Used");
                else {
                    startPositions.add(start);
                    break;
                }
            }

            while(true) {
                end = sc.nextInt();

                if (end < 1 || end >= start)
                    System.out.println("Out of Range");
                else if (startPositions.contains(start))
                    System.out.println("Value Already Used");
                else {
                    startPositions.add(start);
                    break;
                }
            }

            Connector snake = new Connector(start, end, ConnectorType.SNAKE);
            snakes.put(start, snake);
        }

        return snakes;
    }

    @Override
    public HashMap<Integer, Connector> initializeLadders(GameBuilder gameBuilder, ValidatorFacade validatorFacade) {

        Scanner sc = new Scanner(System.in);
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

            while(true) {
                start = sc.nextInt();

                if (start < 1 || start >= gameBuilder.getBoardSize())
                    System.out.println("Out of Range");
                else if (startPositions.contains(start) || endPositions.contains(start))
                    System.out.println("Value Already Used");
                else {
                    startPositions.add(start);
                    break;
                }
            }

            while(true) {
                end = sc.nextInt();

                if (end <= start || end > gameBuilder.getBoardSize())
                    System.out.println("Out of Range");
                else if (startPositions.contains(start))
                    System.out.println("Value Already Used");
                else {
                    startPositions.add(start);
                    break;
                }
            }

            Connector ladder = new Connector(start, end, ConnectorType.SNAKE);
            ladders.put(start, ladder);
        }

        return ladders;
    }
}
