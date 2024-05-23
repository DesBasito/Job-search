package kg.attractor.ht49.dto.educations.customAnnotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DateRangeValidatorForEdition.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDateRangeEdition {
    String message() default "{pastEducation}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
