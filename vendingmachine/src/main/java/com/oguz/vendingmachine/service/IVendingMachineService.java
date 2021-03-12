package com.oguz.vendingmachine.service;

import java.util.List;

import com.oguz.vendingmachine.model.Product;

public interface IVendingMachineService {
	
	List<Product> listProducts();
	
	Product getProductById(int id);
	
	void addBalance(double amount);
	
	void giveProduct(int id);

}
