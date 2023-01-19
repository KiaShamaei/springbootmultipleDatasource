package com.example.springmultipledatasource.slave.repository;

import com.example.springmultipledatasource.slave.entity.ProductSlave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSlaveRepository extends JpaRepository<ProductSlave , Long> {
}
