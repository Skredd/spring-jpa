package com.example.springjpaalura.utils;

import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static DateTimeFormatter createFormatter() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return formatter;
    }
}
