package com.capg.controller;

import java.util.List;

import com.capg.dto.ProductDto;
import com.capg.entities.Product;
import com.capg.exceptions.ProductNotFoundException;
import com.capg.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ProductRestController {
	private static Logger Log= LoggerFactory.getLogger(ProductRestController.class);
	@Autowired
	private IProductService service;
	
	@GetMapping("/products/find/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") int id ){
		Product product=service.findProductById(id);
		if(product==null)
		{
			throw new ProductNotFoundException("Product does not exist for "+id);
		}
		else {
			ResponseEntity<Product> response=new ResponseEntity<>(HttpStatus.OK);
			return response;
		}
	}
	
	@PostMapping("/products/add")
	public ResponseEntity<Product> addProduct(@RequestBody ProductDto dto){
		Product product=new Product();
		product.setId(dto.getId());
		product.setName(dto.getName());
		product.setPrice(dto.getPrice());
		
		product = service.save(product);
		ResponseEntity<Product> response=new ResponseEntity<>(HttpStatus.OK);
		return response;
		
	}
	@GetMapping("/products")
	public ResponseEntity<List<Product>> fetchAll(){
		List<Product> list=service.showAllProducts();
		ResponseEntity<List<Product>> response=new ResponseEntity<>(list,HttpStatus.OK);
		return response;
	}
	
	@DeleteMapping("products/delete/{id}")
	public ResponseEntity<Boolean> deleteProduct(@PathVariable("id") int id ){
		boolean result=service.remove(id);
		ResponseEntity<Boolean> response=new ResponseEntity<>(result,HttpStatus.OK);
		return response;
	}
	@PutMapping("/products/update/{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody ProductDto dto , @PathVariable("id") int id){
		Product product=new Product();
		product.setId(dto.getId());
		product.setName(dto.getName());
		product.setPrice(dto.getPrice());
		
		product = service.save(product);
		ResponseEntity<Product> response=new ResponseEntity<>(HttpStatus.OK);
		return response;
	}

	@ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFound(ProductNotFoundException ex) {
        Log.error("handlerProductNotFound()", ex);
        String msg = ex.getMessage();
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        return response;
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleAll(Throwable ex) {
        Log.error("handleAll()", ex);// this will get logged
        String msg = ex.getMessage();
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }

}