package com.twitter.service;

import com.twitter.model.Tweet;
import com.twitter.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetServiceImpl implements TweetService {

    @Autowired
    private TweetRepository repository;

    @Override
    public List<Tweet> retrieveLast10Tweets() {
        return repository.findFirst10ByOrderByCreationDateDesc();
    }
}
