package com.jetbov.exercise.core.exceptions;

import java.util.stream.IntStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class DefaultException extends RuntimeException {
    public DefaultException(Class<?> clazz, String message, Throwable cause, Object... paramsMap) {
        super(
                DefaultException.generateMessage(
                        clazz.getSimpleName(), message, toMap(String.class, String.class, paramsMap)
                ),
                cause
        );
    }

    public DefaultException(Class<?> clazz, String message, Object... paramsMap) {
        super(
                DefaultException.generateMessage(
                        clazz.getSimpleName(), message, toMap(String.class, String.class, paramsMap
                        )
                )
        );
    }

    protected static String generateMessage(String entity, String message, Map<String, String> searchParams) {
        if (!searchParams.isEmpty()) {
            return StringUtils.capitalize(entity) + " " + message + searchParams;
        }

        return StringUtils.capitalize(entity) + " " + message;
    }

    protected static <K, V> Map<K, V> toMap(Class<K> keyType, Class<V> valueType, Object... entries) {
        if (entries.length % 2 == 1) {
            throw new IllegalArgumentException(
                    "Invalid entries,the number of parameters must be even, as they form a key-value pair"
            );
        }
        return IntStream.range(0, entries.length / 2).
                map(i -> i * 2).
                collect(
                        HashMap::new,
                        (m, i) -> m.put(
                                keyType.cast(entries[i]),
                                valueType.cast(entries[i + 1])
                        ), Map::putAll
                );
    }
}
