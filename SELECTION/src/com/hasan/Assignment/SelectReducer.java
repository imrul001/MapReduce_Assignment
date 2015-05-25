package com.hasan.Assignment;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;


public class SelectReducer extends MapReduceBase implements Reducer<Text, Text, Text, Text> {

	@Override
	public void reduce(Text key, Iterator<Text> values,
			OutputCollector<Text, Text> output, Reporter r) throws IOException {
		while(values.hasNext()){
			Text t = values.next();
			output.collect(key, t);
		}
		
	}

}
