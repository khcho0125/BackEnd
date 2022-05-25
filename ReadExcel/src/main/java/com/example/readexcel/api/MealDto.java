package com.example.readexcel.api;

import com.example.readexcel.entity.MealData;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MealDto {
    private String day;
    private String breakfast;
    private String lunch;
    private String dinner;
}
