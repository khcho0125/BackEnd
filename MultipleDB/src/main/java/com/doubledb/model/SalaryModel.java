package com.doubledb.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SalaryModel {
    private int id;
    private String name;
    private String email;
}
