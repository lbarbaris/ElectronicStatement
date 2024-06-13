package com.lbarbaris.springboot.electronicstatement.controller;

import com.lbarbaris.springboot.electronicstatement.entity.Cell_Values;
import com.lbarbaris.springboot.electronicstatement.entity.Sheets;
import com.lbarbaris.springboot.electronicstatement.entity.Statement_Columns;
import com.lbarbaris.springboot.electronicstatement.entity.Statement_Rows;
import com.lbarbaris.springboot.electronicstatement.service.Cell_ValuesService;
import com.lbarbaris.springboot.electronicstatement.service.SheetsService;
import com.lbarbaris.springboot.electronicstatement.service.Statement_ColumnsService;
import com.lbarbaris.springboot.electronicstatement.service.Statement_RowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StatementController {
    @Autowired
    private Cell_ValuesService cellValuesService;

    @Autowired
    private Statement_RowsService statementRowsService;

    @Autowired
    private Statement_ColumnsService statementColumnsService;

    @Autowired
    private SheetsService sheetsService;




    @RequestMapping("/")
    public String viewAll(){
        Cell_Values cellValues = new Cell_Values(5);
        Sheets sheets = new Sheets("Maths");
        Statement_Rows statementRows = new Statement_Rows("Martynov Pavel");
        Statement_Columns statementColumns = new Statement_Columns("H/W 13.06");
        sheets.setStatementRows(statementRows);
        sheets.setStatementColumns(statementColumns);
        statementRows.setRowsCells(cellValues);
        statementRows.setRowsSheets(sheets);
        statementColumns.setColumnsCells(cellValues);
        statementColumns.setColumnsSheets(sheets);
        cellValues.setStatementColumns(statementColumns);
        cellValues.setStatementRows(statementRows);
        sheetsService.save(sheets);
        statementColumnsService.save(statementColumns);
        statementRowsService.save(statementRows);
        cellValuesService.save(cellValues);
        return "view";
    }
}
