package com.findork.preclinical.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

public class ZonedDateTimeReadConverter implements Converter<Date, ZonedDateTime> {
    @Override
    @Nullable
    public ZonedDateTime convert(@Nullable Date date) {
        return date == null ? null : date.toInstant().atZone(ZoneOffset.UTC);
    }
}
