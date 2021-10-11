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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;

public class PoissonController implements Initializable
{
	@FXML
	private Button back;
	@FXML
	private TextField lambda;
	@FXML
	private TextField expectations;
	@FXML
	private TextField var;

	public void setIn(ActionEvent event)
	{
		double lambda = Double.parseDouble(this.lambda.getText());
		XYData.data.getData().clear();
		XYData.data1.getData().clear();

		expectations.setText(this.lambda.getText());
		var.setText(this.lambda.getText());
		double SD = Math.pow(Double.parseDouble(var.getText()), 0.5);

		XYData.xLowerBound = 0;
		XYData.xUpperBound = Double.parseDouble(expectations.getText()) + 5 * SD;
		XYData.yLowerBound = 0;
		XYData.yUpperBound = 1;
		XYData.data.setName("PDF");
		XYData.data1.setName("CDF");
		XYData.CDF = true;

		double temp = 1;
		double sum = 0;
		for (int i = 0; i <= new BigDecimal(XYData.xUpperBound).setScale(4, RoundingMode.HALF_UP).intValue(); i++)
		{
			if (i != 0) temp *= i;
			double temp1 = f(i, temp);
			XYData.data.getData().add(new XYChart.Data<Number, Number>(i, temp1));
			XYData.data1.getData().add(new XYChart.Data<Number, Number>(i, sum += temp1));
		}


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
		primaryStage.setTitle("第二題卜瓦松分布");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	private double f(double x, double temp)
	{
		double lambda = Double.parseDouble(this.lambda.getText());

		return (Math.pow(Math.E, -1 * lambda) * Math.pow(lambda, x)) / temp;
	}
}