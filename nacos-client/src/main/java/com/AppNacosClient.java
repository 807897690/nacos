package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * @ClassName com.AppNacosClient
 * Description  TODO
 * @Author Administrator
 * @Date 2020/12/9 21:21
 * @Version 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.netflix.client.config"})
@EnableDiscoveryClient
//@EnableFeignClients
//@ComponentScan("com")
public class AppNacosClient {

    public static void main(String[] args) {
        SpringApplication.run(AppNacosClient.class);
    }

//    @FeignClient(value = "nacos-user", fallback = UserFallBack.class)
//    public interface UserClient {
//
//        @RequestMapping("/getUser")
//        String getUser(@RequestParam("name") String name);
//
//    }
//
//    @Component
//    public class UserFallBack implements UserClient {
//
//        @Override
//        public String getUser(String name) {
//            return "降级方法";
//        }
//    }

    @RestController
    public class NacosController{
        @Autowired
        private RestTemplate restTemplate;
//        @Autowired
//        private UserClient userClient;

        @RequestMapping("/getUser")
        public String echoAppName(String name){
            return restTemplate.getForObject("http://nacos-user/getUser",String.class);
        }
//        @RequestMapping("/getUser1")
//        public String getUser1(String name){
//            return userClient.getUser(name);
//        }
    }

    //Instantiate RestTemplate Instance
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){

        return new RestTemplate();
    }

}
