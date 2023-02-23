package br.com.nfast.api.config;

import br.com.nfast.api.utils.Strings;

import java.time.LocalDateTime;

public class Config {
    public static final String API_VERSION = "1.0.38";
    public static final String INSTANCE_ID = Strings.randomAlphanumeric(16).toUpperCase();
    public static final LocalDateTime START_TIME = LocalDateTime.now();

    public static String SERVER_PORT;
    public static String SERVER_FILE;

    public static String AUTH_MODE = "database";
    public static String AUTH_HOST;
    public static String AUTH_PORT;
    public static String AUTH_NAME;
    public static String AUTH_USER;
    public static String AUTH_PASS;

    public static String APPLICATION_NAME;

}
