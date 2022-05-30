package Validators;

import lombok.val;
import model.Connector;

import java.util.HashMap;

public abstract class IValidate {

    private IValidate nextValidator;

    abstract public boolean validate(Connector connector, HashMap<Integer, Connector> snakes, HashMap<Integer, Connector> ladders);

    public boolean hasNext() {

        if (this.nextValidator != null)
            return true;
        else return false;
    }

    public void setValidator(IValidate validator){
        this.nextValidator = validator;
    }

    public IValidate getValidator(){
        return this.nextValidator;
    }

}
