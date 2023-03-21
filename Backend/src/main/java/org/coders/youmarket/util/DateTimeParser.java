package org.coders.youmarket.util;

import java.time.LocalDate;

public class DateTimeParser {
    private DateTimeParser(){}

    public static LocalDate getDateFromFormatPattern(String date){
        return LocalDate.parse(date);
    }
}