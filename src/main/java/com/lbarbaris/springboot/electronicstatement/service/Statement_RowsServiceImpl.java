package com.lbarbaris.springboot.electronicstatement.service;

import com.lbarbaris.springboot.electronicstatement.entity.Statement_Columns;
import com.lbarbaris.springboot.electronicstatement.entity.Statement_Rows;
import com.lbarbaris.springboot.electronicstatement.repository.Statement_RowsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Statement_RowsServiceImpl implements Statement_RowsService {

    @Autowired
    private Statement_RowsRepository statementRowsRepository;

    @Override
    public List<Statement_Rows> getAllRows() {
        return statementRowsRepository.findAll();
    }

    @Override
    public void save(Statement_Rows statementRows) {
        statementRowsRepository.save(statementRows);
    }

    @Override
    public Statement_Rows getRow(int id) {
        Statement_Rows statementRows = null;
        Optional<Statement_Rows> optional = statementRowsRepository.findById(id);
        if (optional.isPresent()) {
            statementRows = optional.get();
        }
        return statementRows;
    }

    @Override
    public void delete(int id) {
        statementRowsRepository.deleteById(id);
    }

}
