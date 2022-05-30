package Strategy;

import Builder.GameBuilder;
import Validators.ValidatorFacade;

public interface GameInitStrategy {

    public GameBuilder initializeGame(GameBuilder gameBuilder, ValidatorFacade validatorFacade);
}
