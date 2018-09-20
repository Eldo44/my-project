package com.twitter.service;

import com.twitter.dto.TweetContentDTO;
import com.twitter.model.Tweet;
import com.twitter.repository.TweetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TweetServiceImpl implements TweetService {

    @Autowired
    private TweetRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TweetContentDTO> retrieveLast10Tweets() {
        List<Tweet> tweets = repository.findFirst10ByOrderByCreationDateDesc();
        return tweets.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private TweetContentDTO convertToDto(Tweet tweet) {
        return modelMapper.map(tweet, TweetContentDTO.class);
    }
}
