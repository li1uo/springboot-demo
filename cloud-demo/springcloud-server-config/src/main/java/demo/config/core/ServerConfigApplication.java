package demo.config.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author LILUO
 * @date 2019/2/18
 */
@EnableConfigServer
@SpringBootApplication
public class ServerConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerConfigApplication.class, args);
    }

}
