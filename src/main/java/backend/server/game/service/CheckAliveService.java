package backend.server.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class CheckAliveService {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    private ScheduledExecutorService executorService;

    @PostConstruct
    public void startUp() {
        executorService = Executors.newScheduledThreadPool(4);
        executorService.scheduleWithFixedDelay(this::aliveChecking, 10, 10, TimeUnit.SECONDS);
    }

    @PreDestroy
    public void tearDown() {
        executorService.shutdown();
    }

    private void aliveChecking() {
        System.out.println("check is alive");
    }
}
