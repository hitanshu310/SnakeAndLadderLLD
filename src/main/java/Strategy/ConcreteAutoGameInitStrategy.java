package Strategy;

import Builder.GameBuilder;
import Validators.ValidatorFacade;
import model.Connector;
import model.ConnectorType;

import java.util.HashMap;

public class ConcreteAutoGameInitStrategy implements GameInitStrategy {
    @Override
    public GameBuilder initializeGame(GameBuilder builder, ValidatorFacade validatorFacade) {
        HashMap<Integer, Connector> snakes = new HashMap<>();
        HashMap<Integer, Connector> ladders = new HashMap<>();
        for (int i = 0; i < builder.getNumSnakes(); i++){
            Connector snake = new Connector(3, 45, ConnectorType.SNAKE);
        }
        return null;
    }
}
