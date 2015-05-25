package com.hasan.Assignment;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;


public class SelectMapper extends MapReduceBase implements Mapper<LongWritable,Text,Text,Text > {
	
	private static int col;
	private static String val;
	
	public void configure(JobConf conf){
		col = Integer.parseInt(conf.get("col"));
		val = conf.get("val");
	}

	@Override
	public void map(LongWritable key, Text value, OutputCollector<Text, Text> output,
			Reporter r) throws IOException {
			String line = value.toString();
			String[] l = line.split(",");
		    if(l[col].equals(val)){
					output.collect(new Text(l[0]), new Text(l[1]+","+l[2]+","+l[3]+","+l[4]));
			}	    
	}

}
