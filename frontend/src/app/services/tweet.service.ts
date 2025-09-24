import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Tweet, TweetRequest } from '../models/tweet.model';

@Injectable({
  providedIn: 'root'
})
export class TweetService {
  private apiUrl = 'http://localhost:8080/api/tweets';

  constructor(private http: HttpClient) {}

  /**
   * Get all tweets from the backend
   */
  getAllTweets(): Observable<Tweet[]> {
    return this.http.get<Tweet[]>(this.apiUrl).pipe(
      catchError(this.handleError)
    );
  }

  /**
   * Create a new tweet
   */
  createTweet(tweetRequest: TweetRequest): Observable<Tweet> {
    return this.http.post<Tweet>(this.apiUrl, tweetRequest).pipe(
      catchError(this.handleError)
    );
  }

  /**
   * Get tweet by ID
   */
  getTweetById(id: number): Observable<Tweet> {
    return this.http.get<Tweet>(`${this.apiUrl}/${id}`).pipe(
      catchError(this.handleError)
    );
  }

  /**
   * Update an existing tweet
   */
  updateTweet(id: number, tweetRequest: TweetRequest): Observable<Tweet> {
    return this.http.put<Tweet>(`${this.apiUrl}/${id}`, tweetRequest).pipe(
      catchError(this.handleError)
    );
  }

  /**
   * Delete a tweet
   */
  deleteTweet(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`).pipe(
      catchError(this.handleError)
    );
  }

  /**
   * Search tweets by author
   */
  getTweetsByAuthor(author: string): Observable<Tweet[]> {
    return this.http.get<Tweet[]>(`${this.apiUrl}/author/${author}`).pipe(
      catchError(this.handleError)
    );
  }

  /**
   * Search tweets by message content
   */
  searchTweetsByMessage(searchText: string): Observable<Tweet[]> {
    return this.http.get<Tweet[]>(`${this.apiUrl}/search?q=${encodeURIComponent(searchText)}`).pipe(
      catchError(this.handleError)
    );
  }

  /**
   * Handle HTTP errors
   */
  private handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage = 'An unknown error occurred!';
    
    if (error.error instanceof ErrorEvent) {
      // Client-side error
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // Server-side error
      if (error.status === 0) {
        errorMessage = 'Unable to connect to the server. Please check if the backend is running.';
      } else if (error.status === 400) {
        errorMessage = 'Invalid request. Please check your input.';
      } else if (error.status === 404) {
        errorMessage = 'Tweet not found.';
      } else if (error.status === 500) {
        errorMessage = 'Internal server error. Please try again later.';
      } else {
        errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
      }
    }
    
    console.error('TweetService Error:', error);
    return throwError(() => new Error(errorMessage));
  }
}