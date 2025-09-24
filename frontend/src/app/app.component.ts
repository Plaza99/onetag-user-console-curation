import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TweetFormComponent } from './components/tweet-form/tweet-form.component';
import { TweetListComponent } from './components/tweet-list/tweet-list.component';
import { TweetService } from './services/tweet.service';
import { Tweet } from './models/tweet.model';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, TweetFormComponent, TweetListComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Twitter-like App';
  tweets: Tweet[] = [];
  loading = false;
  errorMessage = '';

  constructor(private tweetService: TweetService) {}

  ngOnInit(): void {
    this.loadTweets();
  }

  /**
   * Load all tweets from the backend
   */
  loadTweets(): void {
    this.loading = true;
    this.errorMessage = '';

    this.tweetService.getAllTweets().subscribe({
      next: (tweets: Tweet[]) => {
        this.tweets = tweets;
        this.loading = false;
      },
      error: (error) => {
        this.errorMessage = error.message;
        this.loading = false;
        console.error('Error loading tweets:', error);
      }
    });
  }

  /**
   * Handle new tweet creation
   */
  onTweetCreated(newTweet: Tweet): void {
    // Add the new tweet to the beginning of the array
    // The sorting will be handled by the TweetListComponent
    this.tweets = [newTweet, ...this.tweets];
  }

  /**
   * Retry loading tweets
   */
  retryLoadTweets(): void {
    this.loadTweets();
  }
}