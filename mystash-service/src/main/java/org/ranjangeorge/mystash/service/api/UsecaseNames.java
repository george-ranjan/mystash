package org.ranjangeorge.mystash.service.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface UsecaseNames {

    Usecase[] value();
}
