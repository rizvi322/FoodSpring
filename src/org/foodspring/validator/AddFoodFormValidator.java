package org.foodspring.validator;

import org.apache.log4j.Logger;
import org.foodspring.domain.Food;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 4/1/13
 * Time: 2:46 PM
 * To change this template use File | Settings | File Templates.
 */

@Service
public class AddFoodFormValidator implements Validator {

    private static Logger log = Logger.getLogger(AddFoodFormValidator.class.getName());

    @Override
    public boolean supports(Class<?> aClass) {
        return Food.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        Food food = (Food) o;
        if (food == null) {
            errors.rejectValue("name", "Not Specified", null, "Value required.");
        } else {
            log.info("Validating with " + food);

            if (food.getName() == null || food.getName() == "") {
                errors.rejectValue("name", "Value Required",
                        null, "Value Required.");
            }
        }
    }
}
