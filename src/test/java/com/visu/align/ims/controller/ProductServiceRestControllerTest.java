package com.visu.align.ims.controller;

import com.visu.align.ims.service.ProductService;
import com.visu.align.ims.util.TestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductServiceRestController.class)
public class ProductServiceRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

/*
    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }
*/

    @Test
    public void getProductById() throws Exception {
        given(productService.getProductById(anyObject())).willReturn(TestUtil.product1);

        MvcResult result = mvc.perform(get("/products")
                .param("id", "1")
                .contentType(MediaType.ALL))
                .andExpect(status().isOk())
                .andReturn();

        String expected = "{\n" +
                "    \"content\": {\n" +
                "        \"name\": \"prod1\",\n" +
                "        \"brand\": \"brand1\",\n" +
                "        \"price\": 1,\n" +
                "        \"quantity\": 1\n" +
                "    },\n" +
                "    \"_links\": {\n" +
                "        \"self\": {\n" +
                "            \"href\": \"http://localhost:8080/products/1\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void getProductsByName() throws Exception {

    }

    @Test
    public void getProductsByBrand() throws Exception {
    }

    @Test
    public void getLeftovers() throws Exception {
    }

    @Test
    public void addProduct() throws Exception {
    }

    @Test
    public void updateProduct() throws Exception {
    }

    @Test
    public void deleteProduct() throws Exception {

    }
}