
// public class Clean {
//     public static void main(String[] args) throws Exception{
//         // //Mapper Component
//         CleanMapper.map();
//         //Now the data is inputted into reducer, which adds all '\n' characters
//         CleanReducer.reduce();
//     }
// }


import org.apache.hadoop.fs.Path; 
import org.apache.hadoop.io.IntWritable; 
import org.apache.hadoop.io.Text; 
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat ;

public class Clean { 

    public static void main(String[] args) throws Exception { 
        if (args.length != 2) { 
            System.err.println("Usage: CountRecs");  
            System.exit(-1); 
        } 
        Configuration conf = new Configuration();
        conf.set("mapred.textoutputformat.separator", ",");
        Job job = Job.getInstance(conf);
        job.setNumReduceTasks(1);
        job.setJarByClass(Clean.class); 
        job.setJobName("CleanData"); 
        FileInputFormat.addInputPath(job, new Path(args[0])); 
        FileOutputFormat.setOutputPath(job, new Path(args[1])); 
        job.setMapperClass(CleanMapper.class); 
        job.setReducerClass(CleanReducer.class); 
        job.setOutputKeyClass(Text.class); 
        job.setOutputValueClass(IntWritable.class); 
        System.exit(job.waitForCompletion( true ) ? 0 : 1);
    }
}