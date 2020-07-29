package com.sarlog.ojas;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class sarlogagg
{
	public static void main(String[] args) throws Exception
	{
		Configuration conf = new Configuration();
//		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
//		if (otherArgs.length != 2)
//		{
//			System.err.println("Usage: Process Sar Logs <in> <out>");
//			System.exit(2);
//		}
		Job job = new Job(conf, "Process Sar Logs");

		job.setJarByClass(sarlogagg.class);
		job.setMapperClass(sarlogmap.class);
		job.setReducerClass(sarlogreduce.class);
		
		job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FloatWritable.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job, new Path("/home/ojas/data_script/data_script/logs/data001.txt"));
		FileOutputFormat.setOutputPath(job, new Path("/home/ojas/output/sars-log-out03"));
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
