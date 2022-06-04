package Validators;

import model.Connector;
import model.ConnectorType;

import java.util.HashMap;

public class NoSnakeLadderLoopValidator extends IValidate{
    @Override
    public boolean validate(Connector connector, HashMap<Integer, Connector> snakes, HashMap<Integer, Connector> ladders) {

        HashMap<Integer, Connector> snakesShallowCopy = (HashMap<Integer, Connector>) snakes.clone();
        HashMap<Integer, Connector> laddersShallowCopy = (HashMap<Integer, Connector>) ladders.clone();

        if (connector.getConnectorType() == ConnectorType.LADDER)
            laddersShallowCopy.put(connector.getStart(), connector);
        else
            snakesShallowCopy.put(connector.getStart(), connector);

        Connector fastMover = connector;
        Connector slowMover = connector;
        while (snakesShallowCopy.containsKey(fastMover.getEnd()) || laddersShallowCopy.containsKey(fastMover.getEnd())) {
            if (snakesShallowCopy.containsKey(fastMover.getEnd()))
                fastMover = snakesShallowCopy.get(fastMover.getEnd());
            else
                fastMover = laddersShallowCopy.get(fastMover.getEnd());

            if (snakesShallowCopy.containsKey(fastMover.getEnd()) || laddersShallowCopy.containsKey(fastMover.getEnd())) {
                if (snakesShallowCopy.containsKey(fastMover.getEnd()))
                    fastMover = snakesShallowCopy.get(fastMover.getEnd());
                else
                    fastMover = laddersShallowCopy.get(fastMover.getEnd());
            } else if (hasNext())
                return this.getValidator().validate(connector, snakes, ladders);
            else
                return true;

            if (snakesShallowCopy.containsKey(slowMover.getEnd()))
                slowMover = snakesShallowCopy.get(slowMover.getEnd());
            else
                slowMover = laddersShallowCopy.get(slowMover.getEnd());

            if (fastMover == slowMover)
                return false;
        }

        if (fastMover == slowMover) {
            if (hasNext())
                return this.getValidator().validate(connector, snakes, ladders);
            else
                return true;
        }
        else
            return false;
    }

}
