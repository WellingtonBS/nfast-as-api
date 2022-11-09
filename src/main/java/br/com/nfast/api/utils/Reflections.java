package br.com.nfast.api.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Reflections {

    public static Field findField(Class<?> clazz, String name) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getName().equalsIgnoreCase(name))
                return field;
        }
        return null;
    }

    public static Field findField(Class<?> clazz, Class<? extends Annotation> annotation) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(annotation))
                return field;
        }
        return null;
    }

    public static Object get(Object instance, Field field) {
        try {
            if (!field.isAccessible()) field.setAccessible(true);
            return field.get(instance);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    public static boolean hasAnnotation(Class<?> target, Class<? extends Annotation> annotation) {
        return target.isAnnotationPresent(annotation);
    }

    public static boolean hasAnyAnnotation(Class<?> target, Class<? extends Annotation>... annotations) {
        for (Class<? extends Annotation> annotation : annotations) {
            if (target.isAnnotationPresent(annotation))
                return true;
        }
        return false;
    }

    public static boolean hasAllAnnotations(Class<?> target, Class<? extends Annotation>... annotations) {
        for (Class<? extends Annotation> annotation : annotations) {
            if (!target.isAnnotationPresent(annotation))
                return false;
        }
        return true;
    }

}