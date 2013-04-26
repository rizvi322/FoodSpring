package org.foodspring.validator;

import org.apache.log4j.Logger;
import org.foodspring.domain.Comment;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 4/2/13
 * Time: 12:40 PM
 * To change this template use File | Settings | File Templates.
 */

@Service
public class AddCommentFormValidator implements Validator {

    private static Logger log = Logger.getLogger(AddCommentFormValidator.class.getName());

    @Override
    public boolean supports(Class<?> aClass) {
        return Comment.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        Comment comment = (Comment) o;
        if (comment == null) {
            errors.rejectValue("name", "Not Specified", null, "Value required.");
        } else {
            log.info("Validating with " + comment);

            if (comment.getName() == null || comment.getName() == "") {
                errors.rejectValue("name", "Value Required",
                        null, "Value Required.");
                /*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "value.required");*/
            }
            if (comment.getComment() == null || comment.getComment() == "") {
                errors.rejectValue("comment", "Value Required",
                        null, "Value Required.");
                /*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comment", "value.required");*/
            }
        }
    }
}
