package com.doubledb.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CountryModel {
    private int id;
    private String continent;
    private String country;
}