package org.foodspring.validator;

import org.apache.log4j.Logger;
import org.foodspring.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 4/2/13
 * Time: 11:29 AM
 * To change this template use File | Settings | File Templates.
 */

@Service
public class LoginValidator implements Validator {

    private static Logger log = Logger.getLogger(LoginValidator.class.getName());

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        User user = (User) o;
        if (user == null) {
            errors.rejectValue("name", "Not Specified", null, "Value required.");
        } else {
            log.info("Validating with " + user);

            if (!user.getUsername().equals("admin")) {
                errors.rejectValue("username", "Incorrect Username",
                        null, "Incorrect Username.");
            }
            if (!user.getPassword().equals("admin")) {
                errors.rejectValue("password", "Incorrect Password",
                        null, "Incorrect Password.");
            }
        }
    }
}
