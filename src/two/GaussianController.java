package two;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lineChart.LineChartController;
import lineChart.XYData;
import math.Function;
import math.Integration;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;

public class GaussianController implements Initializable
{
	@FXML
	private Button back;
	@FXML
	private TextField avg;
	@FXML
	private TextField SD;
	@FXML
	private TextField expectations;
	@FXML
	private TextField var;

	public void setIn(ActionEvent event)
	{
		double avg = Double.parseDouble(this.avg.getText());
		double SD = Double.parseDouble(this.SD.getText());
		XYData.data.getData().clear();
		XYData.data1.getData().clear();

		expectations.setText(this.avg.getText());
		var.setText(String.format("%4.2f", Math.pow(SD, 2)));


		for (double i = avg - (3 * SD); i <= avg + (3 * SD); i += 0.01)
		{
			XYData.data.getData().add(new XYChart.Data<Number, Number>(i, f(i)));
			XYData.data1.getData().add(new XYChart.Data<Number, Number>(i, stdCDF(i)));
		}

		XYData.xLowerBound = avg - 5 * SD;
		XYData.xUpperBound = avg + 5 * SD;
		XYData.yLowerBound = 0;
		XYData.yUpperBound = f(avg) < 0.99 ? 1 : f(avg);
		XYData.data.setName("PDF");
		XYData.data1.setName("CDF");
		XYData.CDF = true;

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
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("two/Two.fxml"));
			TwoController.start(new Stage(), root);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{

	}

	public static void start(Stage primaryStage, Parent root)
	{
		primaryStage.setTitle("第二題高斯隨機變數");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	private double f(double x)
	{
		double avg = Double.parseDouble(this.avg.getText());
		double SD = Double.parseDouble(this.SD.getText());

		return Math.pow(Math.E, -0.5 * Math.pow((x - avg) / SD, 2)) / (Math.pow(2 * Math.PI, 0.5) * SD);
	}

	public double stdCDF(double realUpper)
	{
		Integration integration = new Integration();
		double avg = Double.parseDouble(this.avg.getText());
		double SD = Double.parseDouble(this.SD.getText());
		double upper = 1.0;
		double lower = 0.0;
		int n = 200; // splited into 200 subintervals.
		// double realUpper = 0.03;

        /*if(realUpper >= 5.0) {
            return 1.0;
        }*/
		double result =
				integration.simpsonRule(upper, lower, n, new Function()
				{
					@Override
					public double fun(double x)
					{
						if (x == 0)
						{
							return 0;
						}
						double t = ((realUpper - (1 - x) / x) - avg) / SD;
						return Math.pow(Math.E, -0.5 * t * t) / (x * x);
					}
				});
		result /= Math.pow(2 * Math.PI, 0.5) * SD;
		result = new BigDecimal(result).setScale(10, RoundingMode.HALF_UP).doubleValue(); // save 6 decimal places.
		return result;
	}
}