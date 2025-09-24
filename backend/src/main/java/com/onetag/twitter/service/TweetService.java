package com.onetag.twitter.service;

import com.onetag.twitter.dto.TweetRequestDto;
import com.onetag.twitter.dto.TweetResponseDto;
import com.onetag.twitter.entity.Tweet;
import com.onetag.twitter.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TweetService {

    @Autowired
    private TweetRepository tweetRepository;
    
    /**
     * Get all tweets
     * Note: Sorting will be handled on the frontend as per requirements
     */
    public List<TweetResponseDto> getAllTweets() {
        List<Tweet> tweets = tweetRepository.findAll();
        return tweets.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * Get tweet by ID
     */
    public Optional<TweetResponseDto> getTweetById(Long id) {
        return tweetRepository.findById(id)
                .map(this::convertToResponseDto);
    }

    /**
     * Create a new tweet
     */
    public TweetResponseDto createTweet(TweetRequestDto tweetRequestDto) {
        Tweet tweet = new Tweet();
        tweet.setAuthor(tweetRequestDto.getAuthor());
        tweet.setMessage(tweetRequestDto.getMessage());
        tweet.setDate(Instant.now().toEpochMilli()); // Store current time in epoch format

        Tweet savedTweet = tweetRepository.save(tweet);
        return convertToResponseDto(savedTweet);
    }

    /**
     * Update an existing tweet
     */
    public Optional<TweetResponseDto> updateTweet(Long id, TweetRequestDto tweetRequestDto) {
        return tweetRepository.findById(id)
                .map(tweet -> {
                    tweet.setAuthor(tweetRequestDto.getAuthor());
                    tweet.setMessage(tweetRequestDto.getMessage());
                    // Keep the original date when updating
                    Tweet updatedTweet = tweetRepository.save(tweet);
                    return convertToResponseDto(updatedTweet);
                });
    }

    /**
     * Delete a tweet
     */
    public boolean deleteTweet(Long id) {
        if (tweetRepository.existsById(id)) {
            tweetRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Search tweets by author
     */
    public List<TweetResponseDto> getTweetsByAuthor(String author) {
        List<Tweet> tweets = tweetRepository.findByAuthorIgnoreCase(author);
        return tweets.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * Search tweets by message content
     */
    public List<TweetResponseDto> searchTweetsByMessage(String searchText) {
        List<Tweet> tweets = tweetRepository.findByMessageContainingIgnoreCase(searchText);
        return tweets.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * Convert Tweet entity to TweetResponseDto
     */
    private TweetResponseDto convertToResponseDto(Tweet tweet) {
        return new TweetResponseDto(
                tweet.getId(),
                tweet.getAuthor(),
                tweet.getMessage(),
                tweet.getFormattedDate()
        );
    }
}