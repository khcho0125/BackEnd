package com.example.readexcel.repo;

import com.example.readexcel.entity.MealData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<MealData, String> {

    List<MealData> findAllByDayBetween(Date day, Date day2);
}
