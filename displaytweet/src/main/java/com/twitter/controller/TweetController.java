package com.twitter.controller;

import com.twitter.dto.TweetContentDTO;
import com.twitter.model.Tweet;
import com.twitter.oauth.TwitterClient;
import com.twitter.repository.TweetRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value = "Twitter", description = "Extracts and displays tweets")
public class TweetController {


    @Autowired
    @Qualifier("filterTwitterClient")
    TwitterClient client;
    @Autowired
    TweetRepository repository;
    @Autowired
    ModelMapper modelMapper;

    @ApiOperation(value = "Displays last 10 tweets tracked on a keyword", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "/tweets", method = RequestMethod.GET, produces = "application/json")
    public List<TweetContentDTO> getTweets() {
        List<Tweet> tweets = repository.findFirst10ByOrderByCreationDateDesc();
        return tweets.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private TweetContentDTO convertToDto(Tweet tweet) {
        return modelMapper.map(tweet, TweetContentDTO.class);
    }


}
