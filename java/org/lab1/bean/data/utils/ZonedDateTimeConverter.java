package org.lab1.bean.data.utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("myZonedDateTimeConverter")
public class ZonedDateTimeConverter implements Converter<Object> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            return ZonedDateTime.parse(value, formatter);
        } catch (DateTimeParseException e) {
            throw new ConverterException("Invalid ZonedDateTime format: " + value, e);
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value == null) {
            return "";
        }
        if (!(value instanceof ZonedDateTime)) {
            throw new ConverterException("Value is not a ZonedDateTime: " + value); //Added value for better debugging
        }
        ZonedDateTime zonedDateTime = (ZonedDateTime) value;
        return zonedDateTime.format(formatter);
    }
}