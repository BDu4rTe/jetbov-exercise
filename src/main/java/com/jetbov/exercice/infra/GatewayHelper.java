package com.jetbov.exercice.infra;

import lombok.NoArgsConstructor;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
public class GatewayHelper {
    public static String[] getNullPropertyNames(Object obj) {
        BeanWrapper source = new BeanWrapperImpl(obj);
        PropertyDescriptor[] descriptors = source.getPropertyDescriptors();
        Set<String> nullProperties = new HashSet<>();

        for (PropertyDescriptor descriptor : descriptors) {
            var propertyName = descriptor.getName();
            Object srcValue = source.getPropertyValue((propertyName));
            if (srcValue == null) nullProperties.add(propertyName);
        }

        return nullProperties.toArray(new String[0]);
    }
}
