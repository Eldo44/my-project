package com.twitter;

import com.twitter.oauth.TwitterAuthenticationException;
import com.twitter.oauth.TwitterClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication(scanBasePackages = "com.twitter")
@ComponentScan(basePackages = "com.twitter")
@EnableJpaRepositories(basePackages = "com.twitter.repository")
@EntityScan("com.twitter.model")
public class SpringBootRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestApplication.class, args);
    }

    private ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
    @Autowired
    @Qualifier("filterTwitterClient")
    private TwitterClient client;

    @PostConstruct
    private void processAllTweets() {
        exec.scheduleAtFixedRate(() -> {
            try {
                client.track("Trump");
            } catch (TwitterAuthenticationException | IOException e) {
                e.printStackTrace();
            }
        }, 0, 1, TimeUnit.HOURS);
    }

    @PreDestroy
    private void shutdown() {
        exec.shutdownNow();
    }

}