package moviecounter;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MovieMapper extends Mapper<Object, Text, Text, Text> {
	private Text category = new Text(), comp = new Text();

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		String[] stringArray = value.toString().split(",");
		if (stringArray[0].equals("video_id")) {
			return;
		}
		String categoryName = stringArray[3];
		String movieId = stringArray[0];
		String country = stringArray[11];
		category.set(categoryName);
		String compValue = movieId + " " + country;
		comp.set(compValue);
		context.write(category, comp);
	}
}