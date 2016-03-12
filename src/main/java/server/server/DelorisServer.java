package server.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
public class DelorisServer {
    public static void main(String[] args) {
        SpringApplication.run(DelorisServer.class, args);
    }
}
