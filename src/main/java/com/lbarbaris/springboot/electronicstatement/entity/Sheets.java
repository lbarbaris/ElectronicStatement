package com.lbarbaris.springboot.electronicstatement.entity;

import jakarta.persistence.*;

@Entity
public class Sheets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "ColumnsSheets", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Statement_Columns statementColumns;

    @OneToOne(mappedBy = "RowsSheets", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Statement_Rows statementRows;

    public Sheets() {
    }

    public Sheets(String name, Statement_Columns statementColumns, Statement_Rows statementRows) {
        this.name = name;
        this.statementColumns = statementColumns;
        this.statementRows = statementRows;
    }

    public Sheets(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Statement_Columns getStatementColumns() {
        return statementColumns;
    }

    public void setStatementColumns(Statement_Columns statementColumns) {
        this.statementColumns = statementColumns;
    }

    public Statement_Rows getStatementRows() {
        return statementRows;
    }

    public void setStatementRows(Statement_Rows statementRows) {
        this.statementRows = statementRows;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Sheets{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
