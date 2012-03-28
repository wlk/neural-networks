import java.io.IOException;

import org.apache.commons.cli2.CommandLine;
import org.apache.commons.cli2.Group;
import org.apache.commons.cli2.Option;
import org.apache.commons.cli2.OptionException;
import org.apache.commons.cli2.builder.ArgumentBuilder;
import org.apache.commons.cli2.builder.DefaultOptionBuilder;
import org.apache.commons.cli2.builder.GroupBuilder;
import org.apache.commons.cli2.commandline.Parser;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.mahout.classifier.bayes.XmlInputFormat;
import org.apache.mahout.common.CommandLineUtil;
import org.apache.mahout.common.HadoopUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WikipediaCharacterCounterDriver {

	private static final Logger log = LoggerFactory
			.getLogger(WikipediaCharacterCounterDriver.class);

	private WikipediaCharacterCounterDriver() {
	}
	
	public static void main(String[] args) throws IOException,
			InterruptedException {
		DefaultOptionBuilder obuilder = new DefaultOptionBuilder();
		ArgumentBuilder abuilder = new ArgumentBuilder();
		GroupBuilder gbuilder = new GroupBuilder();

		Option dirInputPathOpt = obuilder
				.withLongName("input")
				.withRequired(true)
				.withArgument(
						abuilder.withName("input").withMinimum(1)
								.withMaximum(1).create())
				.withDescription("The input directory path").withShortName("i")
				.create();

		Option dirOutputPathOpt = obuilder
				.withLongName("output")
				.withRequired(true)
				.withArgument(
						abuilder.withName("output").withMinimum(1)
								.withMaximum(1).create())
				.withDescription("The output directory Path")
				.withShortName("o").create();

		Option helpOpt = obuilder.withLongName("help")
				.withDescription("Print out help").withShortName("h").create();

		Group group = gbuilder.withName("Options").withOption(dirInputPathOpt)
				.withOption(dirOutputPathOpt).withOption(helpOpt).create();

		Parser parser = new Parser();
		parser.setGroup(group);
		try {
			CommandLine cmdLine = parser.parse(args);
			if (cmdLine.hasOption(helpOpt)) {
				CommandLineUtil.printHelp(group);
				return;
			}

			String inputPath = (String) cmdLine.getValue(dirInputPathOpt);
			String outputPath = (String) cmdLine.getValue(dirOutputPathOpt);

			runJob(inputPath, outputPath);
		} catch (OptionException e) {
			log.error("Exception", e);
			CommandLineUtil.printHelp(group);
		} catch (ClassNotFoundException e) {
			log.error("Exception", e);
			CommandLineUtil.printHelp(group);
		}
	}
	
	
	public static void runJob(String input, String output) throws IOException,
			InterruptedException, ClassNotFoundException {
		Configuration conf = new Configuration();
		conf.set("key.value.separator.in.input.line", " ");
		conf.set("xmlinput.start", "<text xml:space=\"preserve\">");
		conf.set("xmlinput.end", "</text>");
		conf.set(
				"io.serializations",
				"org.apache.hadoop.io.serializer.JavaSerialization,"
						+ "org.apache.hadoop.io.serializer.WritableSerialization");

		Job job = new Job(conf);
		job.setJarByClass(WikipediaCharacterCounterDriver.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
		job.setMapperClass(WikipediaCharacterCounterMapper.class);
		job.setNumReduceTasks(0);
		job.setInputFormatClass(XmlInputFormat.class);
		//job.setCombinerClass(WikipediaCharacterCounterReducer.class);
		//job.setReducerClass(WikipediaCharacterCounterReducer.class);
		//job.setOutputFormatClass(WikipediaCharacterCounterOutputFormat.class);

		FileInputFormat.setInputPaths(job, new Path(input));
		Path outPath = new Path(output);
		FileOutputFormat.setOutputPath(job, outPath);
		HadoopUtil.overwriteOutput(outPath);

		job.waitForCompletion(true);
	}
}
