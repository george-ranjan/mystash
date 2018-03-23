package org.ranjangeorge.mystash.service.api.support;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface UsecaseNames {

    Usecase[] value();
}
