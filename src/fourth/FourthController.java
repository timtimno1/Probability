package fourth;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lineChart.LineChartController;
import lineChart.XYData;
import sample.Main;
import java.net.URL;
import java.util.ResourceBundle;

public class FourthController implements Initializable
{
	@FXML
	private Button start;
	@FXML
	private Button back;
	@FXML
	private TextField resProbability;
	@FXML
	private TextField resSD;
	@FXML
	private TextField resExpectation;

	public void SetIn(ActionEvent event)
	{
		double x = 0;
		double resProbability = (Math.pow(Math.E, -0.5 * 3) * Math.pow(0.5 * 3, x));
		this.resProbability.setText(String.format("%5.2f%%", resProbability));
		resSD.setText("1.5");
		resExpectation.setText(String.format("%5.2f", Math.pow(1.5, 0.5)));
		XYData.data.getData().clear();
		XYData.data1.getData().clear();

		double temp = 1;
		for (double i = 0; i <= 20; i += 1)
		{
			if (i != 0) temp *= i;
			XYData.data.getData().add(new XYChart.Data<Number, Number>(i, (Math.pow(Math.E, -0.5 * 3) * Math.pow(0.5 * 3, i)) / temp));
		}
		XYData.data.setName("PDF");
		XYData.xLowerBound = 0;
		XYData.xUpperBound = 20;
		XYData.yLowerBound = 0;
		XYData.yUpperBound = 0.4;
		XYData.CDF = false;
		try
		{
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("lineChart/LineChart.fxml"));
			new LineChartController().start(new Stage(), root);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void back(ActionEvent event)
	{
		Stage a = (Stage) back.getScene().getWindow();
		a.close();
		a = null;

		try
		{
			new Main().start(new Stage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void start(Stage primaryStage, Parent root) throws Exception
	{
		primaryStage.setTitle("第四題");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{

	}
}
