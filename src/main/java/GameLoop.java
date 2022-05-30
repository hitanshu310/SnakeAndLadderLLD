import Builder.GameBuilder;
import Strategy.ConcreteAutoGameInitStrategy;
import Strategy.GameInitStrategy;
import Validators.BasicSnakeLadderValidator;
import Validators.IValidate;
import Validators.NoMultipleConnectorsAtOneSpotValidator;
import Validators.ValidatorFacade;
import model.Board;
import model.Dice;

import java.util.ArrayList;

public class GameLoop {

    private GameInitStrategy gameInitStrategy;

    public void setupGame(){

        GameBuilder builder = new GameBuilder();
        builder.setGameBoard(new Board(100));
        builder.setNumberOfPlayers(5);
        builder.setDice(new Dice(2));

        gameInitStrategy = new ConcreteAutoGameInitStrategy();
        ArrayList<IValidate> validators = new ArrayList<>();
        validators.add(new NoMultipleConnectorsAtOneSpotValidator());
        validators.add(new BasicSnakeLadderValidator());
        validators.add(new NoMultipleConnectorsAtOneSpotValidator());
        ValidatorFacade validatorFacade = new ValidatorFacade(validators);
        builder = gameInitStrategy.initializeGame(builder, validatorFacade);
        builder.setPlayerQueue(null);
        builder.getGameInstance();
    }

}
