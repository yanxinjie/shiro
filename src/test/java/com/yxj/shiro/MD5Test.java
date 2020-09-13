package com.yxj.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 严新捷
 * @Type MD5Test.java
 * @Desc
 * @date 2020/9/13 16:45
 */
@SpringBootTest
public class MD5Test {
    @Test
    public void testMD5() {
        String hashName = "md5";
        String pwd = "123456789";
        Object result = new SimpleHash(hashName, pwd,null,2);
        System.out.println(result);
    }
}
