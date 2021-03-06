
Description
=========================
This is a twitter data analysis, using word count for the setup of the apache-storm topology, I have converted this to analyse the responses to the ongoing COVID-19 pandemic,
this program will collect data on how people are feeling based on their tweets towards the vaccination and ongoing pandemic. This analyses and gives a score based on
both positive and negative responses.


DONE
=========================
* Positive Tweets filters
* Getting hashtags
* Negative Tweets Filtering
* Hashtag Filtering
* Score Generation 
* Finalize score generation, add negative/positive tweets to hashmap and find out if the entire tweet has a positive/negative demeanor.
* Get final results after certain amount of time (i.e 10 minutes of analysis.)


TODO
=========================
* Fix NLP version and update to own repo

References
========================
* Providing of both negative and positive words.


   * Minqing Hu and Bing Liu. "Mining and Summarizing Customer Reviews." 
       Proceedings of the ACM SIGKDD International Conference on Knowledge 
       Discovery and Data Mining (KDD-2004), Aug 22-25, 2004, Seattle, 
       Washington, USA, 
   * Bing Liu, Minqing Hu and Junsheng Cheng. "Opinion Observer: Analyzing 
       and Comparing Opinions on the Web." Proceedings of the 14th 
       International World Wide Web conference (WWW-2005), May 10-14, 
       2005, Chiba, Japan.

storm-twitter-word-count
========================


Sample project based on https://github.com/abh1nav/dvto1 demonstrating real-time computation Storm framework (https://github.com/nathanmarz/storm).

See my blog post for mor details on this project: http://kaviddiss.com/2013/05/17/how-to-get-started-with-storm-framework-in-5-minutes/.

The code subscribes to Twitter's Sample feed, keeps stats on words occuring in tweets and logs top list with of words with most count in every 10 seconds.

This project contains a simple storm topology that connects to the sample stream of the Twitter Streaming API and keeps stats on words occuring in tweets and prints top list of words with highest count in every 10 seconds.

To get started:
* Clone this repo
* Import as existing Maven project in Eclipse
* Run Topology.java with your twitter credentials as VM args (see http://twitter4j.org/en/configuration.html#systempropertyconfiguration)

You'll need to have valid Twitter OAuth credentials to get the sample working.
For the exact steps on how to do that, visit https://dev.twitter.com/discussions/631.
