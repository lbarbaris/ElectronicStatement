package com.lbarbaris.springboot.electronicstatement.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Statement_Rows {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "sheet_id")
    private Sheets sheets;

    @OneToMany(mappedBy = "statementRows", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Cell_Values> RowsCells;

    public Statement_Rows() {
    }

    public Statement_Rows(String name, Sheets sheets, List<Cell_Values> rowsCells) {
        this.name = name;
        this.sheets = sheets;
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
        return "Statement_Rows{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public List<Cell_Values> getRowsCells() {
        return RowsCells;
    }

    public void setRowsCells(List<Cell_Values> rowsCells) {
        RowsCells = rowsCells;
    }

    public void setId(int id) {
        this.id = id;
    }
}
