package com.lbarbaris.springboot.electronicstatement.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "statement_columns")
public class Statement_Columns {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "sheet_id")
    private Sheets sheets;

    @OneToMany(mappedBy = "statementColumns", cascade = CascadeType.ALL)
    private List<Cell_Values> ColumnsCells;

    public void setId(int id) {
        this.id = id;
    }

    public Statement_Columns() {
    }

    public Statement_Columns(String name, Sheets sheets, List<Cell_Values> columnsCells) {
        this.name = name;
        this.sheets = sheets;
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

    public Sheets getSheets() {
        return sheets;
    }

    public void setSheets(Sheets sheets) {
        this.sheets = sheets;
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

    public List<Cell_Values> getColumnsCells() {
        return ColumnsCells;
    }

    public void setColumnsCells(List<Cell_Values> columnsCells) {
        ColumnsCells = columnsCells;
    }
}
