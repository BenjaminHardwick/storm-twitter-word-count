package com.kaviddiss.storm;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;

public class ScoreCounterBolt extends BaseRichBolt{

	/**
	 * 
	 * @author Benjamin Hardwick
	 * 
	 */
	private static final long serialVersionUID = -7372245130377377659L;

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(Tuple input) {
		// TODO Auto-generated method stub
		int positiveTweets = (int) input.getValueByField("positiveTweets");
		int negativeTweets = (int) input.getValueByField("negativeTweets");
		int finalAnswer;

		System.out.println("POSITIVE: " + positiveTweets + " >>><<<<  NEGATIVE: " + negativeTweets);
		// finding percentage of each being either negative or positive

		finalAnswer = positiveTweets + negativeTweets;
		
		// Positive
			if(positiveTweets >= 1 || negativeTweets >= 1) {	
				
				if(positiveTweets == negativeTweets) {
					System.out.println("There is a 50/50 split between both negative and positive answers");
				}
				
				
				if(positiveTweets >= negativeTweets) {
					finalAnswer = positiveTweets * (100/finalAnswer);
					System.out.println("The majority of responses are Positive! Precisely: " + finalAnswer + "%");
				}
					// Negative
	
				if(positiveTweets <= negativeTweets) {
					finalAnswer = negativeTweets * (100/finalAnswer);
					System.out.println("The majority of responses are Negative! Precisely: " + finalAnswer + "%");
				}
			
		
	
		
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		declarer.declare(new Fields("negativeTweets", "positiveTweets"));
	}
}
