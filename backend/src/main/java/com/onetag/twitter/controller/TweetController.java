package com.onetag.twitter.controller;

import com.onetag.twitter.dto.TweetRequestDto;
import com.onetag.twitter.dto.TweetResponseDto;
import com.onetag.twitter.service.TweetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tweets")
@CrossOrigin(origins = "http://localhost:4200") // Allow Angular frontend
public class TweetController {

    @Autowired
    private TweetService tweetService;

    /**
     * GET /api/tweets - Get all tweets
     */
    @GetMapping
    public ResponseEntity<List<TweetResponseDto>> getAllTweets() {
        List<TweetResponseDto> tweets = tweetService.getAllTweets();
        return ResponseEntity.ok(tweets);
    }

    /**
     * GET /api/tweets/{id} - Get tweet by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<TweetResponseDto> getTweetById(@PathVariable Long id) {
        Optional<TweetResponseDto> tweet = tweetService.getTweetById(id);
        return tweet.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST /api/tweets - Create a new tweet
     */
    @PostMapping
    public ResponseEntity<TweetResponseDto> createTweet(@Valid @RequestBody TweetRequestDto tweetRequestDto) {
        TweetResponseDto createdTweet = tweetService.createTweet(tweetRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTweet);
    }

    /**
     * PUT /api/tweets/{id} - Update an existing tweet
     */
    @PutMapping("/{id}")
    public ResponseEntity<TweetResponseDto> updateTweet(
            @PathVariable Long id,
            @Valid @RequestBody TweetRequestDto tweetRequestDto) {
        Optional<TweetResponseDto> updatedTweet = tweetService.updateTweet(id, tweetRequestDto);
        return updatedTweet.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
    }

    /**
     * DELETE /api/tweets/{id} - Delete a tweet
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTweet(@PathVariable Long id) {
        boolean deleted = tweetService.deleteTweet(id);
        return deleted ? ResponseEntity.noContent().build() 
                      : ResponseEntity.notFound().build();
    }

    /**
     * GET /api/tweets/author/{author} - Get tweets by author
     */
    @GetMapping("/author/{author}")
    public ResponseEntity<List<TweetResponseDto>> getTweetsByAuthor(@PathVariable String author) {
        List<TweetResponseDto> tweets = tweetService.getTweetsByAuthor(author);
        return ResponseEntity.ok(tweets);
    }

    /**
     * GET /api/tweets/search?q={searchText} - Search tweets by message content
     */
    @GetMapping("/search")
    public ResponseEntity<List<TweetResponseDto>> searchTweets(@RequestParam("q") String searchText) {
        List<TweetResponseDto> tweets = tweetService.searchTweetsByMessage(searchText);
        return ResponseEntity.ok(tweets);
    }
}