package com.onetag.twitter.config;

import com.onetag.twitter.entity.Tweet;
import com.onetag.twitter.repository.TweetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private TweetRepository tweetRepository;

    @Override
    public void run(String... args) throws Exception {
        if (tweetRepository.count() == 0) {
            seedTweets();
        }
    }

    private void seedTweets() {
        Instant now = Instant.now();
        
        List<Tweet> tweets = Arrays.asList(
            new Tweet("Alice Johnson", "Just had the most amazing coffee at the new cafe downtown! ☕", 
                     now.minus(5, ChronoUnit.MINUTES).toEpochMilli()),
            
            new Tweet("Bob Smith", "Working on an exciting new project with Java 21. The new features are incredible!", 
                     now.minus(15, ChronoUnit.MINUTES).toEpochMilli()),
            
            new Tweet("Charlie Brown", "Beautiful sunset today! Nature never fails to amaze me 🌅", 
                     now.minus(30, ChronoUnit.MINUTES).toEpochMilli()),
            
            new Tweet("Diana Prince", "Finally finished reading that book I started months ago. Great feeling of accomplishment!", 
                     now.minus(45, ChronoUnit.MINUTES).toEpochMilli()),
            
            new Tweet("Alice Johnson", "Learning Angular 18 and loving the new features. The developer experience keeps getting better!", 
                     now.minus(1, ChronoUnit.HOURS).toEpochMilli()),
            
            new Tweet("Eve Wilson", "Just deployed my first Spring Boot application to production. Nervous but excited! 🚀", 
                     now.minus(2, ChronoUnit.HOURS).toEpochMilli()),
            
            new Tweet("Frank Miller", "Docker makes development so much easier. Can't imagine working without containers now.", 
                     now.minus(3, ChronoUnit.HOURS).toEpochMilli()),
            
            new Tweet("Grace Hopper", "Debugging is like being the detective in a crime movie where you are also the murderer. 🕵️‍♀️", 
                     now.minus(4, ChronoUnit.HOURS).toEpochMilli()),
            
            new Tweet("Henry Ford", "PostgreSQL's performance improvements in the latest version are impressive!", 
                     now.minus(5, ChronoUnit.HOURS).toEpochMilli()),
            
            new Tweet("Bob Smith", "Weekend coding session complete! Built a REST API and learned a lot in the process.", 
                     now.minus(1, ChronoUnit.DAYS).toEpochMilli()),
            
            new Tweet("Alice Johnson", "The weather is perfect for a long walk. Sometimes you need to step away from the screen.", 
                     now.minus(1, ChronoUnit.DAYS).minus(2, ChronoUnit.HOURS).toEpochMilli()),
            
            new Tweet("Diana Prince", "Attended an amazing tech conference today. So many inspiring talks and networking opportunities!", 
                     now.minus(2, ChronoUnit.DAYS).toEpochMilli())
        );

        tweetRepository.saveAll(tweets);
        System.out.println("Database seeded with " + tweets.size() + " sample tweets.");
    }
}