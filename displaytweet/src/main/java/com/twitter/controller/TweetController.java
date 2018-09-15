package com.twitter.controller;

import com.twitter.dto.TweetContentDTO;
import com.twitter.model.Tweet;
import com.twitter.oauth.TwitterClient;
import com.twitter.repository.TweetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TweetController {


    @Autowired
    @Qualifier("filterTwitterClient")
    TwitterClient client;
    @Autowired
    TweetRepository repository;
    @Autowired
    ModelMapper modelMapper;

    @RequestMapping("/tweets")
    public List<TweetContentDTO> getTweets() {
        List<Tweet> tweets = repository.findFirst10ByOrderByCreationDateDesc();
        return tweets.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private TweetContentDTO convertToDto(Tweet tweet) {
        return modelMapper.map(tweet, TweetContentDTO.class);
    }


}
