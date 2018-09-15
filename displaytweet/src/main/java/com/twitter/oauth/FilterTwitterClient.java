package com.twitter.oauth;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.common.base.Strings;
import com.twitter.processor.TweetProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FilterTwitterClient implements TwitterClient {

    @Autowired
    TwitterAuthenticator authenticator;
    @Autowired
    @Qualifier("tweetProcessorImpl")
    TweetProcessor processor;
    public final static String FILTER_STREAMING_API = "https://stream.twitter.com/1.1/statuses/filter.json";


    @Override
    public void track(String trackParameter) throws TwitterAuthenticationException, IOException {
        HttpRequestFactory factory = authenticator.getAuthorizedHttpRequestFactory();
        HttpRequest request = factory.buildGetRequest(constuctURL(trackParameter));
        try (TwitterConnection connection = new TwitterConnection(processor)) {
            connection.connect(request);
            connection.processResponse();
        }

    }

    private GenericUrl constuctURL(String trackParameter) {
        if (Strings.isNullOrEmpty(trackParameter)) {
            return new GenericUrl(FILTER_STREAMING_API);
        } else {
            return new GenericUrl(FILTER_STREAMING_API.concat("?track=" + trackParameter));
        }
    }

}
