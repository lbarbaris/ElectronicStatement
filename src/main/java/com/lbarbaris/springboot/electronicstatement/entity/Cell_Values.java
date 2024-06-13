package com.lbarbaris.springboot.electronicstatement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cell_values")
public class Cell_Values {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "row_id")
    private Statement_Rows statementRows;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "column_id")
    private Statement_Columns statementColumns;

    @Column(name = "value")
    private int value;

    public Cell_Values() {
    }

    public Cell_Values(Statement_Rows statementRows, Statement_Columns statementColumns, int value) {
        this.statementRows = statementRows;
        this.statementColumns = statementColumns;
        this.value = value;
    }

    public Cell_Values(int value) {
        this.value = value;
    }

    public Statement_Rows getStatementRows() {
        return statementRows;
    }

    public void setStatementRows(Statement_Rows statementRows) {
        this.statementRows = statementRows;
    }

    public Statement_Columns getStatementColumns() {
        return statementColumns;
    }

    public void setStatementColumns(Statement_Columns statementColumns) {
        this.statementColumns = statementColumns;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Cell_Values{" +
                "id=" + id +
                ", statementRows=" + statementRows +
                ", statementColumns=" + statementColumns +
                ", value=" + value +
                '}';
    }
}
