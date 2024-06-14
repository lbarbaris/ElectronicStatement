package com.lbarbaris.springboot.electronicstatement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "statement_columns")
public class Statement_Columns {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "sheet_id")
    private Sheets ColumnsSheets;

    @OneToOne(mappedBy = "statementColumns", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Cell_Values ColumnsCells;

    public Statement_Columns() {
    }

    public Statement_Columns(String name, Sheets columnsSheets, Cell_Values columnsCells) {
        this.name = name;
        ColumnsSheets = columnsSheets;
        ColumnsCells = columnsCells;
    }

    public Statement_Columns(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sheets getColumnsSheets() {
        return ColumnsSheets;
    }

    public void setColumnsSheets(Sheets columnsSheets) {
        ColumnsSheets = columnsSheets;
    }

    public Cell_Values getColumnsCells() {
        return ColumnsCells;
    }

    public void setColumnsCells(Cell_Values columnsCells) {
        ColumnsCells = columnsCells;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Statement_Columns{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
