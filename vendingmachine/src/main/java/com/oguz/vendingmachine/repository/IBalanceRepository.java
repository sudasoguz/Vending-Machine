package com.oguz.vendingmachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oguz.vendingmachine.model.Balance;

public interface IBalanceRepository extends JpaRepository<Balance, Integer> {

}
