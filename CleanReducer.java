// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.FileWriter;
// import java.io.IOException;

// public class CleanReducer {
//     public static void reduce() throws Exception{
//         try{
//             BufferedReader reader = new BufferedReader(new FileReader("CleanMapper.csv"));
//             FileWriter output = new FileWriter("cleaned.csv");
//             String line = reader.readLine();
//             //Deaths and Death rates columns removed since age adjusted death rate available, 
//             //if statements serve to simplify the format of the leading cause, and to 
//             //standardize terms such as Female and F to simply F
//             while (line != null){
//                 String [] splitted = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
//                 //removes extra columns
//                 splitted[4]=splitted[6];
//                 splitted[5]="";
//                 splitted[6]="";
//                 //standardize leading cause
//                 splitted[1] = splitted[1].replaceAll("\\(.*\\)", "");
//                 if (splitted[1].charAt(0)!='\"'){
//                     splitted[1]="\"" + splitted[1] + "\"";
//                 }
//                 if (splitted[1].charAt(splitted[1].length()-2)==' '){
//                     splitted[1] = splitted[1].substring(0,splitted[1].length()-2) + "\"";
//                 }
//                 //standardizing terms
//                 //typo in "posioning" instead of "poisoning"
//                 if (splitted[1].equals("\"Accidents Except Drug Posioning\"")){
//                     splitted[1] = "\"Accidents Except Drug Poisoning\"";
//                 }
//                 if (splitted[2].equals("Female")){
//                     splitted[2] = "F";
//                 }
//                 else if (splitted[2].equals("Male")){
//                     splitted[2] = "M";
//                 }
//                 if (splitted[3].equals("Non-Hispanic White") || splitted[3].equals("White Non-Hispanic")){
//                     splitted[3] = "White";
//                 }
//                 else if (splitted[3].equals("Non-Hispanic Black") || splitted[3].equals("Black Non-Hispanic")){
//                     splitted[3] = "Black";
//                 }
//                 for (int i = 0;i<splitted.length-2; i++){
//                     //-3 since 2 terms removed (would be -1 if no columns removed)
//                     if (i==splitted.length-3){
//                         output.write(splitted[i]+"\n");
//                     }
//                     else {
//                         output.write(splitted[i]+",");
//                     }
//                 }
//                 line = reader.readLine();
//             }
//             reader.close();
//             output.close();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
        
//     }
// }

import java.io.IOException; 

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CleanReducer 
    extends Reducer<Text, IntWritable, Text, IntWritable> { 
    
  @Override 
  public void reduce(Text key, Iterable<IntWritable> values, Context context) 
       throws IOException, InterruptedException { 
        String line = key.toString();
        String [] splitted = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        //removes extra columns
        splitted[4]=splitted[6];
        splitted[5]="";
        splitted[6]="";
        //standardize leading cause
        splitted[1] = splitted[1].replaceAll("\\(.*\\)", "");
        if (splitted[1].charAt(0)!='\"'){
            splitted[1]="\"" + splitted[1] + "\"";
        }
        if (splitted[1].charAt(splitted[1].length()-2)==' '){
            splitted[1] = splitted[1].substring(0,splitted[1].length()-2) + "\"";
        }
        //standardizing terms
        //typo in "posioning" instead of "poisoning"
        if (splitted[1].equals("\"Accidents Except Drug Posioning\"")){
            splitted[1] = "\"Accidents Except Drug Poisoning\"";
        }
        if (splitted[2].equals("Female")){
            splitted[2] = "F";
        }
        else if (splitted[2].equals("Male")){
            splitted[2] = "M";
        }
        if (splitted[3].equals("Non-Hispanic White") || splitted[3].equals("White Non-Hispanic")){
            splitted[3] = "White";
        }
        else if (splitted[3].equals("Non-Hispanic Black") || splitted[3].equals("Black Non-Hispanic")){
            splitted[3] = "Black";
        }
        String result = "";
        for (int i = 0;i<splitted.length-2; i++){
            //-3 since 2 terms removed (would be -1 if no columns removed)
            if (i==splitted.length-3){
                result = result + splitted[i];
                break;
            }
            else {
                result = result + splitted[i]+",";
            }
        }
        context.write(new Text(result), new IntWritable(1));
  }
}