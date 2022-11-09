package br.com.nfast.api.utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class Dates {

    public static String dayOfMonthExt(LocalDate date) {
        return Strings.lpad(date.getDayOfMonth(), "0", 2);
    }

    public static String dayOfMonthExt(LocalDateTime date) {
        return Strings.lpad(date.getDayOfMonth(), "0", 2);
    }

    public static String dayOfWeekExt(LocalDate date) {
        switch (date.getDayOfWeek()) {
            case SUNDAY:
                return "Domingo";
            case MONDAY:
                return "Segunda";
            case TUESDAY:
                return "Terça";
            case WEDNESDAY:
                return "Quarta";
            case THURSDAY:
                return "Quinta";
            case FRIDAY:
                return "Sexta";
            case SATURDAY:
                return "Sábado";
            default:
                return "";
        }
    }

    public static String dayOfWeekExtAbbr(LocalDate date) {
        switch (date.getDayOfWeek()) {
            case SUNDAY:
                return "Dom";
            case MONDAY:
                return "Seg";
            case TUESDAY:
                return "Ter";
            case WEDNESDAY:
                return "Qua";
            case THURSDAY:
                return "Qui";
            case FRIDAY:
                return "Sex";
            case SATURDAY:
                return "Sáb";
            default:
                return "";
        }
    }

    public static String dayOfWeekExt(LocalDateTime date) {
        return dayOfWeekExt(date.toLocalDate());
    }

    public static String dayOfWeekExtAbbr(LocalDateTime date) {
        return dayOfWeekExtAbbr(date.toLocalDate());
    }

    public static String monthOfYearExt(int month) {
        switch (month) {
            case 1:
                return "Janeiro";
            case 2:
                return "Fevereiro";
            case 3:
                return "Março";
            case 4:
                return "Abril";
            case 5:
                return "Maio";
            case 6:
                return "Junho";
            case 7:
                return "Julho";
            case 8:
                return "Agosto";
            case 9:
                return "Setembro";
            case 10:
                return "Outubro";
            case 11:
                return "Novembro";
            case 12:
                return "Dezembro";
            default:
                return "";
        }
    }

    public static String monthOfYearExtAbbr(int month) {
        switch (month) {
            case 1:
                return "Jan";
            case 2:
                return "Fev";
            case 3:
                return "Mar";
            case 4:
                return "Abr";
            case 5:
                return "Mai";
            case 6:
                return "Jun";
            case 7:
                return "Jul";
            case 8:
                return "Ago";
            case 9:
                return "Set";
            case 10:
                return "Out";
            case 11:
                return "Nov";
            case 12:
                return "Dez";
            default:
                return "";
        }
    }

    public static String monthOfYearExt(LocalDate date) {
        return monthOfYearExt(date.getMonthValue());
    }

    public static String monthOfYearExtAbbr(LocalDate date) {
        return monthOfYearExtAbbr(date.getMonthValue());
    }

    public static String monthOfYearExt(LocalDateTime date) {
        return monthOfYearExt(date.getMonthValue());
    }

    public static String monthOfYearExtAbbr(LocalDateTime date) {
        return monthOfYearExtAbbr(date.getMonthValue());
    }

    public static String format(TemporalAccessor date, String format) {
        if (date == null)
            return null;
        return DateTimeFormatter.ofPattern(format).format(date);
    }

    public static String format(LocalDate date) {
        if (date == null)
            return null;
        return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(date);
    }

    public static String format(LocalDateTime date) {
        if (date == null)
            return null;
        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(date);
    }

    public static String format(LocalDateTime date, CustomFormat format) {
        if (date == null)
            return null;

        StringBuilder tmp = new StringBuilder();
        switch (format) {
            case A: { //DMMMHHMM
                tmp.append(Strings.lpad(date.getDayOfMonth(), "0", 2)).append(" ");
                tmp.append(monthOfYearExt(date).toLowerCase());
                tmp.append(" ").append(format(date, "HH:mm"));
            }
            break;
            case B: { //DWDMMMHHMM
                tmp.append(dayOfWeekExtAbbr(date).toLowerCase());
                tmp.append(" ").append(Strings.lpad(date.getDayOfMonth(), "0", 2));
                tmp.append(" ").append(monthOfYearExt(date).toLowerCase());
                tmp.append(" ").append(format(date, "HH:mm"));
            }
            break;
            case C: { //DWDMMMYYYYHHMM
                tmp.append(dayOfWeekExtAbbr(date).toLowerCase());
                tmp.append(" ").append(Strings.lpad(date.getDayOfMonth(), "0", 2));
                tmp.append(" ").append(monthOfYearExt(date).toLowerCase());
                tmp.append(" ").append(yearOf(date));
                tmp.append(" ").append(format(date, "HH:mm"));
            }
            break;
            case D: {
                tmp.append(Strings.lpad(date.getDayOfMonth(), "0", 2)).append(" ");
                tmp.append(monthOfYearExtAbbr(date).toLowerCase());
                tmp.append(" ").append(format(date, "HH:mm"));
            }
            break;
            case E: {
                tmp.append(dayOfWeekExtAbbr(date).toLowerCase());
                tmp.append(" ").append(Strings.lpad(date.getDayOfMonth(), "0", 2));
                tmp.append(" ").append(monthOfYearExtAbbr(date).toLowerCase());
                tmp.append(" ").append(format(date, "HH:mm"));
            }
            break;
            default:
                break;
        }

        return tmp.toString();
    }

    public static String format(LocalTime time) {
        if (time == null)
            return null;
        return DateTimeFormatter.ofPattern("HH:mm").format(time);
    }

    public static String format(LocalTime time, String format) {
        if (time == null)
            return null;
        return DateTimeFormatter.ofPattern(format).format(time);
    }

    public static LocalDate toDate(String text) {
        return toDate(text, "dd/MM/yyyy");
    }

    public static LocalDate toDate(String text, String format) {
        try {
            return LocalDate.parse(text, DateTimeFormatter.ofPattern(format));
        } catch (Exception e) {
            return null;
        }
    }

    public static LocalDateTime toDateTime(String text) {
        return toDateTime(text, "dd/MM/yyyy HH:mm:ss");
    }

    public static LocalDateTime toDateTime(String text, String format) {
        try {
            return LocalDateTime.parse(text, DateTimeFormatter.ofPattern(format));
        } catch (Exception e) {
            return null;
        }
    }

    public static LocalTime toTime(String text) {
        return toTime(text, "HH:mm:ss");
    }

    public static LocalTime toTime(String text, String format) {
        try {
            return LocalTime.parse(text, DateTimeFormatter.ofPattern(format));
        } catch (Exception e) {
            return null;
        }
    }

    public static LocalDate utcToDate(String text) {
        return utcToDateTime(text).toLocalDate();
    }

    public static LocalDateTime utcToDateTime(String text) {
        if ((text != null) && (text.length() == 19))
            text += "-00:00";
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(text);
        return zonedDateTime.toLocalDateTime();
    }

    public static LocalDate randomDate() {
        return LocalDate.now().minusDays(Numbers.random(0, 10 * 365)); //Intervalo de 10 anos
    }

    public static LocalDateTime randomDateTime() {
        return LocalDateTime.now().minusSeconds(Numbers.random(0, 10 * 365 * 24 * 60 * 60)); //Intervalo de 10 anos
    }

    public static LocalDate firstDayOfMonth(LocalDate date) {
        if (date == null)
            return null;

        return date.withDayOfMonth(1);
    }

    public static LocalDate lastDayOfMonth(LocalDate date) {
        if (date == null)
            return null;

        return date.withDayOfMonth(date.lengthOfMonth());
    }

    public static LocalDate firstDayOfYear(LocalDate date) {
        if (date == null)
            return null;

        return date.withMonth(1).withDayOfMonth(1);
    }

    public static LocalDate lastDayOfYear(LocalDate date) {
        if (date == null)
            return null;

        return date.withMonth(12).withDayOfMonth(31);
    }

    public static int yearOf(LocalDate date) {
        return date.getYear();
    }

    public static int yearOf(LocalDateTime date) {
        return date.getYear();
    }

    public static String format(Date date) {
        Format df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(date);
    }

    public static String format(Date date, String format) {
        Format df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static Date toDate(LocalDate date) {
        if (date == null)
            return null;

        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date toDate(LocalDateTime date) {
        if (date == null)
            return null;

        return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate toLocalDate(Date date) {
        if (date == null)
            return null;

        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        if (date == null)
            return null;

        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDateTime startOfDay() {
        return startOfDay(LocalDateTime.now());
    }

    public static LocalDateTime startOfDay(LocalDate date) {
        if (date == null)
            return null;

        return date.atStartOfDay();
    }

    public static LocalDateTime startOfDay(LocalDateTime date) {
        if (date == null)
            return null;

        return date.toLocalDate().atStartOfDay();
    }

    public static LocalDateTime endOfDay() {
        return endOfDay(LocalDateTime.now());
    }

    public static LocalDateTime endOfDay(LocalDate date) {
        if (date == null)
            return null;

        LocalDateTime dateTime = date.atStartOfDay();
        return dateTime.withHour(23).withMinute(59).withSecond(59);
    }

    public static LocalDateTime endOfDay(LocalDateTime date) {
        if (date == null)
            return null;

        LocalDateTime dateTime = date.toLocalDate().atStartOfDay();
        return dateTime.withHour(23).withMinute(59).withSecond(59);
    }

    public static <T> LocalDate min(List<T> items, Function<T, LocalDate> supplier) {
        if (items == null)
            return null;

        LocalDate value = null;
        for (T item : items) {
            LocalDate date = supplier.apply(item);
            if (date != null) {
                if ((value == null) || date.isBefore(value))
                    value = date;
            }
        }

        return value;
    }

    public static <T> LocalDate max(List<T> items, Function<T, LocalDate> supplier) {
        if (items == null)
            return null;

        LocalDate value = null;
        for (T item : items) {
            LocalDate date = supplier.apply(item);
            if (date != null) {
                if ((value == null) || date.isAfter(value))
                    value = date;
            }
        }

        return value;
    }

    public static boolean isValid(Date date) {
        if (date == null)
            return false;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR) > 1899;
    }

    public static Date trunc(Date date) {
        if (date == null)
            return null;

        return toDate(startOfDay(toLocalDate(date)));
    }

    public static boolean isValid(LocalDate date) {
        if (date == null)
            return false;

        return date.getYear() > 1899;
    }

    public static boolean isValid(LocalDateTime date) {
        if (date == null)
            return false;

        return date.getYear() > 1899;
    }

    public static Date startDate(Date date) {
        if (date == null)
            return null;

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static boolean dateAfter(Date a, Date b, boolean time) {
        if ((a != null) && (b != null)) {
            return time ? a.after(b) : startDate(a).after(startDate(b));
        }
        return false;
    }

    public static boolean dateAfter(Date a, Date b) {
        return dateAfter(a, b, false);
    }

    public static boolean dateBefore(Date a, Date b, boolean time) {
        if ((a != null) && (b != null)) {
            return time ? a.before(b) : startDate(a).before(startDate(b));
        }
        return false;
    }

    public static boolean dateBefore(Date a, Date b) {
        return dateBefore(a, b, false);
    }

    public static boolean dateEquals(Date a, Date b, boolean time) {
        if ((a != null) && (b != null)) {
            return !(dateAfter(a, b, time) || dateBefore(a, b, time));
        }
        return false;
    }

    public static boolean dateEquals(LocalDateTime a, LocalDateTime b) {
        return dateEquals(Date.from(a.atZone(ZoneId.systemDefault()).toInstant()), Date.from(b.atZone(ZoneId.systemDefault()).toInstant()), false);
    }

    public static boolean dateEquals(Date a, Date b) {
        return dateEquals(a, b, false);
    }

    public static boolean between(LocalDateTime date, LocalDateTime start, LocalDateTime end) {
        return (date.isAfter(start) || date.isEqual(start)) && (date.isBefore(end) || date.isEqual(end));
    }

    public static boolean notBetween(LocalDateTime date, LocalDateTime start, LocalDateTime end) {
        return !between(date, start, end);
    }

    public static Integer daysBetween(LocalDate start, LocalDate end) {
        if ((start == null) || (end == null))
            return null;

        Long days = ChronoUnit.DAYS.between(start, end);
        return days.intValue();
    }

    public static Integer daysSince(LocalDate start) {
        return daysBetween(start, LocalDate.now());
    }

    public enum CustomFormat {
        A, B, C, D, E, F
    }

}
