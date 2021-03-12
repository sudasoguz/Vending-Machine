package com.oguz.vendingmachine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.oguz.vendingmachine.serviceimpl.VendingMachineServiceImpl;

@Controller
public class VendingMachineController {

	private VendingMachineServiceImpl vendingMachineService;

	@Autowired
	public VendingMachineController(VendingMachineServiceImpl vendingMachineService) {
		this.vendingMachineService = vendingMachineService;
	}

	@GetMapping("/buy/{id}")
	public String buyProduct(@PathVariable int id, Model model) {

		double balance = vendingMachineService.checkBalance();
		if (balance < vendingMachineService.getProductById(id).getPrice()) {
			return "nomoney";
		} else {
			vendingMachineService.giveProduct(id);
			if (vendingMachineService.moneyZero() == true) {
				model.addAttribute("balance",vendingMachineService.checkBalance());
				return "exit";
			} else {
				return "redirect:/";
			}
		}
	}
	
	@GetMapping("/buy/confirm/{id}")
	public String confirm(@PathVariable int id,Model model){
		model.addAttribute("product",vendingMachineService.getProductById(id));
		return "confirm";
	}

	@GetMapping("/")
	public String getHomePage(Model model) {
		model.addAttribute("product", vendingMachineService.listProducts());
		double balance = vendingMachineService.checkBalance();
		model.addAttribute("balance", balance);
		return "home";
	}

	@GetMapping("/showAddBalancePage")
	public String showAddBalancePage(Model model) {
		double balance = vendingMachineService.checkBalance();
		model.addAttribute("balance", balance);
		return "addBalance";
	}

	@GetMapping("/addMoney/{amount}")
	public String addMoney(@PathVariable double amount) {
		vendingMachineService.addBalance(amount);
		return "redirect:/showAddBalancePage";
	}

	@GetMapping("/exit")
	public String exit(Model model) {
		double balance = vendingMachineService.checkBalance();
		model.addAttribute("balance", balance);
		return "exit";
	}

}
