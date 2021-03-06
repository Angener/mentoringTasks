package custom_annotation.annotation;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Repeatable(ThisCodeSmellsContainer.class)
@Target(value = {CONSTRUCTOR, LOCAL_VARIABLE, METHOD, MODULE,
        TYPE, ANNOTATION_TYPE, FIELD, TYPE_PARAMETER, TYPE_USE,
        PACKAGE, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ThisCodeSmells {
    public String reviewer() default "Unknown";
}
