package com.lbarbaris.springboot.electronicstatement.service;

import com.lbarbaris.springboot.electronicstatement.entity.Sheets;
import com.lbarbaris.springboot.electronicstatement.entity.Statement_Columns;

import java.util.List;

public interface Statement_ColumnsService {
    public List<Statement_Columns> getAllColumns();

    public void save(Statement_Columns statementColumns);

    public Statement_Columns getColumn(int id);

    public void delete(int id);
}
