package com.findork.preclinical.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;
import java.util.Date;

public class ZonedDateTimeToDateConverter implements Converter<ZonedDateTime, Date> {
	
	@Override
	public Date convert(@Nullable ZonedDateTime source) {
		return source == null ? null : Date.from(source.toInstant());
	}
}
