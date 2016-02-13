package com.squesh.deloris.server

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.socket.config.annotation.EnableWebSocket

@SpringBootApplication
@EnableWebSocket
@EnableWebMvc
class DelorisServer {
    public static void main(String[] args) {
        SpringApplication.run(DelorisServer.class, args);
    }
}