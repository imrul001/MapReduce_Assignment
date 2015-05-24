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

public class MM extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {
		if(args.length <2){
			System.out.println("Invalid Directory.Check the directory");
			return -1;
		}
		JobConf conf = new JobConf(MM.class);
		FileInputFormat.setInputPaths(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));
		
		/**Matrix dimension Parameters**/
		conf.set("m", "2");
		conf.set("n", "2");
		conf.set("p", "2");
		
		conf.setMapperClass(MMmapper.class);
		conf.setReducerClass(MMreducer.class);
		
		conf.setMapOutputKeyClass(Text.class);
		conf.setMapOutputValueClass(Text.class);
		
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);
		
		JobClient.runJob(conf);
		
		return 0;
	}
	
	public static void main(String[] args) throws Exception{
		int exitCode = ToolRunner.run(new MM(), args);
		System.exit(exitCode);
	}
	

}
