package com.jetbov.exercise.core.exceptions;

public class OxAlreadyInArea extends DefaultException {
    public OxAlreadyInArea(Class<?> clazz, String message, Object... paramsMap) {
        super(clazz, message, paramsMap);
    }
}
