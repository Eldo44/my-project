package com.twitter.service;

import com.twitter.model.Tweet;

import java.util.List;

public interface TweetService {
    List<Tweet> retrieveLast10Tweets();
}
