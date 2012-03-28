import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WikipediaCharacterCounterMapper extends
		Mapper<LongWritable, Text, Text, Text> {

	private static final Pattern OPEN_TEXT_TAG_PATTERN = Pattern
			.compile("<text xml:space=\"preserve\">");
	private static final Pattern CLOSE_TEXT_TAG_PATTERN = Pattern
			.compile("</text>");

	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		String document = value.toString();
		document = StringEscapeUtils
				.unescapeHtml(WikipediaCharacterCounterMapper.CLOSE_TEXT_TAG_PATTERN
						.matcher(
								WikipediaCharacterCounterMapper.OPEN_TEXT_TAG_PATTERN
										.matcher(document).replaceFirst(""))
						.replaceAll(""));

		long sum = 0;
		String s;
		char c;
		Long tmp;
		HashMap<String, Long> hm = new HashMap<String, Long>();
		HashMap<String, Double> finalHm = new HashMap<String, Double>();
		CharsetEncoder asciiEncoder = Charset.forName("US-ASCII").newEncoder();

		for (int i = 0; i < document.length(); ++i) {

			s = document.substring(i, i + 1).toLowerCase();
			c = s.charAt(0);
			if (asciiEncoder.canEncode(c) && Character.isLetter(c)) {
				tmp = hm.get(s);
				if (tmp == null) {
					tmp = 0L;
				}
				hm.put(s, tmp + 1);
				++sum;
			}

		}
		if (hm.size() == 26 && sum > 1000) {
			calculateProb(hm, finalHm, sum);
			context.write(new Text(), mergeString(finalHm));
		}
		hm.clear();
		finalHm.clear();
		s = "";
		tmp = 0L;
		c = ' ';
	}

	private void calculateProb(HashMap<String, Long> hm,
			HashMap<String, Double> finalHm, long sum) {
		Set set = hm.entrySet();
		Iterator i = set.iterator();

		while (i.hasNext()) {
			Map.Entry me = (Map.Entry) i.next();
			finalHm.put((String) me.getKey(), (Long) me.getValue()
					/ (sum * 1.0));
		}
	}

	private Text mergeString(HashMap<String, Double> hm) {
		Set set = hm.entrySet();
		StringBuilder sb = new StringBuilder();
		Iterator i = set.iterator();

		while (i.hasNext()) {
			Map.Entry me = (Map.Entry) i.next();
			sb.append(me.getKey() + "," + me.getValue()
					+ " ");
		}
		return new Text(sb.toString());
	}

}
