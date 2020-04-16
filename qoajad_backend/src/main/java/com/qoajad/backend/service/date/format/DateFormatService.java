package com.qoajad.backend.service.date.format;

import java.text.ParseException;
import java.util.Date;

public interface DateFormatService {

    String convertDateToMySQLDateTime(final Date date);
    Date convertMySQLDateTimeToDate(final String dateTime) throws ParseException;
}
