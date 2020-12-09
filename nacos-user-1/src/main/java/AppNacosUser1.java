import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AppNacosUser
 * Description  TODO
 * @Author Administrator
 * @Date 2020/12/9 21:30
 * @Version 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.netflix.client.config"})
@EnableDiscoveryClient
public class AppNacosUser1 {

    public static void main(String[] args) {
        SpringApplication.run(AppNacosUser1.class);
    }

    @RestController
    public class NacosController{
        
        @RequestMapping("/getUser")
        public String echoAppName(){
            return "user1";
        }

    }

}
