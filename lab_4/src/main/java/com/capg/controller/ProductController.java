package com.capg.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.capg.entities.Product;
import com.capg.service.IProductService;

import java.util.List;

import com.capg.entities.Product;
import com.capg.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {
	 private static Logger Log= LoggerFactory.getLogger(ProductController.class);
	
	 @Autowired
	 private IProductService service;
	
	 @GetMapping("/find")
	    public ModelAndView findPage() {
	    	return new ModelAndView("findproduct");
	    }
	    @GetMapping("/processfindproduct")
	    public ModelAndView productDetails(@RequestParam("productid") int id)
	    {
	    	System.out.println("Inside Product Details");
	    	
	    	Product product=service.findProductById(id);
	    	return new ModelAndView("productdetails","product",product);
	    }
	    
	    @GetMapping("/register")
	    public ModelAndView registerCustomer() {
	    	return new ModelAndView("productregister");
	    }
	    
	    @GetMapping("/processregister")
	    public ModelAndView registerCustomer(@RequestParam("id") int id,@RequestParam("name") String productName,@RequestParam("price") double price) {
	        Product product=new Product();
	        product.setName(productName);
	        product.setPrice(price);
	        product.setId(id);
	      
	        product=service.save(product);
	       
	        return new ModelAndView("productdetails","product",product);
	       
	    }
	      
	    @GetMapping("/displayall")
	    public ModelAndView displayAll(){
	    	List<Product> list=service.showAllProducts();
	    	ModelAndView modelAndView=new ModelAndView("listproducts","list",list);
	    	return modelAndView;
	        
	    }
	       
}
