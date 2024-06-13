package com.lbarbaris.springboot.electronicstatement.service;

import com.lbarbaris.springboot.electronicstatement.entity.Statement_Rows;

import java.util.List;

public interface Statement_RowsService {
    public List<Statement_Rows> getAllRows();

    public void save(Statement_Rows statementRows);

    public Statement_Rows getRow(int id);

    public void delete(int id);
}
