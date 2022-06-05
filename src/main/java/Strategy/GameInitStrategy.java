package Strategy;

import Builder.GameBuilder;
import Validators.ValidatorFacade;
import model.Connector;

import java.util.HashMap;

public interface GameInitStrategy {

    public GameBuilder initializeGame(GameBuilder gameBuilder, ValidatorFacade validatorFacade);

    public HashMap<Integer, Connector> initializeSnakes(GameBuilder gameBuilder, ValidatorFacade validatorFacade);

    public HashMap<Integer, Connector> initializeLadders(GameBuilder gameBuilder, ValidatorFacade validatorFacade);
}
