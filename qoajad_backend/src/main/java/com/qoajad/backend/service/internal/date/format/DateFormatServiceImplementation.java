package com.qoajad.backend.service.internal.date.format;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Qualifier("defaultDateFormatService")
public class DateFormatServiceImplementation implements DateFormatService {

    private static final SimpleDateFormat DATE_TIME_MYSQL_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public String convertDateToMySQLDateTime(Date date) {
        return DATE_TIME_MYSQL_FORMAT.format(date);
    }

    @Override
    public Date convertMySQLDateTimeToDate(String dateTime) throws ParseException {
        return DATE_TIME_MYSQL_FORMAT.parse(dateTime);
    }
}
