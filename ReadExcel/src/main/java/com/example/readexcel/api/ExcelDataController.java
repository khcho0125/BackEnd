package com.example.readexcel.api;

import com.example.readexcel.entity.MealData;
import com.example.readexcel.service.ExcelDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExcelDataController {

    private final ExcelDataService service;

    @PostMapping("/upload")
    public void upload(@RequestParam MultipartFile file) {
        service.readExcel(file);
    }

    @GetMapping("/data")
    public List<MealDto> getMeal(@RequestParam Date start, @RequestParam Date end) {
        return service.getList(start, end);
    }
}
