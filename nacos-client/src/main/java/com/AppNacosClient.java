package com;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.wzy.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
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
@EnableFeignClients
@ComponentScan("com.wzy")
public class AppNacosClient {

    public static void main(String[] args) {
        SpringApplication.run(AppNacosClient.class);
    }

    @RestController
    public class NacosController{
        @Autowired
        private RestTemplate restTemplate;
        @Autowired
        private UserClient userClient;

        @RequestMapping("/getUser")
        @SentinelResource(value = "/getUser", blockHandler = "blockHandler")
        public String echoAppName(String name){
            return restTemplate.getForObject("http://nacos-user/getUser",String.class);
        }

        public String blockHandler(String name, BlockException e) {
            e.printStackTrace();
            return "熔断处理";
        }


        @RequestMapping("/getUser1")
        public String getUser1(String name){
            return userClient.getUser(name);
        }
    }

    //Instantiate RestTemplate Instance
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){

        return new RestTemplate();
    }

}
