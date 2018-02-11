package com.visu.align.ims.controller;

import com.visu.align.ims.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/service/product")
public class ProductServiceRestController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/search/name/{productName}")
    public String getProductByName(@PathVariable String productName) {
        return null;
    }

    @RequestMapping("/search/brand/{productBrand}")
    public String getProductByBrand(@PathVariable String productBrand) {
        return null;
    }

    @RequestMapping("/leftovers")
    public String getLeftoverProducts() {
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addProduct() {
        return null;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateProduct() {
        return null;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct() {
        return null;
    }
}
