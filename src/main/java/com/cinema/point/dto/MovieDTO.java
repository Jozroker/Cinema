package com.cinema.point.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
public class MovieDTO {

    private Long id;

    @NotEmpty(message = "name value is required")
    private String name;

    @NotEmpty(message = "description value is required")
    @Max(value = 3000, message = "too long description")
    private String description;

    @NotNull(message = "duration is required")
    private Long duration;

    @NotNull(message = "picture is required")
    private byte[] picture;

    private Set<Long> actorsIds = new HashSet<>();

    private String pictureString;

    public String getGetDurationTime() {
        long minute = (this.duration / (1000 * 60)) % 60;
        long hour = (this.duration / (1000 * 60 * 60)) % 24;
        return hour + ":" + minute;
    }
}
