package br.com.nfast.api.utils;

public class StringList implements CharSequence {
    private final StringBuilder content = new StringBuilder();

    public StringList add(Object value) {
        content.append(value).append("\n");
        return this;
    }

    public StringList addAll(Object... values) {
        for (Object value : values)
            content.append(value).append("\n");
        return this;
    }

    public StringList append(Object value) {
        content.append(value);
        return this;
    }

    public StringList appendAll(Object... values) {
        for (Object value : values)
            content.append(value);
        return this;
    }

    public StringList clear() {
        content.setLength(0);
        return this;
    }

    public void saveToFile(String file) {
        Files.saveToFile(file, toString());
    }

    @Override
    public int length() {
        return content.length();
    }

    @Override
    public char charAt(int index) {
        return content.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return content.subSequence(start, end);
    }

    @Override
    public String toString() {
        return content.toString();
    }

}