package org.self.repository;
import org.springframework.data.domain.Pageable;
import java.util.List;

import org.self.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
 	Page<Product> findByNameContainingIgnoreCase(String name , Pageable pageable);  
	List<Product> findByNameContainingIgnoreCase(String name);
     List<Product> findByPriceBetween(double minPrice,double maxPrice);
}
