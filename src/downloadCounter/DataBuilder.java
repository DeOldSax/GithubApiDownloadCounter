package downloadCounter;

import java.util.ArrayList;
import java.util.List;

public class DataBuilder {
	private List<String> versions = null;
	private List<String> dates = null;
	private List<List<Integer>> series = null;

	public DataBuilder(List<List<String>> data) {
		dates = new ArrayList<String>();
		series = new ArrayList<List<Integer>>();
		if (data.size() != 0) {
			data.get(0).remove(0);
			versions = data.get(0);
			data.remove(0);

			for (int i = 0; i < versions.size(); i++) {
				series.add(new ArrayList<Integer>());
			}

			for (int k = 0; k < data.size(); k++) {
				dates.add(data.get(k).get(0));
				data.get(k).remove(0);
			}

			for (int j = 0; j < dates.size(); j++) {
				for (int i = 0; i < series.size(); i++) {
					series.get(i).add(Integer.valueOf(data.get(j).get(i)));
				}
			}
		}
	}

	public List<String> getVersions() {
		return versions;
	}

	public List<String> getDates() {
		return dates;
	}

	public List<List<Integer>> getSeries() {
		return series;
	}
}
