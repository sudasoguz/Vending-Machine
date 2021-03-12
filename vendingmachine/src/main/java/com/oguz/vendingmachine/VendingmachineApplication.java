package com.oguz.vendingmachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.oguz.vendingmachine.model.Balance;
import com.oguz.vendingmachine.model.Product;
import com.oguz.vendingmachine.repository.IBalanceRepository;
import com.oguz.vendingmachine.repository.IProductRepository;

@SpringBootApplication
public class VendingmachineApplication implements CommandLineRunner {

	@Autowired
	private IProductRepository productRepository;

	@Autowired
	private IBalanceRepository balanceRepository;

	public static void main(String[] args) {
		SpringApplication.run(VendingmachineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		productRepository.save(new Product("Kola", 15));
		productRepository.save(new Product("Fanta", 20));
		productRepository.save(new Product("Gazoz", 30));
		balanceRepository.save(new Balance(0));

	}

}
