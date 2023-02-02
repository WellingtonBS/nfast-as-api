package br.com.nfast.api.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Numbers {

    public static boolean isEmpty(Integer item) {
        return (item == null) || (item <= 0);
    }

    public static boolean isNonEmpty(Integer item) {
        return (item != null) && (item > 0);
    }

    public static boolean isEmpty(Long item) {
        return (item == null) || (item <= 0l);
    }

    public static boolean isNonEmpty(Long item) {
        return (item != null) && (item > 0l);
    }

    public static boolean isEmpty(Double item) {
        return (item == null) || (item <= 0.0);
    }

    public static boolean isNonEmpty(Double item) {
        return (item != null) && (item > 0.0);
    }

    public static boolean isEmpty(BigDecimal item) {
        return (item == null) || (item.compareTo(BigDecimal.ZERO) <= 0);
    }

    public static boolean isNonEmpty(BigDecimal item) {
        return (item != null) && (item.compareTo(BigDecimal.ZERO) > 0.0);
    }

    public static boolean isEmpty(BigInteger item) {
        return (item == null) || (item.compareTo(BigInteger.ZERO) <= 0);
    }

    public static boolean isNonEmpty(BigInteger item) {
        return (item != null) && (item.compareTo(BigInteger.ZERO) > 0.0);
    }

    public static boolean isZeroOrNull(Integer item) {
        return (item == null) || item.equals(0);
    }

    public static boolean isZeroOrNull(Long item) {
        return (item == null) || item.equals(0L);
    }

    public static boolean isZeroOrNull(Double item) {
        return (item == null) || item.equals(0.0);
    }

    public static boolean isZeroOrNull(BigDecimal item) {
        return (item == null) || (item.compareTo(BigDecimal.ZERO) == 0);
    }

    public static boolean isZeroOrNull(BigInteger item) {
        return (item == null) || (item.compareTo(BigInteger.ZERO) == 0);
    }

    public static boolean isAnyEmpty(Integer... items) {
        for (Integer item : items) {
            if ((item == null) || (item <= 0)) return true;
        }
        return false;
    }

    public static boolean isAnyNonEmpty(Integer... items) {
        for (Integer item : items) {
            if ((item != null) && (item > 0)) return true;
        }
        return false;
    }

    public static boolean isAnyEmpty(Long... items) {
        for (Long item : items) {
            if ((item == null) || (item <= 0l)) return true;
        }
        return false;
    }

    public static boolean isAnyNonEmpty(Long... items) {
        for (Long item : items) {
            if ((item != null) && (item > 0l)) return true;
        }
        return false;
    }

    public static boolean isAnyEmpty(Double... items) {
        for (Double item : items) {
            if ((item == null) || (item <= 0.0)) return true;
        }
        return false;
    }

    public static boolean isAnyNonEmpty(Double... items) {
        for (Double item : items) {
            if ((item != null) && (item > 0.0)) return true;
        }
        return false;
    }

    public static boolean isAnyEmpty(BigDecimal... items) {
        for (BigDecimal item : items) {
            if (isEmpty(item))
                return true;
        }
        return false;
    }

    public static boolean isAnyNonEmpty(BigDecimal... items) {
        for (BigDecimal item : items) {
            if (isNonEmpty(item))
                return true;
        }
        return false;
    }

    public static boolean isAnyEmpty(BigInteger... items) {
        for (BigInteger item : items) {
            if (isEmpty(item))
                return true;
        }
        return false;
    }

    public static boolean isAnyNonEmpty(BigInteger... items) {
        for (BigInteger item : items) {
            if (isNonEmpty(item))
                return true;
        }
        return false;
    }

    public static boolean isAllEmpty(Integer... items) {
        for (Integer item : items) {
            if ((item != null) && (item > 0)) return false;
        }
        return true;
    }

    public static boolean isAllNonEmpty(Integer... items) {
        for (Integer item : items) {
            if ((item == null) || (item <= 0)) return false;
        }
        return true;
    }

    public static boolean isAllEmpty(Long... items) {
        for (Long item : items) {
            if ((item != null) && (item > 0l)) return false;
        }
        return true;
    }

    public static boolean isAllNonEmpty(Long... items) {
        for (Long item : items) {
            if ((item == null) || (item <= 0l)) return false;
        }
        return true;
    }

    public static boolean isAllEmpty(Double... items) {
        for (Double item : items) {
            if ((item != null) && (item > 0.0)) return false;
        }
        return true;
    }

    public static boolean isAllNonEmpty(Double... items) {
        for (Double item : items) {
            if ((item == null) || (item <= 0.0)) return false;
        }
        return true;
    }

    public static boolean isAllEmpty(BigDecimal... items) {
        for (BigDecimal item : items) {
            if (isNonEmpty(item))
                return false;
        }
        return true;
    }

    public static boolean isAllNonEmpty(BigDecimal... items) {
        for (BigDecimal item : items) {
            if (isEmpty(item))
                return false;
        }
        return true;
    }

    public static boolean isAllNonEmpty(BigInteger... items) {
        for (BigInteger item : items) {
            if (isEmpty(item))
                return false;
        }
        return true;
    }

    public static boolean isAllEmpty(BigInteger... items) {
        for (BigInteger item : items) {
            if (isNonEmpty(item))
                return false;
        }
        return true;
    }

    public static Double round(Double value, int decimals) {
        if (value == null) return 0.0;

        return BigDecimal.valueOf(value).setScale(decimals, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static Integer round(Double value) {
        if (value == null) return 0;

        return (int) BigDecimal.valueOf(value).setScale(0, BigDecimal.ROUND_UP).doubleValue();
    }

    public static Double truncRound(Boolean trunc, Double value, int decimals) {
        if (trunc)
            return trunc(value, decimals);
        else
            return round(value, decimals);
    }

    public static Double trunc(Double value, int decimals) {
        if (value == null) return 0.0;

        return BigDecimal.valueOf(value).setScale(decimals, BigDecimal.ROUND_DOWN).doubleValue();
    }

    public static Integer trunc(Double value) {
        if (value == null) return 0;
        return (int) value.doubleValue();
    }

    public static int random(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    public static double random(double min, double max) {
        return min + (Math.random() * ((max - min) + 1));
    }

    public static Double ifNull(Double value, Double def) {
        return value != null ? value : def;
    }

    public static Double zeroIfNull(Double value) {
        return value != null ? value : 0.0;
    }

    public static Integer ifNull(Integer value, Integer def) {
        return value != null ? value : def;
    }

    public static Integer zeroIfNull(Integer value) {
        return value != null ? value : 0;
    }

    public static Long ifNull(Long value, Long def) {
        return value != null ? value : def;
    }

    public static Long zeroIfNull(Long value) {
        return value != null ? value : 0l;
    }

    public static int asInt(Object value) {
        try {
            String text = value != null ? value.toString() : null;
            return Strings.isEmpty(text) ? 0 : Integer.valueOf(text);
        } catch (Exception e) {
            return 0;
        }
    }

    public static Integer asInteger(Object value, Integer def) {
        try {
            String text = value != null ? value.toString() : null;
            return Strings.isEmpty(text) ? def : Integer.valueOf(text);
        } catch (Exception e) {
            return def;
        }
    }

    public static long asLong(Object value) {
        try {
            String text = value != null ? value.toString() : null;
            return Strings.isEmpty(text) ? 0 : Long.valueOf(text);
        } catch (Exception e) {
            return 0;
        }
    }

    public static Long asLong(Object value, Long def) {
        try {
            String text = value != null ? value.toString() : null;
            return Strings.isEmpty(text) ? def : Long.valueOf(text);
        } catch (Exception e) {
            return def;
        }
    }

    public static double asDouble(Object value) {
        try {
            String text = value != null ? value.toString() : null;
            return Strings.isEmpty(text) ? 0.0 : Double.valueOf(text.replace(',', '.'));
        } catch (Exception e) {
            return 0.0;
        }
    }

    public static Double asDouble(Object value, Double def) {
        try {
            String text = value != null ? value.toString() : null;
            return Strings.isEmpty(text) ? def : Double.valueOf(text.replace(',', '.'));
        } catch (Exception e) {
            return def;
        }
    }

    public static BigDecimal asBigDecimal(Object value) {
        try {
            String text = value != null ? value.toString() : null;
            return Strings.isEmpty(text) ? BigDecimal.ZERO : new BigDecimal(text.replace(',', '.'));
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    public static BigDecimal asBigDecimal(Object value, BigDecimal def) {
        try {
            String text = value != null ? value.toString() : null;
            return Strings.isEmpty(text) ? def : new BigDecimal(text.replace(',', '.'));
        } catch (Exception e) {
            return def;
        }
    }

    public static BigInteger asBigInteger(Object value) {
        try {
            String text = value != null ? value.toString() : null;
            return Strings.isEmpty(text) ? BigInteger.ZERO : new BigInteger(text.replace(',', '.'));
        } catch (Exception e) {
            return BigInteger.ZERO;
        }
    }

    public static BigInteger asBigInteger(Object value, BigInteger def) {
        try {
            String text = value != null ? value.toString() : null;
            return Strings.isEmpty(text) ? def : new BigInteger(text.replace(',', '.'));
        } catch (Exception e) {
            return def;
        }
    }

    public static String format(Double value, int decimals, int size) {
        return Strings.lpad(format(value, decimals), "0", size);
    }

    public static String format(Double value, int decimals) {
        return getFormat(decimals).format(value == null ? 0.0 : value);
    }

    public static String format(Double value) {
        return format(value, 2);
    }

    public static DecimalFormat getFormat(int decimals) {
        DecimalFormat format = new DecimalFormat();
        format.setGroupingUsed(true);
        format.setDecimalSeparatorAlwaysShown(decimals > 0);
        format.setParseIntegerOnly(decimals == 0);
        format.setMinimumFractionDigits(decimals);
        format.setMaximumFractionDigits(decimals);
        format.setNegativePrefix("-");

        format.getDecimalFormatSymbols().setGroupingSeparator('.');
        if (decimals > 0)
            format.getDecimalFormatSymbols().setDecimalSeparator(',');

        return format;
    }

    public static boolean in(Integer value, Integer... values) {
        for (Integer item : values) {
            if ((item != null) && item.equals(value))
                return true;
        }
        return false;
    }

    public static boolean in(Long value, Long... values) {
        for (Long item : values) {
            if ((item != null) && item.equals(value))
                return true;
        }
        return false;
    }

    public static boolean equals(Long a, Long b) {
        if ((a != null) && (b != null)) return a.equals(b);
        return a == b;
    }

    public static boolean diff(Long a, Long b) {
        if ((a != null) && (b != null)) return !a.equals(b);
        return a != b;
    }

    public static boolean equals(Integer a, Integer b) {
        if ((a != null) && (b != null)) return a.equals(b);
        return a == b;
    }

    public static boolean diff(Integer a, Integer b) {
        if ((a != null) && (b != null)) return !a.equals(b);
        return a != b;
    }

    public static boolean equals(Double a, Double b) {
        if ((a != null) && (b != null)) return a.equals(b);
        return a == b;
    }

    public static boolean equals(Double a, Double b, int decimals) {
        return equals(round(a, decimals), round(b, decimals));
    }

    public static boolean diff(Double a, Double b) {
        if ((a != null) && (b != null)) return !a.equals(b);
        return a != b;
    }

    public static boolean diff(Double a, Double b, int decimals) {
        return diff(round(a, decimals), round(b, decimals));
    }

    public static <T> T ifNull(T value, T def) {
        return value != null ? value : def;
    }

    public static Integer firstNonEmpty(Integer... values) {
        for (Integer value : values) {
            if (isNonEmpty(value))
                return value;
        }
        return null;
    }

    public static Long firstNonEmpty(Long... values) {
        for (Long value : values) {
            if (isNonEmpty(value))
                return value;
        }
        return null;
    }

    public static Double firstNonEmpty(Double... values) {
        for (Double value : values) {
            if (isNonEmpty(value))
                return value;
        }
        return null;
    }

    public static Double abs(Double value) {
        if (value == null)
            return value;

        return value > 0.0 ? value : (value * -1);
    }

    public static Integer[] split(String text, String delimiter) {
        String[] items = Strings.split(text, delimiter);
        List<Integer> list = new ArrayList<>();
        for (String item : items)
            list.add(Numbers.asInt(item));

        return list.toArray(new Integer[0]);
    }

    public static Long min(Long... values) {
        if (values.length == 0)
            return null;

        Long result = null;
        for (Long value : values) {
            if (value != null) {
                if ((result == null) || (value < result))
                    result = value;
            }
        }

        return result;
    }

    public static Long max(Long... values) {
        if (values.length == 0)
            return null;

        Long result = null;
        for (Long value : values) {
            if (value != null) {
                if ((result == null) || (value > result))
                    result = value;
            }
        }

        return result;
    }

    public static Integer min(Integer... values) {
        if (values.length == 0)
            return null;

        Integer result = null;
        for (Integer value : values) {
            if (value != null) {
                if ((result == null) || (value < result))
                    result = value;
            }
        }

        return result;
    }

    public static Integer max(Integer... values) {
        if (values.length == 0)
            return null;

        Integer result = null;
        for (Integer value : values) {
            if (value != null) {
                if ((result == null) || (value > result))
                    result = value;
            }
        }

        return result;
    }

    public static Double min(Double... values) {
        if (values.length == 0)
            return null;

        Double result = null;
        for (Double value : values) {
            if (value != null) {
                if ((result == null) || (value < result))
                    result = value;
            }
        }

        return result;
    }

    public static Double max(Double... values) {
        if (values.length == 0)
            return null;

        Double result = null;
        for (Double value : values) {
            if (value != null) {
                if ((result == null) || (value > result))
                    result = value;
            }
        }

        return result;
    }

}