// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.FileWriter;
// import java.io.IOException;

// public class CleanMapper {
//     public static void map() throws Exception{
//         try {
//             BufferedReader reader = new BufferedReader(new FileReader("New_York_City_Leading_Causes_of_Death.csv"));
//             FileWriter results = new FileWriter("CleanMapper.csv");
//             String line = reader.readLine();
//             while (line != null) {
//                 String [] splitted = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
//                 //lines of improper length are not passed to reducer as these lines did not contain death rates
//                 if (splitted.length!=7||splitted[6].equals(".")){
//                     line = reader.readLine();
//                     continue;
//                 }
//                 results.write(line+"\n");
//                 line = reader.readLine();
//             }
//             results.close();
//             reader.close();
//         }catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
// }

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CleanMapper
    extends Mapper <LongWritable, Text, Text, IntWritable> {
 
  @Override
  public void map (LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    
    String line = value.toString();
    String [] splitted = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"); 
    if (splitted.length==7 && !splitted[6].equals(".")){     
      context.write( new Text(line), new IntWritable(1)); 
    }
  }
} 
