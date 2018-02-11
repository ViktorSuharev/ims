package com.visu.align.ims.controller;

import com.visu.align.ims.entity.Product;
import com.visu.align.ims.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/service/product")
public class ProductServiceRestController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/search/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getProductByName(@PathVariable("name") String name) {
        List<Product> products = productService.getProductByName(name);
        return getResponse(products);
    }

    @RequestMapping(value = "/search/brand/{brand}", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getProductByBrand(@PathVariable("brand") String brand) {
        List<Product> products = productService.getProductByBrand(brand);
        return getResponse(products);
    }

    @RequestMapping(value = "/leftovers", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getLeftoverProducts() {
        List<Product> leftoverProducts = productService.getLeftOvers();
        return getResponse(leftoverProducts);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@RequestBody Product product) {
        productService.deleteProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private ResponseEntity<List<Product>> getResponse(List<Product> products) {
        return products.isEmpty() ?
                new ResponseEntity<>(products, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
