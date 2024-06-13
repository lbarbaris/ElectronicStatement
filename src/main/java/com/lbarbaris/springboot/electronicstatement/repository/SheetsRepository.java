package com.lbarbaris.springboot.electronicstatement.repository;

import com.lbarbaris.springboot.electronicstatement.entity.Sheets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SheetsRepository extends JpaRepository<Sheets, Integer> {
}
