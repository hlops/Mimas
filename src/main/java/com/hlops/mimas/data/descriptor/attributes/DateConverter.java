package com.hlops.mimas.data.descriptor.attributes;

import com.hlops.mimas.data.GlobalProperties;
import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 04.01.13
 * Time: 20:42
 * To change this template use File | Settings | File Templates.
 */
public class DateConverter implements TypeConverter<Date> {

    private static final Logger log = Logger.getLogger(DateConverter.class.getName());

    public Date fromString(@NotNull String value) {
        try {
            return GlobalProperties.getInstance().getDateFormat().parse(value);
        } catch (ParseException e) {
            log.warning(e.getMessage() + ": " + value);
            return null;
        }
    }

    public String toString(@NotNull Date date) {
        return GlobalProperties.getInstance().getDateFormat().format(date);
    }
}
