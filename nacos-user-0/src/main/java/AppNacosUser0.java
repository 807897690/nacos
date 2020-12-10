import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName AppNacosUser
 * Description  TODO
 * @Author Administrator
 * @Date 2020/12/9 21:30
 * @Version 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.netflix.client.config"})
@EnableDiscoveryClient
public class AppNacosUser0 {

    public static void main(String[] args) {
        SpringApplication.run(AppNacosUser0.class);
    }

    @RestController
    public class NacosController{

        @RequestMapping("/getUser")
        public String echoAppName(String name){
            return "user0";
        }

    }

}
