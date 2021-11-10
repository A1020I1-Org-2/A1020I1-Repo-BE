package codegym.vn.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Long.parseLong;

public class AgeValidator implements ConstraintValidator<CheckAge, String> {
    @Override
    public void initialize(CheckAge checkAge) {
    }

    @Override
    public boolean isValid(String dateOfBirth, ConstraintValidatorContext cxt) {
        if (dateOfBirth == null){
            return false;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date birthday;
        try {
             birthday = format.parse(dateOfBirth);
        } catch (ParseException e) {
            return false;
        }
        Date now = new Date();
        long between = now.getTime() - birthday.getTime();
        int age = (int)(between / (1000 * 60 * 60 * 24)) / 365;
        return age > 18;
    }

}
