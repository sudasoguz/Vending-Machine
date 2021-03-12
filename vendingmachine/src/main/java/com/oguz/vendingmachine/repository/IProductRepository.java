package com.oguz.vendingmachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oguz.vendingmachine.model.Product;

public interface IProductRepository extends JpaRepository<Product, Integer> {

}
