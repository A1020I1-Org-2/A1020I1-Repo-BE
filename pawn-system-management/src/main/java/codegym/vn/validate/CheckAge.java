package codegym.vn.validate;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AgeValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)

public @interface CheckAge {
    String message() default "Phải lớn hơn 18 tuổi";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
