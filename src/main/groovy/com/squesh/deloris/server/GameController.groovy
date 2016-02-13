package com.squesh.deloris.server

import org.springframework.messaging.simp.annotation.SubscribeMapping
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GameController {

    @RequestMapping(value = "/hello")
    String hello() {
        return "privet yoba"
    }

//    @SubscribeMapping("/game.players")

}
