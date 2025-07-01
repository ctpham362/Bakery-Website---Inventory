package com.example.demo.controllers;

import com.example.demo.domain.Product;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 *
 *
 *
 *
 */
@Controller
public class BuyProductsController {

    @Autowired
    private ProductRepository productRepository;

@GetMapping("/buyProducts")
    public String buyProducts(@RequestParam("productID") long theId, Model theModel) {
        Optional<Product> product = productRepository.findById(theId);
        int inv = product.get().getInv();

        if (inv == 0) {
            return "/failure";
        } else {
            product.get().setInv(inv - 1);
            productRepository.save(product.get());
            return "/success";
        }
    }
}
