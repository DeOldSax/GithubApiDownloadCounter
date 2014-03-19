package downloadCounter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A Reader for .csv files
 * 
 * @author David Englert
 * 
 */
public class CsvReader {
	/**
	 * @param path
	 * @param delimiter
	 * @return the rows of the csv file
	 */
	public List<List<String>> read(String path, String delimiter) {
		BufferedReader reader = null;
		String row = null;
		List<List<String>> rows = new ArrayList<List<String>>();

		try {
			reader = new BufferedReader(new FileReader(path));
			while ((row = reader.readLine()) != null) {
				rows.add(new ArrayList<String>(Arrays.asList(row.split("\\" + delimiter))));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return rows;
	}
}
