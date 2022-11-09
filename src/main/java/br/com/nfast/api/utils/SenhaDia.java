package br.com.nfast.api.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class SenhaDia {

    public static void main(String[] args) {
        System.out.println(get());
    }

    public static String get() {
        return get(LocalDate.now());
    }

    public static String get(LocalDate date) {
        String dic = "35T5CTI2KWQBJ86PL2VWMZNQNSQBRB629428LZ714X70CA5E";
        while (date.getDayOfWeek() != DayOfWeek.MONDAY)
            date = date.minusDays(1);

        int[] array = new int[10];
        for (int i = 0; i <= 9; i++) {
            int n = 0;
            switch (i) {
                case 0:
                    n = date.getYear() * date.getDayOfMonth();
                    break;
                case 1:
                    n = date.getDayOfMonth() / date.getDayOfWeek().getValue() * date.getMonthValue();
                    break;
                case 2:
                    n = date.getDayOfMonth() * date.getMonthValue();
                    break;
                case 3:
                    n = date.getYear() / date.getDayOfMonth();
                    break;
                case 4:
                    n = date.getDayOfMonth() * date.getDayOfYear();
                    break;
                case 5:
                    n = date.getDayOfMonth() * date.getYear();
                    break;
                case 6:
                    n = date.getDayOfYear() * date.getMonthValue() * date.getDayOfMonth();
                    break;
                case 7:
                    n = date.getYear() * date.getDayOfYear();
                    break;
                case 8:
                    n = date.getYear() / date.getMonthValue() * date.getDayOfMonth();
                    break;
                case 9:
                    n = date.getDayOfMonth() * date.getDayOfWeek().getValue();
                    break;
                default:
                    break;
            }

            n = n / ((i % 2) + 2);
            while (n > (dic.length() - 1))
                n = n / ((i % 2) + 3);

            array[i] = n;
        }

        StringBuilder buffer = new StringBuilder();
        for (int n : array)
            buffer.append(dic.charAt(n));

        return buffer.toString();
    }

}
