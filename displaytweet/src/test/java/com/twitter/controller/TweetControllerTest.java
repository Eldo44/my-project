package com.twitter.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TweetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnLast10Tweets() throws Exception {
        this.mockMvc.perform(get("/tweets")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("[{\"messageText\":\"Hello 1\"},{\"messageText\":\"Hello 2\"}," +
                        "{\"messageText\":\"Hello 3\"},{\"messageText\":\"Hello 4\"},{\"messageText\":\"Hello 5\"}," +
                        "{\"messageText\":\"Hello 6\"},{\"messageText\":\"Hello 7\"},{\"messageText\":\"Hello 8\"}," +
                        "{\"messageText\":\"Hello 9\"},{\"messageText\":\"Hello 10\"}]"));
    }
}