package downloadCounter;

import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;

public class DownloadChart extends Application {
	private static DataBuilder dataBuilder;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("DownloadChart");

		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();

		LineChart<String, Number> chart = new LineChart<String, Number>(xAxis, yAxis);
		chart.setTitle("Downloads");

		final List<List<Integer>> series = dataBuilder.getSeries();

		for (List<Integer> list : series) {
			Series<String, Number> s = new XYChart.Series<String, Number>();
			s.setName(dataBuilder.getVersions().get(series.indexOf(list)));
			int i = 0;
			for (Integer integer : list) {
				s.getData().add(new XYChart.Data<String, Number>(dataBuilder.getDates().get(i), integer));
				i++;
			}
			chart.getData().add(s);
		}

		Scene scene = new Scene(chart, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		dataBuilder = new DataBuilder(new CsvReader().read("downloads.csv", "|"));
		launch(args);
	}
}
