package br.com.nfast.api.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Objects {

    public static boolean diff(Object a, Object b) {
        return !equals(a, b);
    }

    public static boolean equals(Object a, Object b) {
        if (a == b) return true;
        if ((a != null) && (b != null)) {
            if (a instanceof String)
                return Strings.equals(a.toString(), b.toString());
            else return a.equals(b);
        }
        return false;
    }

    public static boolean in(Object value, Object... values) {
        for (Object item : values) {
            if (equals(item, value))
                return true;
        }
        return false;
    }

    public static Object[] asArray(Object... items) {
        return items;
    }

    @SuppressWarnings("unchecked")
    public static <T> T cast(Object value, Class<T> target) {
        if ((value == null) || (target == null) || (value.getClass() == target))
            return (T) value;

        Object newValue = null;
        if ((value != null) && (target != null) && (value.getClass() != target)) {
            if (value instanceof Integer) {
                if (target == Long.class)
                    newValue = ((Integer) value).longValue();
            } else if (value instanceof String) {
                if (target == Long.class)
                    newValue = Long.valueOf((String) value);
            } else if (value instanceof Date) {
                if (target == LocalDate.class)
                    newValue = Dates.toLocalDate((Date) value);
                else if (target == LocalDateTime.class)
                    newValue = Dates.toLocalDateTime((Date) value);
                else if (target == String.class)
                    newValue = Dates.format((Date) value);
            } else if (value instanceof BigDecimal) {
                if (target == Double.class)
                    newValue = ((BigDecimal) value).doubleValue();
                else if (target == Integer.class)
                    newValue = ((BigDecimal) value).intValue();
                else if (target == Long.class)
                    newValue = ((BigDecimal) value).longValue();
            }

            if (newValue == null)
                newValue = target.cast(value);
        }

        return (T) newValue;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getValue(Object instance, Field field) {
        try {
            if (!field.isAccessible())
                field.setAccessible(true);
            return (T) field.get(instance);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void setValue(Object instance, Field field, Object value) {
        try {
            if (!field.isAccessible())
                field.setAccessible(true);
            field.set(instance, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void setValue(Object instance, String fieldName, Object value) {
        Field field = Classes.findField(instance.getClass(), fieldName);
        if (field != null)
            setValue(instance, field, value);
    }

    public static <T> T clone(T source) {
        T target = Classes.create(source.getClass());
        copy(source, target);
        return target;
    }

    public static void copy(Object source, Object target, String... fields) {
        for (Field field : source.getClass().getDeclaredFields()) {
            if ((fields.length == 0) || Strings.isAnyEquals(field.getName(), fields)) {
                if (!Modifier.isStatic(field.getModifiers()))
                    setValue(target, field, getValue(source, field));
            }
        }
    }

    public static void copyExcept(Object source, Object target, String... fields) {
        for (Field field : source.getClass().getDeclaredFields()) {
            if ((fields.length == 0) || Strings.isAllDiff(field.getName(), fields)) {
                if (!Modifier.isStatic(field.getModifiers()))
                    setValue(target, field, getValue(source, field));
            }
        }
    }

    public static String md5(Object item) {
        StringBuilder sb = new StringBuilder();
        for (Field field : item.getClass().getDeclaredFields()) {
            Object value = Classes.getValue(item, field);
            sb.append(value != null ? value : "null");
        }
        return Crypt.md5(sb);
    }

    public static String md5IncludingFields(Object item, String... fieldNames) {
        StringBuilder sb = new StringBuilder();
        for (String fieldName : fieldNames) {
            Object value = Classes.getValue(item, fieldName);
            sb.append(value != null ? value : "null");
        }
        return Crypt.md5(sb);
    }

    public static String md5ExcludingFields(Object item, String... fieldNames) {
        StringBuilder sb = new StringBuilder();
        for (Field field : item.getClass().getDeclaredFields()) {
            if (Strings.isAllDiff(field.getName(), fieldNames)) {
                Object value = Classes.getValue(item, field);
                sb.append(value != null ? value : "null");
            }
        }
        return Crypt.md5(sb);
    }

    public static boolean isAllNull(Object... items) {
        for (Object item : items) {
            if (item != null) return false;
        }
        return true;
    }

    public static boolean isAnyNull(Object... items) {
        for (Object item : items) {
            if (item == null) return true;
        }
        return false;
    }

    public static boolean isAllNonNull(Object... items) {
        for (Object item : items) {
            if (item == null) return false;
        }
        return true;
    }

    public static boolean isAnyNonNull(Object... items) {
        for (Object item : items) {
            if (item != null) return true;
        }
        return false;
    }

    public static Object eval(Object value, Object... items) {
        int index = -1;
        boolean match = false;
        for (Object item : items) {
            index++;
            if ((index % 2) == 0)
                match = equals(value, item);
            else if (match)
                return item;
        }
        return null;
    }

}