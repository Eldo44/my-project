package com.twitter.processor;

import com.twitter.dto.TweetDTO;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface TweetProcessor {

    List<TweetDTO> process(InputStream stream) throws IOException;

}
