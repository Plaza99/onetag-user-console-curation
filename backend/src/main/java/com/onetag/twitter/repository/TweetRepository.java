package com.onetag.twitter.repository;

import com.onetag.twitter.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
    
    /**
     * Find all tweets ordered by date descending (newest first)
     * This provides the base ordering that will be further refined in the service layer
     */
    @Query("SELECT t FROM Tweet t ORDER BY t.date DESC")
    List<Tweet> findAllOrderedByDateDesc();
    
    /**
     * Find tweets by author
     */
    List<Tweet> findByAuthorIgnoreCase(String author);
    
    /**
     * Find tweets containing specific text in the message
     */
    @Query("SELECT t FROM Tweet t WHERE LOWER(t.message) LIKE LOWER(CONCAT('%', :searchText, '%'))")
    List<Tweet> findByMessageContainingIgnoreCase(String searchText);
}