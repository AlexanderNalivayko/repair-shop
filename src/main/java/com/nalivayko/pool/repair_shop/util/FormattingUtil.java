package com.nalivayko.pool.repair_shop.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormattingUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(FormattingUtil.class);
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private FormattingUtil() {
    }

    /**
     * Convert Date to string using pattern yyyy-MM-dd HH:mm:ss
     * @param date date you want to convert
     * @return string representation of date
     */
    public static String dateToString(Date date) {
        return date != null ? SIMPLE_DATE_FORMAT.format(date) : null;
    }

    /**
     * Convert string representation of date into Date object
     * @param strDate - string representation of date
     * @return date object
     */
    public static Date stringToDate(String strDate) {
        try {
            return SIMPLE_DATE_FORMAT.parse(strDate);
        } catch (ParseException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }
}
