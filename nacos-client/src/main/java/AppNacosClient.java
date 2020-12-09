import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName AppNacosClient
 * Description  TODO
 * @Author Administrator
 * @Date 2020/12/9 21:21
 * @Version 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.netflix.client.config"})
@EnableDiscoveryClient
//@ComponentScan("com")
public class AppNacosClient {

    public static void main(String[] args) {
        SpringApplication.run(AppNacosClient.class);
    }
r
    @RestController
    public class NacosController{
        @Autowired
        private RestTemplate restTemplate;
        @RequestMapping("/getUser")
        public String echoAppName(){
            return restTemplate.getForObject("http://nacos-user/getUser",String.class);
        }

    }

    //Instantiate RestTemplate Instance
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){

        return new RestTemplate();
    }

}
