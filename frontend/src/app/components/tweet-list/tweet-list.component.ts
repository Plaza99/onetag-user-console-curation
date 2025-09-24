import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Tweet } from '../../models/tweet.model';

@Component({
  selector: 'app-tweet-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './tweet-list.component.html',
  styleUrls: ['./tweet-list.component.css']
})
export class TweetListComponent {
  @Input() tweets: Tweet[] = [];
  @Input() loading = false;

  /**
   * Sort tweets according to requirements:
   * 1. Reverse chronological order (newest first)
   * 2. For tweets with the same date, sort alphabetically by author name (ascending)
   */
  get sortedTweets(): Tweet[] {
    return [...this.tweets].sort((a, b) => {
      // First, convert formatted date strings back to comparable format for sorting
      const dateA = this.parseFormattedDate(a.date);
      const dateB = this.parseFormattedDate(b.date);
      
      // Sort by date descending (newest first)
      if (dateA.getTime() !== dateB.getTime()) {
        return dateB.getTime() - dateA.getTime();
      }
      
      // If dates are the same, sort by author name ascending (alphabetical)
      return a.author.localeCompare(b.author);
    });
  }

  /**
   * TrackBy function 
   */
  trackByTweetId(index: number, tweet: Tweet): number {
    return tweet.id;
  }

  /**
   * Parse formatted date string back to Date object for sorting
   * Format: "HH:mm - dd/MM/yyyy GMT"
   */
  private parseFormattedDate(formattedDate: string): Date {
    try {
      // Extract the date and time parts
      // Format: "14:30 - 22/09/2025 GMT"
      const parts = formattedDate.split(' - ');
      if (parts.length !== 2) {
        return new Date();
      }
      
      const timePart = parts[0]; // "14:30"
      const datePart = parts[1].replace(' GMT', ''); // "22/09/2025"
      
      const [hours, minutes] = timePart.split(':').map(Number);
      const [day, month, year] = datePart.split('/').map(Number);
      
      // Create Date object (month is 0-indexed in JavaScript)
      return new Date(year, month - 1, day, hours, minutes);
    } catch (error) {
      console.error('Error parsing date:', formattedDate, error);
      return new Date();
    }
  }
}