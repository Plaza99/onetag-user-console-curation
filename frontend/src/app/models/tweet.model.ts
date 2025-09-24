export interface Tweet {
  id: number;
  author: string;
  message: string;
  date: string; // Formatted date string from backend
}

export interface TweetRequest {
  author: string;
  message: string;
}