package com.luttece.luttece;

import com.luttece.luttece.po.User;
import com.luttece.luttece.service.iml.UserserviceIml;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LutteceApplicationTests {
    @Autowired
    private UserserviceIml userserviceIml ;
    @Test
    void contextLoads() {
        String aa = userserviceIml.getString("chen");
        System.out.println(aa);
    }
    @Test
      void  t2(){
        User user = userserviceIml.selectById("2");
        System.out.println(user);
    }

}
