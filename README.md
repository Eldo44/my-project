# TwitterStreamingAPI

This application reads tweets based on a keyword, example "Trump". It performs this task  for 30 seconds or 50 tweets whichever comes first, which is configured to run every hour.
  It follows oauth 1.0 Authentication and therefore you need to look in the console and login to that link with your twitter credentials and authorize the app and key in the PIN code you have received.
  Once Authorized, it will read the live tweets and store both the Tweet and the User information in the h2 in memory database.
  
# TweetController
  This Rest API is used to retrieve the last 10 messages to the client.
