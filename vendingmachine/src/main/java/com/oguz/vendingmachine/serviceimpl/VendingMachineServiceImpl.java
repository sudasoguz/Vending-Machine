package com.oguz.vendingmachine.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oguz.vendingmachine.exception.ItemNotFoundException;
import com.oguz.vendingmachine.model.Product;
import com.oguz.vendingmachine.repository.IProductRepository;
import com.oguz.vendingmachine.service.IVendingMachineActionsService;
import com.oguz.vendingmachine.service.IVendingMachineOperationsService;
import com.oguz.vendingmachine.service.IVendingMachineService;

@Service
public class VendingMachineServiceImpl implements IVendingMachineService {

	private IProductRepository productRepository;
	private IVendingMachineActionsService actionsService;
	private IVendingMachineOperationsService operationsService;

	@Autowired
	public VendingMachineServiceImpl(IProductRepository productRepository, IVendingMachineActionsService actionsService,
			IVendingMachineOperationsService operationsService) {
		super();
		this.productRepository = productRepository;
		this.actionsService = actionsService;
		this.operationsService = operationsService;
	}

	@Override
	public List<Product> listProducts() {

		return productRepository.findAll();
	}

	@Override
	public Product getProductById(int id) {

		return productRepository.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(id + " numaralı ürün bulunamadı."));
	}

	@Override
	public void addBalance(double amount) {
		operationsService.addMoney(amount);

	}

	@Override
	public void giveProduct(int id) {
		Product productToDispense = getProductById(id);
		operationsService.chargeMoney(productToDispense.getPrice());
		actionsService.dispense(productToDispense);

	}
	
	public double checkBalance() {
		return operationsService.getBalance().getBalance();
	}
	
	public boolean moneyZero() {
		if(checkBalance()==0) {
			return true;
		}else {
			return false;
		}
	}

}
