package com.lbarbaris.springboot.electronicstatement.service;


import com.lbarbaris.springboot.electronicstatement.entity.Cell_Values;

import java.util.List;

public interface Cell_ValuesService {

    public List<Cell_Values> getAllCells();

    public void save(Cell_Values cellValues);

    public Cell_Values getCell(int id);

    public void delete(int id);

}
