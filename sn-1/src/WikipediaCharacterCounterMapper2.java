import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WikipediaCharacterCounterMapper2 extends
		Mapper<LongWritable, Text, Text, LongWritable> {
	
	//private Text character = new Text();
	//private final static IntWritable one = new IntWritable(1);

	protected void map(LongWritable key, LongWritable value, Context context)
			throws IOException, InterruptedException {
		//String text = value.toString();
		//String[] tokens = text.split(" "); 
		
			//context.write(new Text(tokens[0]), new IntWritable(Integer.parseInt(tokens[1])));
	}

}
