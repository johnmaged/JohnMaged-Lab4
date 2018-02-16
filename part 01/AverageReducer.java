package solution;  
  
import java.io.IOException;  
  
import org.apache.hadoop.io.DoubleWritable;  
import org.apache.hadoop.io.IntWritable;  
import org.apache.hadoop.io.Text;  
import org.apache.hadoop.mapreduce.Reducer;  
//import org.apache.hadoop.mapreduce.Reducer.Context;
  
/** 
* To define a reduce function for your MapReduce job, subclass 
* the Reducer class and override the reduce method. 
* The class definition requires four parameters:  
* @param The data type of the input key - Text 
* @param The data type of the input value - IntWritable 
* @param The data type of the output key - Text 
* @param The data type of the output value - DoubleWritable 
*/  
public class AverageReducer extends  
    Reducer<Text, IntWritable, Text, DoubleWritable> {  
  
  /** 
   * The reduce method runs once for each key received from 
   * the shuffle and sort phase of the MapReduce framework. 
   * The method receives: 
   * @param A key of type Text 
   * @param A set of values of type IntWritable 
   * @param A Context object 
   */  
  @Override  
//  public void reduce(Text key, Iterable values, Context context)  
//      throws IOException, InterruptedException {  
 
  public void reduce(Text key, Iterable<IntWritable> values, Context context)
	  throws IOException, InterruptedException {
	  
	  
      Double sumOfLengths = 0.0;   // where we sum the total length of diff. words starts with specific letter.
      Double wordCounts = 0.0;   // how many words starts with that letter.
      for (IntWritable wl : values) 
      {  
    	  sumOfLengths += wl.get();  
          wordCounts++;  
      }  
      context.write(key, new DoubleWritable(sumOfLengths / wordCounts)); 
  }  
} 