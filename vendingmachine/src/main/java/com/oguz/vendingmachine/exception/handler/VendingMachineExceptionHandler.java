package com.oguz.vendingmachine.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.oguz.vendingmachine.exception.InsufficientBalanceException;
import com.oguz.vendingmachine.exception.InvalidAmountEntryException;
import com.oguz.vendingmachine.exception.ItemNotFoundException;

@ControllerAdvice
public class VendingMachineExceptionHandler {

	@ExceptionHandler(value = InsufficientBalanceException.class)
	public ResponseEntity<String> handleInsufficientBalanceException(InsufficientBalanceException exception) {
		return new ResponseEntity<>("Yeterli bakiyeniz yok.", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = ItemNotFoundException.class)
	public ResponseEntity<String> handleItemNotFoundException(ItemNotFoundException exception) {
		return new ResponseEntity<>("Ürün bulunamadı.", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = InvalidAmountEntryException.class)
	public ResponseEntity<String> handleInvalidAmountEnrtyException(InvalidAmountEntryException exception) {
		return new ResponseEntity<String>("Gerçersiz para girişi.Sadece 50 kuruş , 1 , 5 ve 10 TL kabul edilir.",
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}