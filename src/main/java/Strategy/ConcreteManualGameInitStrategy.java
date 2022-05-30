package Strategy;

import Builder.GameBuilder;
import Validators.ValidatorFacade;

public class ConcreteManualGameInitStrategy implements GameInitStrategy{

    @Override
    public GameBuilder initializeGame(GameBuilder gameBuilder, ValidatorFacade validatorFacade) {
        return null;
    }
}
