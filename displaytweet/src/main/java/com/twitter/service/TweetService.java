package com.twitter.service;

import com.twitter.dto.TweetContentDTO;
import com.twitter.model.Tweet;

import java.util.List;

public interface TweetService {
    List<TweetContentDTO> retrieveLast10Tweets();
}
