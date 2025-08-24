package com.ed.std.engine.dtos;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "students")
@Getter
@Setter
public class studentContactInfoDto {
    private String name;
    private Map<String,String> contactDetails;
    private List<String> onCallSupport;
}
