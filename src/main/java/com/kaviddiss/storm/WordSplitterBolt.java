package com.kaviddiss.storm;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import twitter4j.HashtagEntity;
import twitter4j.Status;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Receives tweets and emits its words over a certain length.
 */
public class WordSplitterBolt extends BaseRichBolt {
	
	private static final long serialVersionUID = 5151173513759399636L;

	private final int minWordLength;
	private int totalTweets;
	
    private OutputCollector collector;

    public WordSplitterBolt(int minWordLength) {
        this.minWordLength = minWordLength;
    }

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector collector) {
        this.collector = collector;
    }

    @Override
    public void execute(Tuple input) {
    	//List<String> mandatoryTags = Arrays.asList("covid", "covid19", "coronavirus", "covid19vaccine", "covidvaccine");
        Status tweet = (Status) input.getValueByField("tweet");
        String lang = tweet.getLang();
        String text = tweet.getText().replaceAll("\\p{Punct}", " ").replaceAll("\\r|\\n", "").toLowerCase();
        String[] words = text.split(" ");
       
       // List<HashtagEntity> hashtags = Arrays.asList(tweet.getHashtagEntities());
       // StringBuilder hashtag = new StringBuilder();

        
        if("en".equals(lang)) {
        	totalTweets+=1;
        	System.out.println("Total Tweets: " + totalTweets);
        	for (String word : words) {
                if (word.length() >= minWordLength) {
                	//System.out.println(word);
                	
                    collector.emit(new Values(lang, word));
                }
            	
            }
        	//System.out.println(hashtags);

        	/*

	        if(hashtags != null && hashtags.size() > 0)
	        {
	        	int totalTweets = 0;
	        	totalTweets+=1;
	        	System.out.println("Total Tweets: " + totalTweets);
	        	for(HashtagEntity tag: hashtags) {
	        		if(mandatoryTags.contains(tag.getText().toLowerCase())) {
	        			//System.out.println("Hashtags Entity: ** " + tag.getText().toLowerCase());
	        			//System.out.println(tweet.getUser().getName() + " | " + text);
	        	        

	        		}
	        	}
	        }*/

    	}
        
        	
        /*
	        for(int i = 0; i < hashtagEntities.length; i++) {
	        	if(hashtagEntities[i].getText().toLowerCase() == "coronavirus" || hashtagEntities[i].getText().toLowerCase() == "covid19" || hashtagEntities[i].getText().toLowerCase() == "covid" || hashtagEntities[i].getText().toLowerCase() == "Covid19vaccine") {
	        		System.out.println("hashtag entities: **** " + hashtagEntities[i].getText());

	        	}
	        }
	        */
        }
        //System.out.println(hashtag);
       

    	
        
    

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("lang", "word"));
    }
}
