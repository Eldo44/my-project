package com.twitter;


import com.twitter.controller.TweetController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRestApplicationTest {

    @Autowired
    private TweetController controller;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }
}
