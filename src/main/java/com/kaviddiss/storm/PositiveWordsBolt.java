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


public class PositiveWordsBolt extends BaseRichBolt{


	



	/**
	 * Filters for positive words in a tweet
	 * @author Benjamin Hardwick
	 * 
	 */
	
	private static final long serialVersionUID = 3057360032975896451L;
	
	private Set<String> POSITIVE_LIST = new HashSet<String>(Arrays.asList(new String[] {}));
	
	private OutputCollector collector;
	
	int positiveTweets;
	
	public void fileReader() {
		
		if(POSITIVE_LIST.isEmpty()) {
			try {
				File positiveWords = new File("positive-words.txt");
				Scanner positiveScanner = new Scanner(positiveWords);
				
				
				while(positiveScanner.hasNextLine()) {
					
					if(positiveScanner.hasNextLine()) {
						String positiveWord = positiveScanner.nextLine();
						POSITIVE_LIST.add(positiveWord);
					}
					
				}
				positiveScanner.close();
				
				
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
		String word = (String) input.getValueByField("word");
		if(POSITIVE_LIST.isEmpty()) {
			this.fileReader();
		}
		else {
			  //String lang = (String) input.getValueByField("lang");
			  
			  
			  
		        if (POSITIVE_LIST.contains(word)) {
		            positiveTweets +=1;
		            //System.out.println("Positive: " + positiveTweets);
		            //System.out.println(word);
		        
		        }

		}
		 collector.emit(new Values(positiveTweets, word));
    }

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		//declarer.declare(new Fields("lang", "word"));
		declarer.declare(new Fields("positiveTweets", "word"));
	}

}
