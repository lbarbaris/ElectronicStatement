package com.lbarbaris.springboot.electronicstatement.service;

import com.lbarbaris.springboot.electronicstatement.entity.Sheets;
import com.lbarbaris.springboot.electronicstatement.entity.Statement_Columns;
import com.lbarbaris.springboot.electronicstatement.repository.Statement_ColumnsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Statement_ColumnsServiceImpl implements Statement_ColumnsService {

    @Autowired
    private Statement_ColumnsRepository statementColumnsRepository;

    @Override
    public List<Statement_Columns> getAllColumns() {
        return statementColumnsRepository.findAll();
    }

    @Override
    public void save(Statement_Columns statementColumns) {
        statementColumnsRepository.save(statementColumns);
    }

    @Override
    public Statement_Columns getColumn(int id) {
        Statement_Columns statementColumns = null;
        Optional<Statement_Columns> optional = statementColumnsRepository.findById(id);
        if (optional.isPresent()) {
            statementColumns = optional.get();
        }
        return statementColumns;
    }

    @Override
    public void delete(int id) {
        statementColumnsRepository.deleteById(id);
    }

}
