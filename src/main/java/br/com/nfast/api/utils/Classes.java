package br.com.nfast.api.utils;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Classes {

    public static Field findField(Class<?> beanType, String fieldName) {
        if (Strings.isNonEmpty(fieldName)) {
            for (Field field : beanType.getDeclaredFields()) {
                if (field.getName().equalsIgnoreCase(fieldName))
                    return field;
            }
        }
        return null;
    }

    public static Field findField(Class<?> type, Class<? extends Annotation> annotation) {
        for (Field field : type.getDeclaredFields()) {
            if (field.isAnnotationPresent(annotation))
                return field;
        }
        return null;
    }

    public static Field findSubclassField(Class<?> rootType, String fullPath) {
        Field field = null;
        Class<?> beanType = rootType;
        String[] chain = Strings.split(fullPath, "\\.");

        for (String property : chain) {
            field = findField(beanType, property);
            if (field == null)
                return null;

            beanType = field.getType();
        }

        return field;
    }

    public static Method findMethod(Class<?> beanType, String methodName) {
        if (Strings.isNonEmpty(methodName)) {
            for (Method method : beanType.getDeclaredMethods()) {
                if (method.getName().equalsIgnoreCase(methodName))
                    return method;
            }
        }
        return null;
    }

    public static Object getValue(Object obj, Field field) {
        if (obj == null)
            return null;

        if (!field.isAccessible())
            field.setAccessible(true);

        try {
            return field.get(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Object getValue(Object obj, String fieldName) {
        if (obj == null)
            return null;

        Field field = findField(obj.getClass(), fieldName);
        if (!field.isAccessible())
            field.setAccessible(true);

        try {
            return field.get(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isAnyEquals(Class<?> beanType, Class<?>... items) {
        for (Class<?> item : items) {
            if (item == beanType)
                return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public static <T> T create(Class<?> type, Object... params) {
        try {
            if (params.length == 0)
                return (T) type.newInstance();

            for (Constructor<?> constructor : type.getConstructors()) {
                if (constructor.getParameterCount() == params.length)
                    return (T) constructor.newInstance(params);
            }
            throw new RuntimeException("Class " + type.getName() + " has no constructor with " + params.length + " parameter(s)");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> scan(String packageName, boolean recursive) {
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            ClassPath cp = ClassPath.from(loader);

            List<String> list = new ArrayList<>();
            ImmutableSet<ClassPath.ClassInfo> set;
            set = cp.getTopLevelClasses();
            for (ClassPath.ClassInfo item : set) {
                String classPackageName = item.getPackageName();
                if (classPackageName.startsWith("BOOT-INF.classes."))
                    classPackageName = classPackageName.substring(17);

                boolean match = false;
                if (recursive) {
                    match = classPackageName.startsWith(packageName);
                } else {
                    match = classPackageName.equalsIgnoreCase(packageName);
                }

                if (match) {
                    String className = item.getName();
                    if (className.startsWith("BOOT-INF.classes."))
                        className = className.substring(17);
                    list.add(className);
                }
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Class<?>> scanClasses(String packageName, boolean recursive, Class<? extends Annotation>... annotations) {
        List<Class<?>> classes = new ArrayList<>();
        List<String> list = scan(packageName, recursive);
        for (String item : list) {
            try {
                Class<?> c = Class.forName(item);
                boolean match = true;
                if (annotations.length > 0) {
                    match = false;
                    for (Class<? extends Annotation> annotation : annotations) {
                        if (c.isAnnotationPresent(annotation)) {
                            match = true;
                            break;
                        }
                    }
                }

                if (match)
                    classes.add(c);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return classes;
    }

    /*public static List<Class<?>> scanClasses(String packageName, boolean recursive, Class<? extends Annotation>... annotations) {
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            ClassPath cp = ClassPath.from(loader);

            List<Class<?>> list = new ArrayList<>();
            ImmutableSet<ClassPath.ClassInfo> set;
            set = cp.getTopLevelClasses();
            for(ClassPath.ClassInfo item: set) {
                String classPackageName = item.getPackageName();
                if(classPackageName.startsWith("BOOT-INF.classes."))
                    classPackageName = classPackageName.substring(17);

                boolean match = false;
                if(recursive) {
                    match = classPackageName.startsWith(packageName);
                } else {
                    match = classPackageName.equalsIgnoreCase(packageName);
                }

                if(match) {
                    Class<?> type = item.load();
                    if(annotations.length > 0) {
                        match = false;
                        for(Class<? extends Annotation> annotation: annotations) {
                            if(type.isAnnotationPresent(annotation)) {
                                match = true;
                                break;
                            }
                        }
                    }

                    if(match)
                        list.add(type);
                }
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/

    public static void printSetters(Class<?> type, String name) {
        for (Field field : type.getDeclaredFields()) {
            System.out.println(name + ".set" + Strings.capitalize(field.getName()) + "();");
        }
    }

}