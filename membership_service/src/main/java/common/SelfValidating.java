package common;


import jakarta.validation.*;

import java.util.Set;

public abstract class SelfValidating<T> {

    private Validator validator;

    public SelfValidating() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    protected void validateSelf() {
        Set<ConstraintViolation<T>> validate = validator.validate((T) this);
        if (!validate.isEmpty()) {
            throw new ConstraintViolationException(validate);
        }
    }

}