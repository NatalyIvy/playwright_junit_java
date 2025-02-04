package org.example.helpers.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // this can just be used in methods
@Retention(RetentionPolicy.RUNTIME) // the annotation will be available during runtime
public @interface CustomStep {
    String value();
}
