import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TweetService } from '../../services/tweet.service';
import { TweetRequest, Tweet } from '../../models/tweet.model';

@Component({
  selector: 'app-tweet-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './tweet-form.component.html',
  styleUrls: ['./tweet-form.component.css']
})
export class TweetFormComponent {
  @Output() tweetCreated = new EventEmitter<Tweet>();

  tweetRequest: TweetRequest = {
    author: '',
    message: ''
  };

  isSubmitting = false;
  errorMessage = '';

  constructor(private tweetService: TweetService) {}

  onSubmit(): void {
    if (this.isFormValid()) {
      this.isSubmitting = true;
      this.errorMessage = '';

      this.tweetService.createTweet(this.tweetRequest).subscribe({
        next: (tweet: Tweet) => {
          this.tweetCreated.emit(tweet);
          this.resetForm();
          this.isSubmitting = false;
        },
        error: (error) => {
          this.errorMessage = error.message;
          this.isSubmitting = false;
        }
      });
    }
  }

  private isFormValid(): boolean {
    return this.tweetRequest.author.trim().length > 0 && 
           this.tweetRequest.message.trim().length > 0 &&
           this.tweetRequest.author.length <= 50 &&
           this.tweetRequest.message.length <= 280;
  }

  private resetForm(): void {
    this.tweetRequest = {
      author: '',
      message: ''
    };
  }

  get authorError(): string {
    if (this.tweetRequest.author.length > 50) {
      return 'Author name cannot exceed 50 characters';
    }
    return '';
  }

  get messageError(): string {
    if (this.tweetRequest.message.length > 280) {
      return 'Message cannot exceed 280 characters';
    }
    return '';
  }

  get remainingCharacters(): number {
    return 280 - this.tweetRequest.message.length;
  }
}