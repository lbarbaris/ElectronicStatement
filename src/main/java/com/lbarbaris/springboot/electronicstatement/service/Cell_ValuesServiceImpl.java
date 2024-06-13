package com.lbarbaris.springboot.electronicstatement.service;

import com.lbarbaris.springboot.electronicstatement.entity.Cell_Values;
import com.lbarbaris.springboot.electronicstatement.repository.Cell_ValuesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Cell_ValuesServiceImpl implements Cell_ValuesService {

    @Autowired
    private Cell_ValuesRepository cellValuesRepository;

    @Override
    public List<Cell_Values> getAllCells() {
        return cellValuesRepository.findAll();
    }

    @Override
    public void save(Cell_Values cellValues) {
        cellValuesRepository.save(cellValues);
    }

    @Override
    public Cell_Values getCell(int id) {
        Cell_Values cellValues = null;
        Optional<Cell_Values> optional = cellValuesRepository.findById(id);
        if (optional.isPresent()) {
            cellValues = optional.get();
        }
        return cellValues;
    }

    @Override
    public void delete(int id) {
        cellValuesRepository.deleteById(id);
    }

}

