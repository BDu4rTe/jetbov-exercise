package com.jetbov.exercise.core.exceptions;

public class CopyFromDto extends DefaultException {

    public <C, D> CopyFromDto(Class<C> clazz, Class<D> dto, Throwable cause, Object... paramsMap) {
        super(clazz,
                String.format(
                        " was not updated, error in copy properties from %s to %s.", clazz, dto
                ),
                cause, paramsMap
        );
    }

}
