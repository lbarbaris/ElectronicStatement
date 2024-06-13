package com.lbarbaris.springboot.electronicstatement.service;

import com.lbarbaris.springboot.electronicstatement.entity.Cell_Values;
import com.lbarbaris.springboot.electronicstatement.entity.Sheets;

import java.util.List;

public interface SheetsService {

        public List<Sheets> getAllSheets();

        public void save(Sheets sheets);

        public Sheets getSheet(int id);

        public void delete(int id);

}
