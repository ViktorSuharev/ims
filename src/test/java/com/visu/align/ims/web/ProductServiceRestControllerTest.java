package com.visu.align.ims.web;

import com.visu.align.ims.web.wrapper.impl.WrapperServiceImpl;
import com.visu.align.ims.service.ProductService;
import com.visu.align.ims.util.TestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductServiceRestController.class, secure = false)
@ContextConfiguration(classes = {WrapperServiceImpl.class})
public class ProductServiceRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
//
//    @Autowired
//    WebApplicationContext wac;

    @MockBean
    private ProductService productService;

    @Test
    public void partyNotFoundTest() throws Exception {
        mockMvc.perform(get("/parties/2").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    public void getProductById() throws Exception {
//        mockMvc =  MockMvcBuilders.webAppContextSetup(this.wac).alwaysExpect(forwardedUrl(null)).build();
        given(productService.getProductById(anyObject())).willReturn(TestUtil.product1);

        MvcResult result = mockMvc.perform(get("/products/1")
//                .param("id", "1")
//                .header("Authorization", "Bearer sdfds")
                .accept(APPLICATION_JSON))
//                .contentType(APPLICATION_JSON))
//                .andExpect(status().isOk())
                .andReturn();

        System.out.println("content " + result.getResponse().getContentAsString());
        System.out.println("status " + result.getResponse().getStatus());
        System.out.println("URI " + result.getRequest().getRequestURI());
        System.out.println("URL " + result.getRequest().getRequestURL());
        System.out.println("port " + result.getRequest().getRemotePort());

        String expected = "{\n" +
                "    \"content\": {\n" +
                "        \"name\": \"prod1\",\n" +
                "        \"brand\": \"brand1\",\n" +
                "        \"price\": 1,\n" +
                "        \"quantity\": 1\n" +
                "    },\n" +
                "    \"_links\": {\n" +
                "        \"self\": {\n" +
                "            \"href\": \"http://localhost:8080/align/products/1\"\n" +
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