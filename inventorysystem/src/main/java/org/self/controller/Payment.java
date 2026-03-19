package org.self.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class Payment{
  @PostMapping("/pay")
  public String makePayment(@RequestParam double amount) {
	  return "Payment of $" + amount + " Successful!";
  }
}
