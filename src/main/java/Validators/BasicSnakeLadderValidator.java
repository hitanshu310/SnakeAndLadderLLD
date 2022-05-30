package Validators;

import model.Connector;
import model.ConnectorType;

import java.util.HashMap;

public class BasicSnakeLadderValidator extends IValidate {


    @Override
    public boolean validate(Connector connector, HashMap<Integer, Connector> snakes, HashMap<Integer, Connector> ladders) {
        if (connector.getConnectorType() == ConnectorType.SNAKE) {
            if (connector.getEnd() >= connector.getStart())
                return false;
            else if (hasNext())
                return this.getValidator().validate(connector, snakes, ladders);
            else
                return true;

        }
        else if (connector.getConnectorType() == ConnectorType.LADDER )
        {
            if (connector.getStart() >= connector.getEnd())
                return false;
            else if (hasNext())
                return this.getValidator().validate(connector, snakes, ladders);
            else
                return true;
        }
        else
            return false;
    }

}
