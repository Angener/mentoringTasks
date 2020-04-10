package custom_annotation.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target(value = {CONSTRUCTOR, LOCAL_VARIABLE, METHOD, MODULE,
        TYPE, ANNOTATION_TYPE, FIELD, TYPE_PARAMETER, TYPE_USE,
        PACKAGE, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ThisCodeSmellsContainer {
    ThisCodeSmells[] value();
}
