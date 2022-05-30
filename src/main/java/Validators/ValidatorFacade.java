package Validators;

import java.util.LinkedList;
import java.util.List;

public class ValidatorFacade {

    LinkedList<IValidate> validateList;
    private IValidate firstValidator;

    public ValidatorFacade(List<IValidate> validateList){
        if (!validateList.isEmpty())
            firstValidator = validateList.get(0);
        IValidate currentValidator = firstValidator;
        for (int i = 1; i < validateList.size(); i++)
        {
            currentValidator.setValidator(validateList.get(i));
            currentValidator = currentValidator.getValidator();
        }
    }

    public IValidate getFirstValidator() {
        return firstValidator;
    }
}
