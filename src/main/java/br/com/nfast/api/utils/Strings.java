package br.com.nfast.api.utils;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Strings {

    public static final String[] LETTERS = new String[]{
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "x", "y", "w", "z",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "X", "Y", "W", "Z"
    };
    public static final String[] LETTERS_LOWER_CASE = new String[]{
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "x", "y", "w", "z"
    };
    public static final String[] LETTERS_UPPER_CASE = new String[]{
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "X", "Y", "W", "Z"
    };

    public static boolean isEmpty(String item) {
        return (item == null) || item.isEmpty();
    }

    public static boolean isNonEmpty(String item) {
        return (item != null) && !item.isEmpty();
    }

    public static boolean isAnyEmpty(String... items) {
        for (String item : items) {
            if ((item == null) || item.isEmpty()) return true;
        }
        return false;
    }

    public static boolean isAnyNonEmpty(String... items) {
        for (String item : items) {
            if ((item != null) && (!item.isEmpty())) return true;
        }
        return false;
    }

    public static boolean isAllEmpty(String... items) {
        for (String item : items) {
            if ((item != null) && (!item.isEmpty())) return false;
        }
        return true;
    }

    public static boolean isAllNonEmpty(String... items) {
        for (String item : items) {
            if ((item == null) || item.isEmpty()) return false;
        }
        return true;
    }

    public static String firstNonEmpty(String... items) {
        for (String item : items) {
            if ((item != null) && !item.isEmpty()) return item;
        }
        return null;
    }

    public static String lpad(Object text, String c, int size) {
        StringBuilder sb = new StringBuilder(text != null ? text.toString() : "");
        for (int i = sb.length(); i < size; i++)
            sb.insert(0, c);
        return sb.toString();
    }

    public static String rpad(Object text, String c, int size) {
        StringBuilder sb = new StringBuilder(text != null ? text.toString() : "");
        for (int i = sb.length(); i < size; i++)
            sb.append(c);
        return sb.toString();
    }

    public static String ltrunc(Object text, int size) {
        if (text == null)
            return null;

        if (text.toString().length() <= size)
            return text.toString();

        String str = text.toString();
        return str.substring(str.length() - size, str.length());
    }

    public static String rtrunc(Object text, int size) {
        if (text == null)
            return null;

        if (text.toString().length() <= size)
            return text.toString();

        String str = text.toString();
        return str.substring(0, size);
    }

    public static String random(String... items) {
        return items[Numbers.random(0, items.length - 1)];
    }

    public static String randomAlpha(int size) {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVXYWZ";
        StringBuilder buf = new StringBuilder();
        for (int i = 1; i <= size; i++)
            buf.append(chars.charAt(Numbers.random(0, chars.length() - 1)));
        return buf.toString();
    }

    public static String randomNumeric(int size) {
        final String chars = "0123456789";
        StringBuilder buf = new StringBuilder();
        for (int i = 1; i <= size; i++)
            buf.append(chars.charAt(Numbers.random(0, chars.length() - 1)));
        return buf.toString();
    }

    public static String randomAlphanumeric(int size) {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVXYWZ0123456789";
        StringBuilder buf = new StringBuilder();
        for (int i = 1; i <= size; i++)
            buf.append(chars.charAt(Numbers.random(0, chars.length() - 1)));
        return buf.toString();
    }

    public static String onlyNumbers(String text) {
        if (text == null) text = "";
        return text.replaceAll("\\D+", "");
    }

    public static String onlyAlphaNumeric(String text) {
        if (text == null) text = "";
        return text.replaceAll("[^A-Za-z0-9]", "");
    }

    public static String requireNonEmpty(String text, String message) {
        if ((text == null) || text.isEmpty()) throw new RuntimeException(message);
        return text;
    }

    public static boolean equals(String a, String b) {
        if ((a != null) && (b != null)) return a.equalsIgnoreCase(b);
        return a == b;
    }

    public static boolean diff(String a, String b) {
        if ((a != null) && (b != null)) return !a.equalsIgnoreCase(b);
        return a != b;
    }

    public static boolean in(String value, String... items) {
        return isAnyEquals(value, items);
    }

    public static boolean notIn(String value, String... items) {
        return isAllDiff(value, items);
    }

    public static boolean isAnyEquals(String value, String... items) {
        for (String item : items) {
            if (equals(value, item))
                return true;
        }
        return false;
    }

    public static boolean isAllEquals(String value, String... items) {
        for (String item : items) {
            if (diff(value, item))
                return false;
        }
        return true;
    }

    public static boolean isAnyDiff(String value, String... items) {
        for (String item : items) {
            if (diff(value, item))
                return true;
        }
        return false;
    }

    public static boolean isAllDiff(String value, String... items) {
        for (String item : items) {
            if (equals(value, item))
                return false;
        }
        return true;
    }

    public static String capitalize(String text) {
        return text != null ? (text.substring(0, 1).toUpperCase() + text.substring(1)) : null;
    }

    public static String capitalizeAll(String text) {
        if (text == null) return null;

        String prev = null;
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            String s = String.valueOf(c);
            if ((prev == null) || prev.equals(" ")) s = s.toUpperCase();

            result.append(s);
            prev = s;
        }

        return result.toString();
    }

    public static String[] split(String text, String delimiter) {
        if (isEmpty(text))
            return new String[0];

        return text.split(delimiter);
    }

    public static List<String> splitIntoList(String text, String delimiter) {
        List<String> list = new ArrayList<>();
        for (String item : split(text, delimiter))
            list.add(item);
        return list;
    }

    public static String splitedValueAt(String text, String delimiter, int index, String def) {
        if (text == null) return def;

        String[] items = text.split(delimiter);
        if (items.length < (index + 1)) return def;

        return items[index];
    }

    public static String quoteItems(String text, String delimiter) {
        String[] array = split(text, delimiter);
        StringBuilder items = new StringBuilder();
        for (String item : array) {
            if (items.length() > 0)
                items.append(delimiter);
            items.append("'").append(item).append("'");
        }
        return items.toString();
    }

    public static Map<String, String> map(String... items) {
        Map<String, String> map = new HashMap<>();
        int index = -1;
        String key = null;
        for (String item : items) {
            index++;
            if (index % 2 == 0) key = item;
            else map.put(key, item);
        }
        return map;
    }

    public static String eval(String value, String... items) {
        int index = -1;
        boolean match = false;
        for (String item : items) {
            index++;
            if ((index % 2) == 0)
                match = equals(value, item);
            else if (match)
                return item;
        }
        return null;
    }

    public static String evalDef(String value, String def, String... items) {
        int index = -1;
        boolean match = false;
        for (String item : items) {
            index++;
            if ((index % 2) == 0)
                match = equals(value, item);
            else if (match)
                return item;
        }
        return def;
    }

    public static String ifEmpty(String value, String def) {
        return (value != null) && (value.length() > 0) ? value : def;
    }

    public static String insertBeforeCapitals(String text, String filler) {
        return text.replaceAll("([A-Z])", filler + "$1");
    }

    public static boolean containsAny(String text, String... texts) {
        if (text != null) {
            String value = text.toLowerCase();
            for (String item : texts) {
                if (item != null) {
                    if (value.contains(item.toLowerCase()))
                        return true;
                }
            }
        }
        return false;
    }

    public static boolean containsAll(String text, String... texts) {
        if ((text == null) || (texts.length <= 0))
            return false;

        String value = text.toLowerCase();
        for (String item : texts) {
            if (item == null)
                return false;
            if (!value.contains(item.toLowerCase()))
                return false;
        }
        return true;
    }

    public static boolean containsLetter(String text) {
        return containsAny(text, LETTERS);
    }

    public static String constantName(String text) {
        String name = text.toUpperCase();
        name = name.replaceAll(" ", "_");
        name = name.replaceAll("-", "_");
        name = name.replaceAll("'", "");
        name = name.replaceAll(",", "");
        name = name.replaceAll("\\(", "_");
        name = name.replaceAll("\\)", "_");
        name = name.replaceAll("\\+", "_");
        name = name.replaceAll("/", "_");
        name = name.replaceAll("\\.", "");
        name = name.replaceAll("\\:", "");
        name = name.replaceAll("\\*", "");

        while (name.startsWith("_"))
            name = name.substring(1);
        while (name.endsWith("_"))
            name = name.substring(0, name.length() - 1);
        while (name.contains("__"))
            name = name.replaceAll("__", "_");

        name = name.replaceAll("Á", "A");
        name = name.replaceAll("Â", "A");
        name = name.replaceAll("Ã", "A");
        name = name.replaceAll("À", "A");
        name = name.replaceAll("É", "E");
        name = name.replaceAll("Ê", "E");
        name = name.replaceAll("Í", "I");
        name = name.replaceAll("Ó", "O");
        name = name.replaceAll("Ô", "O");
        name = name.replaceAll("Õ", "O");
        name = name.replaceAll("Ú", "U");
        name = name.replaceAll("Ç", "C");

        return name;
    }

    public static String[] asArray(String... items) {
        return items;
    }

    public static String initials(String text, int size) {
        String[] splited = text.replace(".", " ").split("\\s+");
        StringBuilder tmp = new StringBuilder();
        int count = 0;
        for (String item : splited) {
            count++;
            tmp.append(item.substring(0, 1).toUpperCase());
            if (count == size)
                break;
        }
        return tmp.toString();
    }

    public static String IsoToUTF8(String text) {
        if (isEmpty(text))
            return text;

        Charset utf8 = Charset.forName("UTF-8");
        Charset iso = Charset.forName("ISO-8859-1");

        ByteBuffer inputBuffer = ByteBuffer.wrap(text.getBytes());

        // decode ISO-8559-1
        CharBuffer data = iso.decode(inputBuffer);

        // encode UTF-8
        ByteBuffer outputBuffer = utf8.encode(data);
        byte[] outputData = outputBuffer.array();

        return new String(outputData);
    }

    public static String UTF8ToIso(String text) {
        if (isEmpty(text))
            return text;

        text = text.replace("–", "-");
        text = text.replace("Â", "A");

        Charset utf8 = Charset.forName("UTF-8");
        Charset iso = Charset.forName("ISO-8859-1");

        ByteBuffer inputBuffer = ByteBuffer.wrap(text.getBytes());

        // decode UTF-8
        CharBuffer data = utf8.decode(inputBuffer);

        // encode ISO-8559-1
        ByteBuffer outputBuffer = iso.encode(data);
        byte[] outputData = outputBuffer.array();

        return new String(outputData);
    }

    public static String valueOf(Object value) {
        return value != null ? value.toString() : null;
    }

    public static String extract(String text, String start, String end) {
        if (text == null)
            return null;

        int startIndex = text.indexOf(start);
        if (startIndex < 0)
            return null;

        startIndex += start.length();
        int endIndex = text.indexOf(end, startIndex);
        if (endIndex < 0)
            return null;

        return text.substring(startIndex, endIndex);
    }

    public static String lcopy(String text, String delimiter, boolean includeDelimiter) {
        if (text == null)
            return null;

        int index = text.indexOf(delimiter);
        if (index < 0)
            return null;

        return text.substring(0, index + (includeDelimiter ? 1 : 0));
    }

    public static String rcopy(String text, String delimiter, boolean includeDelimiter) {
        if (text == null)
            return null;

        int index = text.indexOf(delimiter);
        if (index < 0)
            return null;

        index += delimiter.length();
        return text.substring(index - (includeDelimiter ? 1 : 0));
    }

    public static String leading(String text, String part) {
        if (isEmpty(part))
            return text;

        StringBuilder tmp = new StringBuilder(text);
        while (tmp.indexOf(part) == 0) {
            tmp.delete(0, part.length());
        }
        return tmp.toString();
    }

    public static String trailing(String text, String part) {
        if (isEmpty(part))
            return text;

        StringBuilder tmp = new StringBuilder(text);
        while (tmp.lastIndexOf(part) == (tmp.length() - part.length())) {
            tmp.delete(tmp.length() - part.length(), tmp.length());
        }
        return tmp.toString();
    }

    public static String trim(String text) {
        if (text == null)
            return null;

        return text.replaceAll("\\s", "");
    }

    public static String normali(String text) {
        if (text == null)
            return null;

        String a = Normalizer.normalize(text, Normalizer.Form.NFD);
        return a.replaceAll("[^\\p{ASCII}]", "");
    }

    public static String concat(String separator, String... items) {
        StringBuilder tmp = new StringBuilder();
        for (String item : items) {
            if (isNonEmpty(item)) {
                if ((tmp.length() > 0) && isNonEmpty(separator))
                    tmp.append(separator);
                tmp.append(item);
            }
        }
        return tmp.toString();
    }

    public static String copyFirst(String text, int length) {
        if (text == null)
            return null;
        if (text.length() <= length)
            return text;
        return text.substring(0, length);
    }

    public static String copyLast(String text, int length) {
        if (text == null)
            return null;
        if (text.length() <= length)
            return text;
        return text.substring(text.length() - length);
    }

    public static String firstWord(String text) {
        if (isNonEmpty(text)) {
            String tmp = text.trim();
            int i = tmp.indexOf(' ');
            if (i >= 0)
                return tmp.substring(0, i);
        }
        return text;
    }

    public static <T> String of(T obj, Function<T, String> provider) {
        String value = null;
        if ((obj != null) && (provider != null))
            value = provider.apply(obj);
        return value;
    }

    public static <T> String of(T obj, Function<T, String> provider, String def) {
        String value = null;
        if ((obj != null) && (provider != null))
            value = provider.apply(obj);
        return ifEmpty(value, def);
    }

    public static String replaceAll(String text, Object... items) {
        String result = text;
        String key = null;
        int i = -1;
        for (Object item : items) {
            i++;
            if ((i % 2) == 0) {
                key = item.toString();
            } else {
                result = result.replaceAll(key, Strings.valueOf(item));
            }
        }
        return result;
    }

    public static String replaceWithPrefix(String text, String prefix, Object... items) {
        String result = text;
        String key = null;
        int i = -1;
        for (Object item : items) {
            i++;
            if ((i % 2) == 0) {
                key = item.toString();
            } else {
                result = result.replaceAll(prefix + key, Strings.valueOf(item));
            }
        }
        return result;
    }

}
