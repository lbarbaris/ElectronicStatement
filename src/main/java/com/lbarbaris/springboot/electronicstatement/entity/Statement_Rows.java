package com.lbarbaris.springboot.electronicstatement.entity;

import jakarta.persistence.*;

@Entity
public class Statement_Rows {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "sheet_id")
    private Sheets RowsSheets;

    @OneToOne(mappedBy = "statementRows", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Cell_Values RowsCells;

    public Statement_Rows() {
    }

    public Statement_Rows(String name, Sheets rowsSheets, Cell_Values rowsCells) {
        this.name = name;
        RowsSheets = rowsSheets;
        RowsCells = rowsCells;
    }

    public Statement_Rows(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sheets getRowsSheets() {
        return RowsSheets;
    }

    public void setRowsSheets(Sheets rowsSheets) {
        RowsSheets = rowsSheets;
    }

    public Cell_Values getRowsCells() {
        return RowsCells;
    }

    public void setRowsCells(Cell_Values rowsCells) {
        RowsCells = rowsCells;
    }

    public int getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Statement_Rows{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
