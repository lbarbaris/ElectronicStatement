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
        Sheets sheets = new Sheets("English");
        Statement_Columns statementColumns = new Statement_Columns("H/W 11.05");
*//*        Sheets sheets = sheetsService.getSheet(3);*//*
        Statement_Rows statementRows = new Statement_Rows("Kostina Nastya");
        Statement_Rows statementRows2 = new Statement_Rows("Martynov Pavel");
        Statement_Rows statementRows3 = new Statement_Rows("Yudintsev Ivan");
*//*        Statement_Columns statementColumns = statementColumnsService.getColumn(3);*//*
        ArrayList<Statement_Rows> statementRowsArrayList = new ArrayList<Statement_Rows>();
        statementRowsArrayList.add(statementRows);
        statementRowsArrayList.add(statementRows2);
        statementRowsArrayList.add(statementRows3);
        sheets.setStatementRows(statementRowsArrayList);

        ArrayList<Statement_Columns> statementColumnsArrayList = new ArrayList<Statement_Columns>();
        statementColumnsArrayList.add(statementColumns);
        sheets.setStatementColumns(statementColumnsArrayList);

        ArrayList<Cell_Values> cellValuesArrayList = new ArrayList<Cell_Values>();
        cellValuesArrayList.add(cellValues);
        statementRows.setRowsCells(cellValuesArrayList);
        statementRows2.setRowsCells(cellValuesArrayList);
        statementRows3.setRowsCells(cellValuesArrayList);

        statementRows.setSheets(sheets);
        statementRows2.setSheets(sheets);
        statementRows3.setSheets(sheets);
        statementColumns.setColumnsCells(cellValuesArrayList);
        statementColumns.setSheets(sheets);
        cellValues.setStatementColumns(statementColumns);
        cellValues.setStatementRows(statementRows);
        cellValues.setStatementRows(statementRows2);
        cellValues.setStatementRows(statementRows3);
        sheetsService.save(sheets);
        statementColumnsService.save(statementColumns);
        statementRowsService.save(statementRows);
        statementRowsService.save(statementRows2);
        statementRowsService.save(statementRows3);
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
            model.addAttribute("sheet", sheetsService.getSheet(id));
            return "updateRow";
        }

    }

    @PostMapping("/saveRow")
    public String saveRow(@RequestParam("action")String action, @ModelAttribute("oneRow") Statement_Rows statementRows, @RequestParam("sheetId") int id){

        if (action.equals("update")) {
            Statement_Rows updatedRows = new Statement_Rows(statementRows.getName(), sheetsService.getSheet(id), statementRowsService.getRow(statementRows.getId()).getRowsCells());
            updatedRows.setId(statementRows.getId());
            statementRowsService.save(updatedRows);
        }
        else if (action.equals("add")){
            statementRows.setSheets(sheetsService.getSheet(id));
            statementRowsService.save(statementRows);
        }
        else{
            statementRowsService.delete(statementRows.getId());
            if (sheetsService.getSheet(id).getStatementRows().isEmpty()){
                List<Statement_Columns> toDelete = sheetsService.getSheet(id).getStatementColumns();
                for (int i = 0; i < toDelete.size(); i++){
                    int colid = toDelete.get(i).getId();
                    statementColumnsService.delete(colid);
                }
                sheetsService.delete(id);
            }

        }
        return "redirect:/";
    }

    @GetMapping("/addRow")
    public String addRow(@RequestParam("sheetId") int id, Model model){

        List<Cell_Values> cellValues = new ArrayList<Cell_Values>();
        for (int i = 0; i < statementColumnsService.getAllColumns().size() - 1; i++){
            cellValues.add(new Cell_Values(0));
        }
        Statement_Rows statementRows = new Statement_Rows(null, sheetsService.getSheet(id), cellValues);
        model.addAttribute("oneRow", statementRows);
        model.addAttribute("sheet", sheetsService.getSheet(id));
        return "addRow";
    }
}