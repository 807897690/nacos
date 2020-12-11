package com.wzy.client;

import com.wzy.fallback.UserFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName UserClient
 * Description  TODO
 * @Author Administrator
 * @Date 2020/12/10 20:43
 * @Version 1.0
 */
@FeignClient(value = "nacos-user", fallback = UserFallBack.class)
public interface UserClient {

    @RequestMapping("/getUser")
    String getUser(@RequestParam("name") String name);

}
