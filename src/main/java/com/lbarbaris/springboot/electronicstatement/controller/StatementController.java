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
import java.util.Arrays;
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
            List<Cell_Values> cellValues = new ArrayList<Cell_Values>();
            List<Statement_Columns> columns = sheetsService.getSheet(id).getStatementColumns();
            for (int i = 0; i < columns.size(); i++) {
                    Cell_Values cell = new Cell_Values(statementRows, columns.get(i), 0);
                    cellValues.add(cell);
                    cellValuesService.save(cell);
            }
            statementRows.setRowsCells(cellValues);
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

        Statement_Rows statementRows = new Statement_Rows(null);
        model.addAttribute("oneRow", statementRows);
        model.addAttribute("sheet", sheetsService.getSheet(id));
        return "addRow";
    }


    @GetMapping("/updateColumn")
    public String updateColumn(@RequestParam("sheetId") int id, Model model){
        if (sheetsService.getSheet(id).getStatementRows().isEmpty() && sheetsService.getSheet(id).getStatementColumns().isEmpty()){
            sheetsService.delete(id);
            return "redirect:/";
        }
        else{
            List<Statement_Columns> toSend = new ArrayList<>();
            for (int i = 0; i < statementColumnsService.getAllColumns().size(); i++){
                if (statementColumnsService.getAllColumns().get(i).getSheets() == sheetsService.getSheet(id)){
                    toSend.add(statementColumnsService.getAllColumns().get(i));
                }
            }
            model.addAttribute("cols", toSend);
            model.addAttribute("oneColumn", new Statement_Columns());
            model.addAttribute("sheet", sheetsService.getSheet(id));
            return "updateColumn";
        }
    }


    @GetMapping("/addColumn")
    public String addColumn(@RequestParam("sheetId") int id, Model model){
        model.addAttribute("oneColumn", new Statement_Columns());
        model.addAttribute("sheet", sheetsService.getSheet(id));
        return "addColumn";
    }

    @PostMapping("/saveColumn")
    public String saveColumn(@RequestParam("action")String action, @ModelAttribute("oneColumn") Statement_Columns statementColumns, @RequestParam("sheetId") int id){

        if (action.equals("update")) {
            Statement_Columns updatedCols = new Statement_Columns(statementColumns.getName(), sheetsService.getSheet(id), statementColumnsService.getColumn(statementColumns.getId()).getColumnsCells());
            updatedCols.setId(statementColumns.getId());
            statementColumnsService.save(updatedCols);
        }
        else if (action.equals("add")){
            statementColumns.setSheets(sheetsService.getSheet(id));
            List<Cell_Values> cellValues = new ArrayList<Cell_Values>();
            List<Statement_Rows> rows = sheetsService.getSheet(id).getStatementRows();
            for (int i = 0; i < rows.size(); i++) {
                Cell_Values cell = new Cell_Values(rows.get(i), statementColumns, 0);
                cellValues.add(cell);
                cellValuesService.save(cell);
            }
            statementColumns.setColumnsCells(cellValues);
            statementColumnsService.save(statementColumns);

        }
        else{
            statementColumnsService.delete(statementColumns.getId());
            if (sheetsService.getSheet(id).getStatementColumns().isEmpty()){
                List<Statement_Rows> toDelete = sheetsService.getSheet(id).getStatementRows();
                for (int i = 0; i < toDelete.size(); i++){
                    int rowid = toDelete.get(i).getId();
                    statementRowsService.delete(rowid);
                }
                sheetsService.delete(id);
            }

        }
        return "redirect:/";
    }


    @GetMapping("/updateCell")
    public String updateCell(@RequestParam("sheetId") int idSheet, @RequestParam("rowId") int idRow, Model model){
            List<Cell_Values> toSend = new ArrayList<>();
            for (int i = 0; i < cellValuesService.getAllCells().size(); i++){
                if (cellValuesService.getAllCells().get(i).getStatementRows() == statementRowsService.getRow(idRow)){
                    toSend.add(cellValuesService.getAllCells().get(i));
                }
            }
            model.addAttribute("cells", toSend);
            model.addAttribute("oneCell", new Cell_Values());
            return "updateCell";
    }

    @PostMapping("/saveCell")
    public String saveCell(@RequestParam("action")String action, @ModelAttribute("oneCell") Cell_Values cellValues){

            Cell_Values updatedCell = new Cell_Values(cellValuesService.getCell(cellValues.getId()).getStatementRows(), cellValuesService.getCell(cellValues.getId()).getStatementColumns(), cellValues.getValue());
            updatedCell.setId(cellValues.getId());
            cellValuesService.save(updatedCell);

        return "redirect:/";
    }

    @GetMapping("/addSheet")
    public String addSheet(Model model){
        model.addAttribute("oneSheet", new Sheets());
        return "addSheet";
    }

    @PostMapping("/saveSheet")
    public String saveSheet(@RequestParam("columnName")String columnName, @RequestParam("rowName")String rowName, @ModelAttribute("oneSheet") Sheets sheets){
        Cell_Values cellValues = new Cell_Values(0);
        Statement_Rows statementRows = new Statement_Rows(rowName, sheets, new ArrayList<>(List.of(cellValues)));
        Statement_Columns statementColumns = new Statement_Columns(columnName, sheets, new ArrayList<>(List.of(cellValues)));
        cellValues.setStatementColumns(statementColumns);
        cellValues.setStatementRows(statementRows);
        sheets.setStatementRows(new ArrayList<>(List.of(statementRows)));
        sheets.setStatementColumns(new ArrayList<>(List.of(statementColumns)));
        System.out.println(statementRows);
        System.out.println(statementColumns);
        System.out.println(sheets);
        System.out.println(cellValues);
        cellValuesService.save(cellValues);
        statementRowsService.save(statementRows);
        statementColumnsService.save(statementColumns);
        sheetsService.save(sheets);
        return "redirect:/";
    }
}