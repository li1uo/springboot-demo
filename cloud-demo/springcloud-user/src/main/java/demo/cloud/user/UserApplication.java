package demo.cloud.user;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author LILUO
 * @date 2019/02/13
 */
@ComponentScan(basePackages = {"demo.springboot", "demo.cloud"})
@SpringCloudApplication
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}

