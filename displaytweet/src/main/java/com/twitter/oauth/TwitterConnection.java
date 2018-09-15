package com.twitter.oauth;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.common.base.Preconditions;
import com.twitter.dto.TweetDTO;
import com.twitter.processor.TweetProcessor;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TwitterConnection implements AutoCloseable {

    private HttpResponse response;
    private InputStream stream;
    private TweetProcessor processor;

    TwitterConnection(TweetProcessor processor) {

        this.processor = Preconditions.checkNotNull(processor);
    }

    /**
     * Opens a Connection
     *
     * @param request http request
     * @throws IOException throws IO exception
     */
    void connect(HttpRequest request) throws IOException {
        this.response = request.execute();
        this.stream = response.getContent();
    }


    List<TweetDTO> processResponse() throws IOException {
        return processor.process(stream);
    }

    /**
     * Cleans up all resources
     */
    @Override
    public void close() throws IOException {
        if (response != null) {
            response.disconnect();
            stream.close();
        }
    }
}
