package br.com.nfast.api.utils;

public class Cast {

    @SuppressWarnings("unchecked")
    public static <T> T of(Object obj) {
        return (T) obj;
    }

}