package Validators;

import model.Connector;
import model.ConnectorType;

import java.util.HashMap;

public class NoSnakeLadderLoopValidator extends IValidate{
    @Override
    public boolean validate(Connector connector, HashMap<Integer, Connector> snakes, HashMap<Integer, Connector> ladders) {
        Connector fastMover = connector;
        Connector slowMover = connector;
        while (snakes.containsKey(fastMover.getEnd()) || ladders.containsKey(fastMover.getEnd())) {
            if (snakes.containsKey(fastMover.getEnd()))
                fastMover = snakes.get(fastMover.getEnd());
            else
                fastMover = ladders.get(fastMover.getEnd());

            if (snakes.containsKey(fastMover.getEnd()) || ladders.containsKey(fastMover.getEnd())) {
                if (snakes.containsKey(fastMover.getEnd()))
                    fastMover = snakes.get(fastMover.getEnd());
                else
                    fastMover = ladders.get(fastMover.getEnd());
            } else if (hasNext())
                return this.getValidator().validate(connector, snakes, ladders);
            else
                return true;

            if (snakes.containsKey(slowMover.getEnd()))
                slowMover = snakes.get(slowMover.getEnd());
            else
                slowMover = ladders.get(slowMover.getEnd());

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
