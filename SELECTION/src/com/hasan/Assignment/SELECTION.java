package com.hasan.Assignment;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class SELECTION extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {
		if(args.length < 2){
			System.out.println("Invalid Directory");
			return -1;
		}
		
		JobConf conf = new JobConf(SELECTION.class);
		FileInputFormat.setInputPaths(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));
//		Input SELECT * from table WHERE column=3, value="T"
		conf.set("col", "3");
		conf.set("val", "T");
		
		conf.setMapperClass(SelectMapper.class);
		conf.setReducerClass(SelectReducer.class);
		
		conf.setMapOutputKeyClass(Text.class);
		conf.setMapOutputValueClass(Text.class);
		
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(Text.class);
		
		JobClient.runJob(conf);
		
		return 0;
	}
	
	public static void main(String args[]) throws Exception{
		int exitCode = ToolRunner.run(new SELECTION(), args);
		System.exit(exitCode);
	}

}
//1,A,M,T,Q
//2,B,P,I,Z
//3,X,N,T,P