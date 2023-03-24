package org.coders.youmarket.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeParser {
    private DateTimeParser(){}

    public static LocalDate getLocalDateFromFormatPattern(String date){
        return LocalDate.parse(date);
    }
    public static LocalDateTime getLocalDateTimeFromFormatPattern(String date){
        return LocalDateTime.parse(date);
    }

    public static String getStringDateOfLocalDateTimeStandardPattern(LocalDateTime date){
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return date.format(myFormatObj);
    }
}