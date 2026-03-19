package org.self.controller;

import org.self.entity.Order;
import org.self.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
   @Autowired
   private OrderService orderService;
   
   @PostMapping("/create")
   public Order createOrder(@RequestParam double amount) {
	   return orderService.createOrder(amount);
  }
   @PostMapping("/pay/{id}")
   public Order payOrder(@PathVariable Long id) {
	   return orderService.marksPaid(id);
   }
}
