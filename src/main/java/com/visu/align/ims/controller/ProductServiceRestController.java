package com.visu.align.ims.controller;

import com.visu.align.ims.controller.wrapper.ListWrapper;
import com.visu.align.ims.controller.wrapper.MessageWrapper;
import com.visu.align.ims.controller.wrapper.ObjectWrapper;
import com.visu.align.ims.entity.Product;
import com.visu.align.ims.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductServiceRestController {

    private static final String ADD_SUCCESS = "Adding is successful";
    private static final String UPDATE_SUCCESS = "Putting is successful";
    private static final String DELETE_SUCCESS = "Deleting is successful";

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ObjectWrapper> getProductById(@PathVariable("id") BigInteger id) {
        Product product = productService.getProductById(id);
        ObjectWrapper objWrapper = createProductWrapper(product);

        return buildResponse(objWrapper);
    }

    @RequestMapping(value = "/search/name/{name}", method = RequestMethod.GET)
    public HttpEntity<ResourceSupport> getProductsByName(@PathVariable("name") String name) {
        List<Product> products = productService.getProductsByName(name);
        ResourceSupport listWrapper = getWrappedList(products);

        return buildResponse(listWrapper);
    }

    @RequestMapping(value = "/search/brand/{brand}", method = RequestMethod.GET)
    public HttpEntity<ListWrapper> getProductsByBrand(@PathVariable("brand") String brand) {
        List<Product> products = productService.getProductsByBrand(brand);
        ListWrapper listWrapper = getWrappedList(products);

        return buildResponse(listWrapper);
    }

    @RequestMapping(value = "/leftovers", method = RequestMethod.GET)
    public HttpEntity<ResourceSupport> getLeftovers() {
        List<Product> products = productService.getLeftovers();
        ResourceSupport listWrapper = getWrappedList(products);

        return buildResponse(listWrapper);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        ResourceSupport messageWrapper = new MessageWrapper("Add product");
        messageWrapper.add(
                ControllerLinkBuilder.linkTo(
                        ControllerLinkBuilder.methodOn(ProductServiceRestController.class)
                                .addProduct(product)).withSelfRel());

        return new ResponseEntity<>(ADD_SUCCESS, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateProduct(@PathVariable("id") BigInteger id, @RequestBody Product product) {
        productService.updateProduct(id, product);
        return new ResponseEntity<>(UPDATE_SUCCESS, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteProduct(@PathVariable("id") BigInteger id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(DELETE_SUCCESS, HttpStatus.OK);
    }

    private ListWrapper getWrappedList(List<Product> products) {
        if (products.isEmpty()) {
            return null;
        }

        List<ObjectWrapper> listOfWrappedObj = new ArrayList<>();
        for (Product product : products) {
            ObjectWrapper objWrapper = createProductWrapper(product);
            listOfWrappedObj.add(objWrapper);
        }

        ListWrapper listWrapper = new ListWrapper(listOfWrappedObj);
        listWrapper.add(
                ControllerLinkBuilder.linkTo(
                        ControllerLinkBuilder.methodOn(ProductServiceRestController.class)
                                .getLeftovers()).withSelfRel());

        return listWrapper;
    }

    private ObjectWrapper createProductWrapper(Product product) {
        if (product == null) {
            return null;
        }

        ObjectWrapper objWrapper = new ObjectWrapper(product);
        objWrapper.add(
                ControllerLinkBuilder.linkTo(
                        ControllerLinkBuilder.methodOn(ProductServiceRestController.class)
                                .getProductById(product.getId())).withSelfRel());

        return objWrapper;
    }

    private <T> ResponseEntity<T> buildResponse(T obj) {
        return obj == null ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(obj, HttpStatus.OK);
    }
}
