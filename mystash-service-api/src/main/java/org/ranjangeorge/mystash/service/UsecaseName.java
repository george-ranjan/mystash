package org.ranjangeorge.mystash.service;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface UsecaseName {

    Usecase value();
}
