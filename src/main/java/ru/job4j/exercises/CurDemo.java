package ru.job4j.exercises;

import java.util.Currency;
import java.util.Locale;

public class CurDemo {
    public static void main(String[] args) {
        Currency currency = Currency.getInstance("RUB");
        System.out.println("Symbol: " + currency.getSymbol());
        System.out.println("Number of decimal digits by default: " + currency.getDefaultFractionDigits());
        System.out.println(Currency.getAvailableCurrencies());
        System.out.println(currency.getDisplayName(Locale.CANADA));
        System.out.println(currency.getNumericCode());
        System.out.println(currency);
    }
}
