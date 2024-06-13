package com.lbarbaris.springboot.electronicstatement.repository;

import com.lbarbaris.springboot.electronicstatement.entity.Statement_Rows;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Statement_RowsRepository extends JpaRepository<Statement_Rows, Integer> {
}
