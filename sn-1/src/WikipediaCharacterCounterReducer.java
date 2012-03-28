import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WikipediaCharacterCounterReducer extends
		Reducer<Text, LongWritable, Text, Text> {

	public void reduce(Text key, Iterator<LongWritable> values, Context context)
			throws IOException, InterruptedException {
		int sum = 0;
		while (values.hasNext()) {
			sum += values.next().get();
		}
		context.write(key, new Text("asdf"));
	}
}
