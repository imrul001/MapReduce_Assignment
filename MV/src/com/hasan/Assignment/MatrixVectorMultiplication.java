package com.hasan.Assignment;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/*This is the driver class for Matrix Vector Multiplication*/
/**arguments = input directory, output directory, L(rows of A), M(columns of A)**/
public class MatrixVectorMultiplication extends Configured implements Tool {
	
	@Override
	public int run(String[] args) throws Exception {
		if(args.length < 2){
			System.out.println("Please give input and output directories properly");
			return 1;
		}
		JobConf conf = new JobConf(MatrixVectorMultiplication.class);
		FileInputFormat.setInputPaths(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));
		/*Matrix A is L-by-M V is M-by-N*/
//		conf.set("L", "2");
//		conf.set("M", "2");	
//		conf.set("N", "1");
		conf.setMapperClass(MatrixVectorMapper.class);
		conf.setReducerClass(MatrixVectorReducer.class);
		
		conf.setMapOutputKeyClass(Text.class);
		conf.setMapOutputValueClass(IntWritable.class);
		
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);
		
		JobClient.runJob(conf);
		return 0;
	}
	
	public static void main(String args[]) throws Exception{
		int exitCode = ToolRunner.run(new MatrixVectorMultiplication(), args);
		System.exit(exitCode);
	}
	
	
}
