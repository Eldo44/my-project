package com.twitter.repository;

import com.twitter.model.Tweet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TwitterRepositoryTest {


    @Autowired
    private TweetRepository repository;

    @Test
    public void findFirst10ByOrderByCreationDateDescTest() {

        //Given
        List<Tweet> randomTweets = repository.findAll();
        List<Tweet> expectedTweets = randomTweets.stream().sorted((a, b) -> b.getCreationDate().
                compareTo(a.getCreationDate())).limit(10).collect(Collectors.toList());

        //When
        List<Tweet> tweets = repository.findFirst10ByOrderByCreationDateDesc();

        //Then
        assertThat(tweets.size()).isEqualTo(10);
        assertThat(tweets).isEqualTo(expectedTweets);
    }
}
