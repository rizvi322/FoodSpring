package org.foodspring.validator;

import org.apache.log4j.Logger;
import org.foodspring.domain.AssignMeal;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 4/1/13
 * Time: 12:35 PM
 * To change this template use File | Settings | File Templates.
 */

@Service
public class AssignMealFormValidator implements Validator {

    private static Logger log = Logger.getLogger(AssignMealFormValidator.class.getName());

    @Override
    public boolean supports(Class<?> aClass) {
        return AssignMeal.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        AssignMeal assignMeal = (AssignMeal) o;
        if (assignMeal == null) {
            errors.rejectValue("meal_id", "Not Specified", null, "Value required.");
        } else {
            log.info("Validating with " + assignMeal);
            if (assignMeal.getMeal_id() == 0) {
                errors.rejectValue("meal_id", "Value Required",
                        null, "Value Required.");
            }

            if (assignMeal.getMeal_time() == null || assignMeal.getMeal_time() == "") {
                errors.rejectValue("meal_time", "Value Required",
                        null, "Value Required.");
            }
        }
    }
}
