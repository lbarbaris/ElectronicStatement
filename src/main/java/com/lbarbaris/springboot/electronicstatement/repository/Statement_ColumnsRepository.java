package com.lbarbaris.springboot.electronicstatement.repository;

import com.lbarbaris.springboot.electronicstatement.entity.Statement_Columns;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Statement_ColumnsRepository extends JpaRepository<Statement_Columns, Integer> {
}
