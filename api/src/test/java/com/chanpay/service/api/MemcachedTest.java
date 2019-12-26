package com.chanpay.service.api;

import com.chanpay.service.api.util.MemcachedConfig;
import lombok.extern.slf4j.Slf4j;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeoutException;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-24 10:39
 * @Description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class MemcachedTest {
    @Autowired
    MemcachedClient memcachedClient;

    @Test
    public void testSetValue() {
        try {
            memcachedClient.set("abc",0,"asssss");
            String access_token = memcachedClient.get("abc");
            log.info("从memcached当中获取到的值是："+access_token);
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }

    }
}
