package com.rory;

import com.rory.dao.UserMapper;
import com.rory.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTest {

     @Autowired
     protected WebApplicationContext wac;
     protected MockMvc mockMvc;
     @Before
     public void setup(){
         mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
     }
    @Test
    public void contextLoads() throws  Exception {
        mockMvc.perform(get("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));

    }

    @Autowired
    UserMapper userMapper;
     @Test
     public void test(){
         User user=userMapper.selectByUsername("admin");
         System.out.println(user.getUsername());
    }


}
