package com.cinema.point.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
public class MovieDTO {

    private Long id;

    public MovieDTO(String name, String description, Long duration,
                    byte[] picture, Set<Long> actorsIds) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.picture = picture;
        this.actorsIds = actorsIds;
    }

    @NotEmpty(message = "name value is required")
    private String name;

    @NotEmpty(message = "description value is required")
    @Max(value = 3000, message = "too long description")
    private String description;

    @NotNull(message = "duration is required")
    private Long duration;

    @NotNull(message = "picture is required")
    private byte[] picture;

    //    private Set<ActorDTO> actors = new HashSet<>();
    private Set<Long> actorsIds = new HashSet<>();

    private String pictureString;
}
