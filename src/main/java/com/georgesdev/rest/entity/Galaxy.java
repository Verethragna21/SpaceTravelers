package com.georgesdev.rest.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Galaxy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "galaxyName cannot be null")
    private String galaxyName;

    @NotNull(message = "lightYears cannot be null")
    private Integer lightYears;

    public Galaxy(String galaxyName, Integer lightYears) {
        this.galaxyName = galaxyName;
        this.lightYears = lightYears;
    }
}
