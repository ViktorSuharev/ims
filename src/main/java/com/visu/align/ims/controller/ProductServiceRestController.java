package com.visu.align.ims.controller;

import com.visu.align.ims.controller.wrapper.WrapperService;
import com.visu.align.ims.entity.Product;
import com.visu.align.ims.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import java.math.BigInteger;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/products")
public class ProductServiceRestController {

    private static final String ADD_SUCCESS = "Adding is successful";
    private static final String UPDATE_SUCCESS = "Updating is successful";
    private static final String DELETE_SUCCESS = "Deleting is successful";

    @Autowired
    private ProductService productService;

    @Autowired
    private WrapperService wrapperService;

    @PermitAll
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResourceSupport> getProductById(@PathVariable("id") BigInteger id) {
        Product product = productService.getProductById(id);
        ResourceSupport objWrapper = wrapperService.createProductWrapper(product);

        return buildResponse(objWrapper, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResourceSupport> addProduct(@RequestBody Product product) {
        productService.addProduct(product);

        ResponseEntity responseEntity = methodOn(ProductServiceRestController.class).addProduct(product);
        ResourceSupport messageWrapper = wrapperService.createWrapper(responseEntity, ADD_SUCCESS);

        return buildResponse(messageWrapper, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResourceSupport> updateProduct(@PathVariable("id") BigInteger id, @RequestBody Product product) {
        productService.updateProduct(id, product);

        ResponseEntity responseEntity = methodOn(ProductServiceRestController.class).updateProduct(id, product);
        ResourceSupport messageWrapper = wrapperService.createWrapper(responseEntity, UPDATE_SUCCESS);

        return buildResponse(messageWrapper, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResourceSupport> deleteProduct(@PathVariable("id") BigInteger id) {
        productService.deleteProduct(id);

        ResponseEntity responseEntity = methodOn(ProductServiceRestController.class).deleteProduct(id);
        ResourceSupport messageWrapper = wrapperService.createWrapper(responseEntity, DELETE_SUCCESS);

        return buildResponse(messageWrapper, HttpStatus.OK);
    }

    @PermitAll
    @RequestMapping(value = "/search/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<ResourceSupport> getProductsByName(@PathVariable("name") String name) {
        List<Product> products = productService.getProductsByName(name);
        ResourceSupport listWrapper = wrapperService.createWrappedList(products);

        return buildResponse(listWrapper, HttpStatus.OK);
    }

    @PermitAll
    @RequestMapping(value = "/search/brand/{brand}", method = RequestMethod.GET)
    public ResponseEntity<ResourceSupport> getProductsByBrand(@PathVariable("brand") String brand) {
        List<Product> products = productService.getProductsByBrand(brand);
        ResourceSupport listWrapper = wrapperService.createWrappedList(products);

        return buildResponse(listWrapper, HttpStatus.OK);
    }

    @PermitAll
    @RequestMapping(value = "/leftovers", method = RequestMethod.GET)
    public ResponseEntity<ResourceSupport> getLeftovers() {
        List<Product> products = productService.getLeftovers();
        ResourceSupport listWrapper = wrapperService.createWrappedList(products);

        return buildResponse(listWrapper, HttpStatus.OK);
    }

    private ResponseEntity<ResourceSupport> buildResponse(ResourceSupport obj, HttpStatus httpStatus) {
        return obj == null ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(obj, httpStatus);
    }
}
