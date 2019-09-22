package moviecounter;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.util.ArrayList;
import org.apache.hadoop.io.DoubleWritable;

public class MovieReducer extends Reducer<Text, Text, Text, DoubleWritable> {
	DoubleWritable result = new DoubleWritable();

	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		ArrayList<String> list1 = new ArrayList<String>(), list2 = new ArrayList<String>();
		double numerator = 0.0;
		double denominator = 0.0;
		for (Text text : values) {
			String str = text.toString();
			String[] arr = text.toString().split(" ");
			String movieId = arr[0];
			if (!list1.contains(str)) {
				list1.add(str);
				numerator++;
			}

			if (!list2.contains(movieId)) {
				list2.add(movieId);
				denominator++;
			}

		}
		double output = numerator / denominator;
		output = (double) Math.round(output * 100) / 100;
		result.set(output);
		context.write(key, result);
	}
}