package com.jetbov.exercise.core.exceptions;

public class CrowdedArea extends DefaultException {

    public CrowdedArea(Class<?> clazz, String message, Object... paramsMap) {
        super(clazz, message, paramsMap);
    }
}
