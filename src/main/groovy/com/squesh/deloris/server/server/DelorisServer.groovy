package com.squesh.deloris.server.server

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.CrossOrigin

@SpringBootApplication
@CrossOrigin
class DelorisServer {
    static void main(String[] args) {
        SpringApplication.run(DelorisServer.class, args)
    }
}