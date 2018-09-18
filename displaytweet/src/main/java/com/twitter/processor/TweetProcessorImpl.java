package com.twitter.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.twitter.dto.TweetDTO;
import com.twitter.model.Tweet;
import com.twitter.repository.TweetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class TweetProcessorImpl implements TweetProcessor {

    private static final Logger LOGGER = Logger.getLogger(TweetProcessorImpl.class.getName());
    private static final int MAX_NUMBER_OF_MESSAGES = 50;
    /**
     * Maximum time in milliseconds
     */
    private static final long MAX_TIME_IN_MS = 30000;
    @Autowired
    private TweetRepository tweetRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<TweetDTO> process(InputStream stream) throws IOException {
        Preconditions.checkNotNull(stream);
        List<TweetDTO> tweetDTOS = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
        mapper.setDateFormat(dateFormat);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        int tweetCount = 0;
        long startTime = System.currentTimeMillis();

        while (tweetCount < MAX_NUMBER_OF_MESSAGES && ((System.currentTimeMillis() - startTime) < MAX_TIME_IN_MS)) {
            String line = reader.readLine();
            // If no line keep spinning until 30 seconds.
            if (Strings.isNullOrEmpty(line)) {
                continue;
            }
            TweetDTO tweetDTO = mapper.readValue(line, TweetDTO.class);
            if (tweetDTO.getAuthorDTO() != null && tweetDTO.getAuthorDTO().getCreationDate() != null) {
                tweetDTOS.add(tweetDTO);
                tweetCount++;
            } else {
                System.out.println("Why no AuthorDTO here ??, TweetDTO seems to be something else " + line);
            }

        }
        LOGGER.info("Number of Input Tweets: " + tweetCount);
        List<Tweet> tweets = tweetDTOS.stream().map(this::convertToEntity).collect(Collectors.toList());
        tweetRepository.saveAll(tweets);
        return tweetDTOS;

    }

    private Tweet convertToEntity(TweetDTO tweetDTO) {
        return modelMapper.map(tweetDTO, Tweet.class);

    }

}
