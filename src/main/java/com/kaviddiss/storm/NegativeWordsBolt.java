package com.kaviddiss.storm;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import twitter4j.Status;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class NegativeWordsBolt extends BaseRichBolt{

	/**
	 * Filters out for negative words in a tweet
	 * 
	 * @author Benjamin Hardwick
	 * 
	 */
	private static final long serialVersionUID = 6069146554651714100L;
	
	private Set<String> NEGATIVE_LIST = new HashSet<String>(Arrays.asList(new String[] {}));
	
	
	private OutputCollector collector;
	
	private int negativeTweets;
	
	
	public void fileReader() {
		
		if(NEGATIVE_LIST.isEmpty()) {
			try {
				File negativeWords = new File("negative-words.txt");
				Scanner negativeScanner = new Scanner(negativeWords);
				
				while(negativeScanner.hasNextLine()) {
					
					if(negativeScanner.hasNextLine()) {
						String negativeWord = negativeScanner.nextLine();
						NEGATIVE_LIST.add(negativeWord);
					}

				}
				
				negativeScanner.close();
				
			}
			catch(FileNotFoundException e) {
				System.out.println("Error occured: ");
				e.printStackTrace();
			}
			
		}
		
	}
	
	@Override
	public void prepare(Map map, TopologyContext context, OutputCollector collector) {
		// TODO Auto-generated method stub
		this.collector = collector;
		
	}

	@Override
	public void execute(Tuple input) {
		if(NEGATIVE_LIST.isEmpty()) {
			this.fileReader();
		}
		else { 
			
			  //String lang = (String) input.getValueByField("lang");
			  String word = (String) input.getValueByField("word");
			  int positiveTweets = (int) input.getValueByField("positiveTweets");
			  //int negativeTweets = (int) input.getValueByField("negativeTweets");
			  //collector.emit(new Values(lang, word));

		      if(NEGATIVE_LIST.contains(word)) {
			   negativeTweets +=1;
			   
			  // System.out.println("Negative: " + negativeTweets);
		        	
		        }
		      
		      collector.emit(new Values(negativeTweets, positiveTweets));
		       

		}
	  
    }

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		declarer.declare(new Fields("positiveTweets", "negativeTweets"));
	}

}
