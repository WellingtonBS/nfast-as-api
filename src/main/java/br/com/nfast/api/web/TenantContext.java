package br.com.nfast.api.web;

import java.util.Optional;

public class TenantContext {
    private static final ThreadLocal<String> holder = new InheritableThreadLocal<>();

    public static String get() {
        return Optional.ofNullable(holder.get()).orElse("");
    }

    public static void set(String value) {
        holder.set(value);
    }

    public static void clear() {
        holder.set(null);
    }

}
