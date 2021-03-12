package com.oguz.vendingmachine.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oguz.vendingmachine.model.Product;
import com.oguz.vendingmachine.repository.IProductRepository;
import com.oguz.vendingmachine.service.IVendingMachineActionsService;

@Service
public class VendingMachineActionsServiceImpl implements IVendingMachineActionsService {

	private IProductRepository productRepository;

	@Autowired
	public VendingMachineActionsServiceImpl(IProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	@Transactional
	public void dispense(Product product) {

		productRepository.save(product);
		System.out.println("dispensed : " + product.getName());

	}

}
