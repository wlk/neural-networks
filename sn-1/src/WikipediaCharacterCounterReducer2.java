import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WikipediaCharacterCounterReducer2 extends
		Reducer<Text, IntWritable, Text, IntWritable> {

	public void reduce(Text key, Iterator<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		int sum = 0;
		while (values.hasNext()) {
			sum += values.next().get();
		}
		context.write(key, new IntWritable(sum));
	}
}
