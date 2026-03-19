package org.self.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "PurchesOrders") 
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
	
   private double amount;
   private String status;
}
