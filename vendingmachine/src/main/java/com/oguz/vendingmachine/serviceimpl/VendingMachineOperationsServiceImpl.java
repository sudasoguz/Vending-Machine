package com.oguz.vendingmachine.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oguz.vendingmachine.exception.InsufficientBalanceException;
import com.oguz.vendingmachine.exception.InvalidAmountEntryException;
import com.oguz.vendingmachine.model.Balance;
import com.oguz.vendingmachine.repository.IBalanceRepository;
import com.oguz.vendingmachine.service.IVendingMachineOperationsService;

@Service
public class VendingMachineOperationsServiceImpl implements IVendingMachineOperationsService {

	private IBalanceRepository balanceRepository;

	@Autowired
	public VendingMachineOperationsServiceImpl(IBalanceRepository balanceRepository) {
		this.balanceRepository = balanceRepository;
	}

	@Override
	public Balance getBalance() {
		return balanceRepository.findAll().stream().findFirst().orElseThrow(IllegalStateException::new);
	}

	@Override
	public void addMoney(double amount) {
		if (amount == 0.5 || amount == 1 || amount == 5 || amount == 10) {

			balanceRepository.save(updateBalance(getBalance(), amount));
		} else {
			throw new InvalidAmountEntryException();
		}

	}

	@Override
	@Transactional
	public void chargeMoney(double amount) {
		if (getBalance().getBalance() - amount >= 0) {
			balanceRepository.save(updateBalance(getBalance(), -amount));
		} else {
			 throw new InsufficientBalanceException();
		}

	}

	private Balance updateBalance(Balance balance, double amount) {
		balance.setBalance(balance.getBalance() + amount);
		return balance;
	}

}
