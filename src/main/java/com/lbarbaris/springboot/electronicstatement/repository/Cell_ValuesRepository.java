package com.lbarbaris.springboot.electronicstatement.repository;

import com.lbarbaris.springboot.electronicstatement.entity.Cell_Values;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Cell_ValuesRepository extends JpaRepository<Cell_Values, Integer> {
}
