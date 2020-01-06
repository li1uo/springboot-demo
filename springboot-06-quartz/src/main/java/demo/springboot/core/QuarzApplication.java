package demo.springboot.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author LILUO
 */
@SpringBootApplication
@MapperScan("demo.springboot.core.mapper")
public class QuarzApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuarzApplication.class, args);
    }
}
