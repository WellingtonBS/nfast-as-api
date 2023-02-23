package br.com.nfast.api.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZip {

    public static byte[] gzip(String content) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            GZIPOutputStream gzip = new GZIPOutputStream(out);
            OutputStreamWriter writer = new OutputStreamWriter(gzip, StandardCharsets.UTF_8);
            writer.write(content);
            writer.close();
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] gzip(byte[] content) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            GZIPOutputStream gzip = new GZIPOutputStream(out);
            gzip.write(content);
            gzip.finish();
            gzip.close();
            out.close();
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] gzipFile(String filename) {
        try {
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            FileInputStream input = new FileInputStream(filename);
            GZIPOutputStream gzip = new GZIPOutputStream(out);

            int size;
            while ((size = input.read(buffer)) > 0) {
                gzip.write(buffer, 0, size);
            }

            input.close();
            gzip.finish();
            gzip.close();
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] ungzip(byte[] content) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            GZIPInputStream reader = new GZIPInputStream(new ByteArrayInputStream(content));
            byte[] buf = new byte[1024];
            for (int len; (len = reader.read(buf)) > 0; ) {
                out.write(buf, 0, len);
            }
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String ungzipToString(byte[] content) {
        try {
            StringWriter out = new StringWriter();
            InputStreamReader reader = new InputStreamReader(new GZIPInputStream(new ByteArrayInputStream(content)), StandardCharsets.UTF_8);
            char[] buf = new char[1024];
            for (int len; (len = reader.read(buf)) > 0; ) {
                out.write(buf, 0, len);
            }
            return out.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String gzipAndEncodeB64(String content) {
        return B64.encode(gzip(content));
    }

    public static String gzipAndEncodeB64(byte[] content) {
        return B64.encode(gzip(content));
    }

    public static String gzipFileAndEncodeB64(String filename) {
        return B64.encode(gzipFile(filename));
    }

    public static String decodeB64AndUngzip(String content) {
        return ungzipToString(B64.decodeToBytes(content));
    }

    public static byte[] decodeB64AndUngzipInBytes(String content) {
        return ungzip(B64.decodeToBytes(content));
    }

    public static void decodeB64AndUngzipToFile(String content, String filename) {
        byte[] bytes = ungzip(B64.decodeToBytes(content));
        Files.saveToFile(filename, bytes);
    }

}