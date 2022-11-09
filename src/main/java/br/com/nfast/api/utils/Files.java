package br.com.nfast.api.utils;

import com.google.common.io.CharStreams;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Files {

    public static boolean exists(String filename) {
        try {
            Path path = Paths.get(filename);
            return java.nio.file.Files.exists(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void delete(String filename) {
        try {
            Path path = Paths.get(filename);
            java.nio.file.Files.deleteIfExists(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveToFile(String filename, String content) {
        saveToFile(filename, content.getBytes());
    }

    public static void saveToFile(String filename, byte[] content) {
        try {
            Path path = Paths.get(filename);
            java.nio.file.Files.createDirectories(path.getParent());
            java.nio.file.Files.write(path, content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String loadFromFile(String filename) {
        return new String(loadBytesFromFile(filename));
    }

    public static byte[] loadBytesFromFile(String filename) {
        try {
            Path path = Paths.get(filename);
            return java.nio.file.Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String loadFromInputStream(String path) {
        try {
            InputStream is = Files.class.getClassLoader().getResourceAsStream(path);
            return CharStreams.toString(new InputStreamReader(is));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static Properties loadProperties(String filename) {
        try {
            Properties props = new Properties();
            FileInputStream input = new FileInputStream(new File(filename));
            props.load(new InputStreamReader(input, Charset.forName("UTF-8")));
            return props;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void saveProperties(Properties props, String filename) {
        try {
            props.store(new FileOutputStream(filename), null);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static List<String> scanFilenames(String path, Predicate<Path> filter) {
        try {
            Path dir = Paths.get(path);
            if (!java.nio.file.Files.exists(dir))
                return new ArrayList<>();

            Stream<Path> stream = java.nio.file.Files.walk(dir);
            if (filter != null)
                stream = stream.filter(filter);
            return stream.map(Path::toString).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String getFileName(String filepath, boolean withExtension) {
        if (filepath == null)
            return null;

        final int lastUnixPos = filepath.lastIndexOf('/');
        final int lastWindowsPos = filepath.lastIndexOf('\\');
        int index = Math.max(lastUnixPos, lastWindowsPos);

        String filename = filepath.substring(index + 1);
        if (!withExtension) {
            index = filename.lastIndexOf('.');
            if (index > 0)
                filename = filename.substring(0, index);
        }

        return filename;
    }

    public static String getFileDir(String filepath) {
        if (filepath == null)
            return null;

        final int lastUnixPos = filepath.lastIndexOf('/');
        final int lastWindowsPos = filepath.lastIndexOf('\\');
        int index = Math.max(lastUnixPos, lastWindowsPos);

        String dir = filepath.substring(0, index);
        return dir;
    }

    public static String getFileExt(String filename) {
        if (filename == null)
            return null;

        int index = filename.lastIndexOf('.');
        if (index < 0)
            return null;

        return filename.substring(index + 1);
    }

    public static boolean hasExtension(String filename, String extension) {
        return Strings.equals(getFileExt(filename), extension);
    }

}