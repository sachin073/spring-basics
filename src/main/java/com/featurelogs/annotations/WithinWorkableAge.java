package com.featurelogs.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE,ElementType.TYPE_USE})
@Constraint(validatedBy = WorkableAgeConstraintValidator.class)
public @interface WithinWorkableAge {

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String message() default "age outside valid range{}";

    int min() default 21;

    int max() default 62;

}
