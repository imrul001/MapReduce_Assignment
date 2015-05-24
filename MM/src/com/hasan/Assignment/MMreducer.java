package com.hasan.Assignment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class MMreducer extends MapReduceBase implements Reducer<Text, Text, Text, IntWritable> {
	
	private static int n;
	
	public void configure(JobConf conf){
		n = Integer.parseInt(conf.get("n"));
	}

	@Override
	public void reduce(Text key, Iterator<Text> values,
			OutputCollector<Text, IntWritable> output, Reporter r)
			throws IOException {
		List<Matrix> Matrix_A = new ArrayList<Matrix>();
		List<Matrix> Matrix_B = new ArrayList<Matrix>();
		while(values.hasNext()){
		    Text temp = values.next();
		    String[] value = temp.toString().split(",");
		    if(value[0].equalsIgnoreCase("A")){
		    	Matrix MA = new Matrix();
		    	MA.setName("A");
		    	MA.setJvalue(value[1]);
		    	MA.setValue(value[2]);
		    	Matrix_A.add(MA);
		    }else{
		    	Matrix MB = new Matrix();
		    	MB.setName("B");
		    	MB.setJvalue(value[1]);
		    	MB.setValue(value[2]);
		    	Matrix_B.add(MB);
		    }
		}
		int result = 0;
		for(int i = 0 ; i < n; i++){
			int temp = 0;
			int mAvalue = 0;
			int mBvalue = 0;
			for(Matrix M: Matrix_A){
				if(M.getJvalue().equalsIgnoreCase(String.valueOf(i))){
					mAvalue=Integer.parseInt(M.getValue());
					break;
				}
			}
			for(Matrix M: Matrix_B){
				if(M.getJvalue().equalsIgnoreCase(String.valueOf(i))){
					mBvalue=Integer.parseInt(M.getValue());
					break;
				}
			}
			temp = mAvalue*mBvalue;
			result = result + temp;
		}
		output.collect(key, new IntWritable(result));
		
	}

}
