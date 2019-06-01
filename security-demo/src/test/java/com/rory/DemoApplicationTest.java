package com.rory;
import com.jayway.jsonpath.JsonPath;
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

import javax.xml.crypto.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

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
     public void test() throws Exception {
         String str=mockMvc.perform(get("/user")
                            .param("id","1")
                            .param("size","10")
                            .contentType(MediaType.APPLICATION_JSON_UTF8))
                            .andExpect(MockMvcResultMatchers.status().isOk()                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         )
                            .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value("3"))
                            .andReturn().getResponse().getContentAsString();
                 System.out.println("getUserlist"+str);
    }

    @Test
    public void test1() throws Exception {
     String str=  mockMvc.perform(get("/user/1")
                 .contentType(MediaType.APPLICATION_JSON))
                 .andExpect(MockMvcResultMatchers.status().isOk())
                 .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("sujinquan"))
                .andReturn().getResponse().getContentAsString();
     System.out.println("getuserDetail:"+str);
    }
    @Test
    public void test2() throws Exception {
         mockMvc.perform(get("/user/a")
                 .contentType(MediaType.APPLICATION_JSON))
                 .andExpect(MockMvcResultMatchers.status().is4xxClientError());

    }
    @Test
    public void creatUserSuccess() throws Exception {
         Long  timenoew=new Date().getTime();
         String str="{\n" +
                 "\t\"id\": \"1\",\n" +
                 "\t\"username\": \"sujinquan\",\n" +
                 "\t\"password\": \"null\",\n" +
                 "\t\"birthday\": "+timenoew+"\n" +
                 "}";
         System.out.println("test 获取的当前时间："+timenoew);
        String returstru= mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON_UTF8).content(str))
                 .andExpect(MockMvcResultMatchers.status().isOk())
                 .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                 .andReturn().getResponse().getContentAsString();
        System.out.println("新建的用户信息："+returstru);

    }
     @Test
     public void updateUserInfo() throws Exception {
         Date date=new Date(LocalDateTime.now().plusMinutes(20).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());//获取20分钟后的时间戳
         Long  timenoew=new Date().getTime();
         Long  future=timenoew+1000*60;
         String str="{\n" +
                 "\t\"id\": \"1\",\n" +
                 "\t\"username\": \"sujinquan\",\n" +
                 "\t\"pwdword\": \"null\",\n" +
                 "\t\"birthday\": "+future+"\n" +
                 "}";
         String reStr=mockMvc.perform(put("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8).content(str))
                 .andExpect(MockMvcResultMatchers.status().isOk())
                 .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                 .andReturn().getResponse().getContentAsString();
         System.out.println("修改后的用户信息"+reStr);
     }
     @Test
    public void deleteUser() throws Exception {
          mockMvc.perform(delete("/user/2").contentType(MediaType.APPLICATION_JSON_UTF8))
                  .andExpect(MockMvcResultMatchers.status().isOk());
     }

}
