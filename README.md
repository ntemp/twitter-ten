# twitter-ten
Latest 10 tweets for a user

## Running Locally
The backend binds on 0.0.0.0:8080 and can be hit at http://localhost:8080/twitterten-api/tweets
The following environment variables are required:
```sh
TWITTER_CONSUMER_KEY=
TWITTER_CONSUMER_SECRET=
TWITTER_ACCESS_TOKEN=
TWITTER_ACCESS_TOKEN_SECRET=
```
The backend can be started with:
```sh
cd server
mvn exec:java
```

## What's Done
* Use Twitter5j to query twitter
* Return results in JSON via an endpoint
* Display tweets in client frontend
* Filter tweets

## To Do
* Front end tests
* Front end styling
* Cleanup server Dockefile
  * Seems to pull down packages on run
* Backend end to end tests
  * Investigate possibilty of mocking a twitter response
* Investigate truncated Twitter text
  * Tweets with URLs are truncated due to exceeding 140 chars
* Improve tweet filtering logic
  * Currently blows away all tweets and rerenders on keyup
  * Ideal solution would be to toggle a "hidden" class
* Use configuration to extract some hardcoded values
  * The backend URL in the frontend
  * The host and port for the backend
