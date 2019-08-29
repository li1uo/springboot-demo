package demo.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot启动类
 * @author LILUO
 * @date 19/08/28
 */
@MapperScan(basePackages = "demo.springboot.mapper")
@SpringBootApplication
public class AtomikosApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtomikosApplication.class, args);
    }

}
