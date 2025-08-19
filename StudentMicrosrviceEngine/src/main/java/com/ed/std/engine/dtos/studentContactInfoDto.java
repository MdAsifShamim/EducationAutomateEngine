package com.ed.std.engine.dtos;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "students")
public record studentContactInfoDto(
		String name,
        Map<String,String> contactDetails,
        List<String> onCallSupport) {
}
