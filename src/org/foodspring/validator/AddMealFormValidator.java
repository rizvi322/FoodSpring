package org.foodspring.validator;

import org.apache.log4j.Logger;
import org.foodspring.domain.Meal;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 4/1/13
 * Time: 3:26 PM
 * To change this template use File | Settings | File Templates.
 */

@Service
public class AddMealFormValidator implements Validator {

    private static Logger log = Logger.getLogger(AddMealFormValidator.class.getName());

    @Override
    public boolean supports(Class<?> aClass) {
        return Meal.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        Meal meal = (Meal) o;
        if (meal == null) {
            errors.rejectValue("items", "Not Specified", null, "Value required.");
        } else {
            log.info("Validating with " + meal);

            if (meal.getItems() == null || meal.getItems() == "") {
                errors.rejectValue("items", "Value Required",
                        null, "Value Required.");
            }
        }
    }
}
