package com.example.readexcel.service;

import com.example.readexcel.api.MealDto;
import com.example.readexcel.entity.MealData;
import com.example.readexcel.repo.MealRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExcelDataService {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private final MealRepository mealRepository;

    @Transactional(rollbackFor = Exception.class)
    public void readExcel(MultipartFile file) {
        try {
            List<MealData> dataList = new ArrayList<>();

            String extension = FilenameUtils.getExtension(file.getOriginalFilename());

            if (!extension.equals("xlsx") && !extension.equals("xls")) {
                throw new IOException("엑셀파일만 업로드해주세요.");
            }

            Workbook excelFile = extension.equals("xlsx") ? new XSSFWorkbook(file.getInputStream()) : new HSSFWorkbook(file.getInputStream());
            Sheet workSheet = excelFile.getSheetAt(0);

            for (int i = 1; i < workSheet.getPhysicalNumberOfRows(); i++) {
                Row row = workSheet.getRow(i);

                dataList.add(
                        new MealData(
                                row.getCell(0).getDateCellValue(),
                                row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue(),
                                row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue(),
                                row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue()
                        )
                );
            }

            mealRepository.saveAll(dataList);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<MealDto> getList(Date start, Date end) {
        List<MealData> mealData = mealRepository.findAllByDayBetween(start, end);
        return mealData.stream().map(
                meal -> new MealDto(
                            FORMAT.format(meal.getDay()),
                            meal.getBreakfast(),
                            meal.getLunch(),
                            meal.getDinner()
                )).collect(Collectors.toList());
    }
}
