package com.hasan.Assignment;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class MatrixVectorMapper extends MapReduceBase implements Mapper
<LongWritable, Text, Text, IntWritable>{

	@Override
	public void map(LongWritable key, Text value,
			OutputCollector<Text, IntWritable> output, Reporter r)
			throws IOException {
		String line = value.toString();
		String[] A = new String[3];
		int length = line.length() - 5;
		String[] B = new String[length];
		A = getArrayA(line);
		B = getArrayB(line);
		int m = Integer.parseInt(A[2]);
		int n = Integer.parseInt(getBvalue(B, A[1]));
		output.collect(new Text(A[0]), new IntWritable(m*n));
	}
	
	public String[] getArrayA(String line){
		String[] A = new String[3];
		String[] l = line.split(",");
		A[0] = l[1];
		A[1] = l[2];
		A[2] = l[3];
		return A;
	}
	
	public String[] getArrayB(String line){
		String[] l = line.split(",");		
		int length = l.length;
		String[] B = new String[length-5];
		int k = 0;
		for(int i = 5; i < length; i++){
			B[k] = l[i];
			k++;
		}
		return B;
	}
	
	public String getBvalue(String[] B, String k){
		String r=null;
		for(int i=0; i< B.length; i=i+2){
			if(B[i].equalsIgnoreCase(k)){
				r=B[i+1];
				break;
			}
		}
		return r;
	}

}
