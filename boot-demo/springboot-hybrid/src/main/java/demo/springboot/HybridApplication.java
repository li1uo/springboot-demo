package demo.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class HybridApplication {

    public static void main(String[] args) {
        SpringApplication.run(HybridApplication.class, args);
    }

}
