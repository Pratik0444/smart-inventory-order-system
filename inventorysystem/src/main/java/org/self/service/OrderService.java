package org.self.service;

import org.self.entity.Order;
import org.self.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
 @Autowired
 private OrderRepo orderRepo;
 
 public Order createOrder(double amount) {
	 Order order = new Order();
     order.setAmount(amount);
     order.setStatus("PENDING");
	 return orderRepo.save(order);
 }
 public Order marksPaid(Long id){
	 Order order = orderRepo.findById(id)
			 .orElseThrow(() -> new RuntimeException("Order not found"));
    order.setStatus("PAID");
    return orderRepo.save(order);
 }
}
