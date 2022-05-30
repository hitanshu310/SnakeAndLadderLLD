package Validators;

import model.Connector;

import java.util.HashMap;

public class NoMultipleConnectorsAtOneSpotValidator extends IValidate {
    @Override
    public boolean validate(Connector connector, HashMap<Integer, Connector> snakes, HashMap<Integer, Connector> ladders) {
        int startingPosition = connector.getStart();
        if (snakes.containsKey(startingPosition) || ladders.containsKey(startingPosition))
            return false;
        else if (hasNext())
            return this.getValidator().validate(connector, snakes, ladders);
        else
            return true;
    }
}
