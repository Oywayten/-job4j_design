package ru.job4j.exercises;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CalendarDemo {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        System.out.print("Date: ");
        System.out.print(calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ROOT) + " ");
        System.out.print(calendar.get(Calendar.DATE) + " ");
        System.out.println(calendar.get(Calendar.YEAR));
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        System.out.print("Is it a leap year now?");
        boolean isLeapYear = gregorianCalendar.isLeapYear(calendar.get(Calendar.YEAR));
        System.out.println(isLeapYear);
        System.out.println(calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE));
        calendar.set(Calendar.HOUR, 10);
        calendar.set(Calendar.MINUTE, 10);
        System.out.println("New calendar time");
        System.out.println(calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE));
        System.out.print("Era is now: ");
        System.out.println(calendar.getDisplayName(Calendar.ERA, Calendar.LONG, Locale.ROOT));
        System.out.print("Next leap year: ");
        for (int i = calendar.get(Calendar.YEAR); i < calendar.get(Calendar.YEAR) + 5; i++) {
            if (gregorianCalendar.isLeapYear(i)) {
                System.out.println(i);
            }
        }
    }
}
