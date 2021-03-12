package com.oguz.vendingmachine.service;

import com.oguz.vendingmachine.model.Balance;

public interface IVendingMachineOperationsService {

	Balance getBalance();

	void addMoney(double amount);

	void chargeMoney(double amount);

}
