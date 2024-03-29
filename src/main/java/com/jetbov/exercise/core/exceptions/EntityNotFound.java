package com.jetbov.exercise.core.exceptions;

public class EntityNotFound extends DefaultException {

    public EntityNotFound(Class<?> clazz, String message, Throwable cause, Object... paramsMap) {
        super(clazz, message, cause, paramsMap);
    }

    public EntityNotFound(Class<?> clazz, Object... paramsMap) {
        super(clazz, "was not found by parameter(s): ", paramsMap);
    }

}
