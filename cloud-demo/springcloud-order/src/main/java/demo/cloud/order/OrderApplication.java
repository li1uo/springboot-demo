package demo.cloud.order;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author LILUO
 * @date 2019/02/13
 */
@ComponentScan(basePackages = "demo.springboot")
@EnableFeignClients
@SpringCloudApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}

