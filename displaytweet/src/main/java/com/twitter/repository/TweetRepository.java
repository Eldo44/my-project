package com.twitter.repository;

import com.twitter.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TweetRepository extends JpaRepository<Tweet, String> {
    List<Tweet> findFirst10ByOrderByCreationDateDesc();
}
