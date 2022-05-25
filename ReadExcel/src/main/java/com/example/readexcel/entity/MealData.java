package com.example.readexcel.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class MealData {

    @Id
    private Date day;

    private String breakfast;

    private String lunch;

    private String dinner;
}
