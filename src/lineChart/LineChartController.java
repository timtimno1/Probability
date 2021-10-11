package lineChart;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class LineChartController implements Initializable
{
	@FXML
	Button button;
	@FXML
	NumberAxis xAxis;
	@FXML
	NumberAxis YAxis;
	@FXML
	private LineChart<Number, Number> chart1;

	@Override
	public void initialize(URL location, ResourceBundle resou)
	{
		xAxis.setAutoRanging(false);
		xAxis.setLowerBound(XYData.xLowerBound);
		xAxis.setUpperBound(XYData.xUpperBound);
		xAxis.setTickUnit(1);
		xAxis.setTickLabelFill(Color.WHITE);
		xAxis.setLabel("X");

		YAxis.setAutoRanging(false);
		YAxis.setLowerBound(XYData.yLowerBound);
		YAxis.setUpperBound(XYData.yUpperBound);
		YAxis.setTickUnit(0.1);
		YAxis.setTickLabelFill(Color.WHITE);
		YAxis.setLabel("P");

		chart1.getData().clear();
		chart1.setCreateSymbols(false);
		chart1.setAnimated(false);
		if (XYData.CDF)
		{
			chart1.getData().add(XYData.data);
			chart1.getData().add(XYData.data1);
		}
		else
			chart1.getData().add(XYData.data);
		chart1.getCreateSymbols();
	}

	public void clear()
	{
		Stage a = (Stage) button.getScene().getWindow();
		a.close();
		a = null;
		chart1.getData().clear();
	}

	public static void start(Stage primaryStage, Parent root)
	{

		primaryStage.setTitle("LineChart");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();

	}
}
