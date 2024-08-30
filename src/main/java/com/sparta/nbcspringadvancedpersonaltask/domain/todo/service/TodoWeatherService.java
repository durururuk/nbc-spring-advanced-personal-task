package com.sparta.nbcspringadvancedpersonaltask.domain.todo.service;

import com.sparta.nbcspringadvancedpersonaltask.domain.todo.dto.WeatherDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;

@Service
public class TodoWeatherService {

    private final RestTemplate restTemplate;
    public TodoWeatherService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    private URI buildWeatherApiUri() {
        return UriComponentsBuilder
                .fromUriString("https://f-api.github.io")
                .path("/f-api/weather.json")
                .encode()
                .build()
                .toUri();
    }

    public String getCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");
        return LocalDate.now().format(formatter);
    }

    public String getTodayWeather() {
        ResponseEntity<WeatherDto[]> responseEntity =
                restTemplate.getForEntity(buildWeatherApiUri(), WeatherDto[].class);

        if(!HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            throw new RestClientException("날씨 데이터를 가져오는 데 실패했습니다. 상태 코드: " + responseEntity.getStatusCode());
        }

        WeatherDto[] weatherDtoArray = responseEntity.getBody();
        if (weatherDtoArray == null || weatherDtoArray.length == 0) {
            throw new RestClientException("날씨 데이터가 없습니다.");
        }

        String todayDate = getCurrentDate();
        Optional<WeatherDto> todayWeather = Arrays.stream(weatherDtoArray)
                .filter(weatherDto -> todayDate.equals(weatherDto.getDate()))
                .findFirst();

        return todayWeather.map(WeatherDto::getWeather)
                .orElseThrow(() -> new RestClientException("오늘에 해당하는 날씨가 없습니다."));
    }
}
