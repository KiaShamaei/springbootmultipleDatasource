package com.example.springmultipledatasource.master.repository;

import com.example.springmultipledatasource.master.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product , Long> {
}
