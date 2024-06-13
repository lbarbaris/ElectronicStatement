package com.lbarbaris.springboot.electronicstatement.service;

import com.lbarbaris.springboot.electronicstatement.entity.Cell_Values;
import com.lbarbaris.springboot.electronicstatement.entity.Sheets;
import com.lbarbaris.springboot.electronicstatement.repository.SheetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SheetsServiceImpl implements SheetsService {

    @Autowired
    private SheetsRepository sheetsRepository;

    @Override
    public List<Sheets> getAllSheets() {
        return sheetsRepository.findAll();
    }

    @Override
    public void save(Sheets sheets) {
        sheetsRepository.save(sheets);
    }

    @Override
    public Sheets getSheet(int id) {
        Sheets sheets = null;
        Optional<Sheets> optional = sheetsRepository.findById(id);
        if (optional.isPresent()) {
            sheets = optional.get();
        }
        return sheets;
    }

    @Override
    public void delete(int id) {
        sheetsRepository.deleteById(id);
    }

}
