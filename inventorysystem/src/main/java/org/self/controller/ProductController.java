package org.self.controller;

import java.util.List;

import org.self.dto.ProductDto;
import org.self.entity.Product;
import org.self.payload.ApiResponse;
import org.self.repository.ProductRepo;
import org.self.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor

public class ProductController {

  

   private final ProductService productService;


   
   @PostMapping
   public ResponseEntity<ApiResponse<Product>> addProduct(@Valid @RequestBody ProductDto productDto){
	   Product saveProduct = (productService.addProduct(productDto));
	   return ResponseEntity.ok(
			   new ApiResponse<>("Product created successfully", saveProduct));
   }
   @GetMapping
   public ResponseEntity<List<Product>> getAll(){
	   return ResponseEntity.ok(productService.getAllProducts());
   }
   
   @GetMapping("/search")
   public ResponseEntity<List<Product>> searchProduct(@RequestParam String name){
	   return ResponseEntity.ok(productService.searchProduct(name));
   }
   @PutMapping("/{id}")
   public ResponseEntity<ApiResponse<Product>> updateProduct(@PathVariable Long id , @Valid @RequestBody ProductDto productDto){
	  Product updateProduct = productService.updateProduct(id, productDto);
	  return ResponseEntity.ok(
			  new ApiResponse<>("Product Update Successfully", updateProduct)
			  );
   }
   
   @GetMapping("/filter")
    public ResponseEntity<List<Product>> filterProducts(
    		@RequestParam double minPrice,
    		@RequestParam double maxPrice){
	   return ResponseEntity.ok(productService.filterByPrice(minPrice, maxPrice));
   }
    		
   @GetMapping("/paginated")
   public ResponseEntity<Page<Product>> getProducts(
           @RequestParam int page,
           @RequestParam int size,
           @RequestParam(required = false) String sortBy,
           @RequestParam(required = false) String name
		   ){

       return ResponseEntity.ok(productService.getProducts(page, size, sortBy,name));
   }
   
   @GetMapping("/{id}")
   public ResponseEntity<Product> getProductById(@PathVariable Long id){
	   Product product = productService.getProductById(id);
	   return ResponseEntity.ok(product);
   }
   
   @DeleteMapping("/{id}")
   public ResponseEntity<String> delete(@PathVariable Long id){
	   productService.deleteProduct(id);
	   return ResponseEntity.ok("Deleted");
   }
}
