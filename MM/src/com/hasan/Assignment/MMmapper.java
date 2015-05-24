package com.hasan.Assignment;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class MMmapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {
	
	private static int m;
	private static int p;
	
	public void configure(JobConf conf){
		m = Integer.parseInt(conf.get("m"));
		p = Integer.parseInt(conf.get("p"));
	}

	@Override
	public void map(LongWritable key, Text value,
			OutputCollector<Text, Text> output, Reporter r)
			throws IOException {
		String line = value.toString();
		String[] positionAndValue = line.split(",");
		if(positionAndValue[0].equalsIgnoreCase("A")){
			String i = positionAndValue[1];
			String j = positionAndValue[2];
			for(int k=0; k < p; k++){
				output.collect(new Text(i+","+String.valueOf(k)), new Text("A,"+j+","+positionAndValue[3]));
			}
		}else{
			String j = positionAndValue[1];
			String k = positionAndValue[2];
			for(int i=0; i < m; i++){
				output.collect(new Text(i+","+String.valueOf(k)), new Text("B,"+j+","+positionAndValue[3]));
			}
			
		}

	}

}
