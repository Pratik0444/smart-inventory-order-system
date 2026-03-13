package org.self.service;

import java.util.List;

import org.self.dto.ProductDto;
import org.self.entity.Product;
import org.self.repository.ProductRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
 private final ProductRepo productRepo;
 
 public Product addProduct(ProductDto productDto) {
	 Product product = new Product();
	 product.setName(productDto.getName());
	 product.setQuantity(productDto.getQuantity());
	 product.setPrice(productDto.getPrice());
	 return productRepo.save(product);
 }
 
 public Product updateProduct(long id,ProductDto productDto) {
	 Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
 product.setName(productDto.getName());
 product.setPrice(productDto.getPrice());
 product.setQuantity(productDto.getQuantity());
 return productRepo.save(product);
 }
 
 public Page<Product> getProducts(int page, int size , String sortBy){

	    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

	    return productRepo.findAll(pageable);
	}
 
 public List<Product> getAllProducts(){
	 return productRepo.findAll();
 }
 
 public void deleteProduct(Long id) {
	 productRepo.deleteById(id);
 }
}
