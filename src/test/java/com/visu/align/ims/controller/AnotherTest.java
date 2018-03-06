package com.visu.align.ims.controller;

import com.visu.align.ims.controller.wrapper.WrapperService;
import com.visu.align.ims.controller.wrapper.impl.WrapperServiceImpl;
import com.visu.align.ims.service.ProductService;
import com.visu.align.ims.service.ProductServiceImpl;
import com.visu.align.ims.util.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {WrapperServiceImpl.class})
@WebMvcTest(value = ProductServiceRestController.class, secure = false)
public class AnotherTest {

    @MockBean
    private ProductService productService;

    @Autowired
    private WrapperService wrapperService;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc =  MockMvcBuilders.webAppContextSetup(this.wac).alwaysExpect(forwardedUrl(null)).build();
    }

    @Test
    public void getAccount() throws Exception {
        given(productService.getProductById(anyObject())).willReturn(TestUtil.product1);
//        given(wrapperService.createWrappedList(anyObject())).willReturn();
        MvcResult result = mockMvc.perform(get("/products/1")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).andReturn();

        System.out.println(result.getResponse().getContentAsString());
        System.out.println(result.getResponse().getStatus());
        
    }

}