package com.sparta.nbcspringadvancedpersonaltask.domain.todo.dto;

import lombok.Getter;

@Getter
public class WeatherDto {
    private final String date;
    private final String weather;

    public WeatherDto(String date, String weather) {
        this.date = date;
        this.weather = weather;
    }

}
