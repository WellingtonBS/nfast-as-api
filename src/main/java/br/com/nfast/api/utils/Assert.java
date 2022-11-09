package br.com.nfast.api.utils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Assert {

    public static void check(boolean condition, String message) {
        if (!condition)
            throw new IllegalArgumentException(message);
    }

    public static void diff(Object a, Object b, String message) {
        check(Objects.diff(a, b), message);
    }

    public static void equals(Object a, Object b, String message) {
        check(Objects.equals(a, b), message);
    }

    public static void nonNull(Object object, String message) {
        check(object != null, message);
    }

    public static void nonEmpty(Integer object, String message) {
        check(Numbers.isNonEmpty(object), message);
    }

    public static void nonEmpty(String object, String message) {
        check(Strings.isNonEmpty(object), message);
    }

    public static boolean existsVar(Class<?> model, String var) {
        Class<?> type = model;
        String[] fields = var.split("\\.");
        for (String field : fields) {
            Field f = getField(type, field);
            if (f == null)
                return false;
            type = f.getType();
        }
        return true;
    }

    public static Field getField(Class<?> model, String name) {
        for (Field field : model.getDeclaredFields()) {
            if (field.getName().equalsIgnoreCase(name))
                return field;
        }
        return null;
    }

    public static Object getValueByVar(Object obj, String var) {
        if (obj == null)
            return null;

        int count = 0;
        Object value = obj;
        String[] fields = var.split("\\.");
        for (String field : fields) {
            count++;
            try {
                value = getValue(value, field);
            } catch (Exception e) {
                value = null;
            }

            if (count == fields.length)
                return value;
            if (value == null)
                return null;
        }
        return null;
    }

    public static <T> T getValue(Object obj, Field field) {
        try {
            if (!field.isAccessible())
                field.setAccessible(true);
            return cast(field.get(obj));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T cast(Object value) {
        return (T) value;
    }

    public static <T> T getValue(Object obj, String field) {
        return getValue(obj, getField(obj.getClass(), field));
    }

    public static boolean empty(Object value) {
        if (value == null)
            return true;

        Class<?> type = value.getClass();
        if (type == String.class)
            return ((String) value).trim().isEmpty();
        else if (type == Integer.class)
            return ((Integer) value) <= 0;
        else if (type == Double.class)
            return ((Double) value) <= 0;
        else if (type == Long.class)
            return ((Long) value) <= 0;
        else if (value instanceof List)
            return ((List<?>) value).isEmpty();

        return (value == null);
    }

    public static boolean filled(Object value) {
        return !empty(value);
    }

    public static String replaceVars(String text, Object obj) {
        List<String> vars = extractAll(text, "{", "}");
        for (String var : vars) {
            Object value = "";
            boolean existsVar = (obj != null) && Assert.existsVar(obj.getClass(), var);
            if (existsVar) {
                value = getValueByVar(obj, var);
                if (Assert.filled(value)) {
                    if (value.getClass() == Double.class)
                        value = Numbers.format((Double) value, 2);
                    else if (value.getClass() == Date.class) {
                        value = ((LocalDate) value).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    }
                } else {
                    value = "";
                }
            }

            if (existsVar || (obj == null))
                text = text.replace("{" + var + "}", value.toString());
        }
        return text;
    }

    public static List<String> extractAll(String text, String ini, String end) {
        List<String> list = new ArrayList<>();
        if (Assert.filled(text)) {
            Matcher m = Pattern.compile("\\" + ini + "(?s)(.*?)\\" + end).matcher(text);
            while (m.find())
                list.add(m.group(1));
        }
        return list;
    }

}