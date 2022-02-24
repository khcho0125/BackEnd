package com.doubledb;

import com.doubledb.Config.DataSourceDAO;
import com.doubledb.mapper.OneData.OneDataMapper;
import com.doubledb.mapper.TwoData.TwoDataMapper;
import com.doubledb.model.CountryModel;
import com.doubledb.model.SalaryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Service
public class TestController {

//    @Autowired
//    private TwoDataMapper twoDataMapper;
//
//    @Autowired
//    private OneDataMapper oneDataMapper;

    @Autowired
    private DataSourceDAO dao;

    @GetMapping("/salary/{id}")
    public ResponseEntity<SalaryModel> salary(@PathVariable Long id) {
        return ResponseEntity.ok(dao.getSalary(id));
//        return ResponseEntity.ok(oneDataMapper.selectSalary(id));
    }

    @GetMapping("/country/{id}")
    public ResponseEntity<CountryModel> Country(@PathVariable Long id) {
        return ResponseEntity.ok(dao.getCountry(id));
//        return ResponseEntity.ok(twoDataMapper.selectCountry(id));
    }
}
