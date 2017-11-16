# twitter-ten
Latest 10 tweets for a user

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
