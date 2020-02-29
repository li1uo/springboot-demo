package demo.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author LILUO
 * @date 2019/02/13
 */
@EnableEurekaServer
@SpringBootApplication(scanBasePackages = {"demo.cloud", "demo.springboot"})
public class EurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }

}

