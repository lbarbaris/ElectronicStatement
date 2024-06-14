package com.lbarbaris.springboot.electronicstatement.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Sheets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "sheets", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Statement_Columns> statementColumns;

    @OneToMany(mappedBy = "sheets", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Statement_Rows> statementRows;

    public Sheets() {
    }

    public Sheets(String name, List<Statement_Columns> statementColumns, List<Statement_Rows> statementRows) {
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

    public List<Statement_Columns> getStatementColumns() {
        return statementColumns;
    }

    public void setStatementColumns(List<Statement_Columns> statementColumns) {
        this.statementColumns = statementColumns;
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

    public List<Statement_Rows> getStatementRows() {
        return statementRows;
    }

    public void setStatementRows(List<Statement_Rows> statementRows) {
        this.statementRows = statementRows;
    }
}
