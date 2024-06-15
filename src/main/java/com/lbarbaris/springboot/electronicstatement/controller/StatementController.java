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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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




    @GetMapping("/")
    public String viewAll(Model model){
        List<Sheets> sheets = sheetsService.getAllSheets();
        List<Statement_Columns> statementColumns = statementColumnsService.getAllColumns();
        List<Statement_Rows> statementRows = statementRowsService.getAllRows();
        List<Cell_Values> cellValues = cellValuesService.getAllCells();
        model.addAllAttributes(Map.of(
                "sheets", sheets,
                "statementColumns", statementColumns,
                "statementRows", statementRows,
                "cellValues", cellValues
        ));

/*        Cell_Values cellValues = new Cell_Values(5);
        Sheets sheets = sheetsService.getSheet(3);
        Statement_Rows statementRows = new Statement_Rows("Kostina Nastya");
        Statement_Columns statementColumns = statementColumnsService.getColumn(3);
        ArrayList<Statement_Rows> statementRowsArrayList = new ArrayList<Statement_Rows>();
        statementRowsArrayList.add(statementRows);
        sheets.setStatementRows(statementRowsArrayList);

        ArrayList<Statement_Columns> statementColumnsArrayList = new ArrayList<Statement_Columns>();
        statementColumnsArrayList.add(statementColumns);
        sheets.setStatementColumns(statementColumnsArrayList);

        ArrayList<Cell_Values> cellValuesArrayList = new ArrayList<Cell_Values>();
        cellValuesArrayList.add(cellValues);
        statementRows.setRowsCells(cellValuesArrayList);

        statementRows.setSheets(sheets);
        statementColumns.setColumnsCells(cellValuesArrayList);
        statementColumns.setSheets(sheets);
        cellValues.setStatementColumns(statementColumns);
        cellValues.setStatementRows(statementRows);
        sheetsService.save(sheets);
        statementColumnsService.save(statementColumns);
        statementRowsService.save(statementRows);
        cellValuesService.save(cellValues);*/
        return "view";
    }

    @GetMapping("/updateRow")
    public String updateRow(@RequestParam("sheetId") int id, Model model){
        if (sheetsService.getSheet(id).getStatementRows().size() == 0 && sheetsService.getSheet(id).getStatementColumns().size() == 0){
            sheetsService.delete(id);
            return "redirect:/";
        }
        else{
            List<Statement_Rows> toSend = new ArrayList<>();
            for (int i = 0; i < statementRowsService.getAllRows().size(); i++){
                if (statementRowsService.getAllRows().get(i).getSheets() == sheetsService.getSheet(id)){
                    toSend.add(statementRowsService.getAllRows().get(i));
                }
            }
            model.addAttribute("rows", toSend);
            model.addAttribute("oneRow", new Statement_Rows());
            return "updateRow";
        }

    }

    @PostMapping("/saveRow")
    public String saveOrDeleteRow(@RequestParam("action")String action, @ModelAttribute("rows") Statement_Rows statementRows){
        if (action.equals("update")) {
            Statement_Rows updatedRows = new Statement_Rows(statementRows.getName(), statementRowsService.getRow(statementRows.getId()).getSheets(), statementRowsService.getRow(statementRows.getId()).getRowsCells());
            updatedRows.setId(statementRows.getId());
            statementRowsService.save(updatedRows);
        }
        else{
            statementRowsService.delete(statementRows.getId());
        }
        return "redirect:/";
    }
}