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

public class HypergeometricController implements Initializable
{
	@FXML
	private Button back;
	@FXML
	private TextField n;
	@FXML
	private TextField m;
	@FXML
	private TextField k;
	@FXML
	private TextField expectations;
	@FXML
	private TextField var;

	public void setIn(ActionEvent event)
	{
		double n = Integer.parseInt(this.n.getText());
		double m = Integer.parseInt(this.m.getText());
		double k = Integer.parseInt(this.k.getText());

		XYData.data.getData().clear();
		XYData.data1.getData().clear();

		expectations.setText(String.format("%4.2f", k * (m / n)));
		var.setText(String.format("%4.2f", k * (m / n) * (1 - m / n) * ((n - k) / (n - 1))));
		double SD = Math.pow(Double.parseDouble(var.getText()), 0.5);

		XYData.xLowerBound = Double.parseDouble(expectations.getText()) - 5 * SD;
		XYData.xUpperBound = Double.parseDouble(expectations.getText()) + 5 * SD;
		XYData.yLowerBound = 0;
		XYData.yUpperBound = 1;
		XYData.data.setName("PDF");
		XYData.data1.setName("CDF");
		XYData.CDF = true;

		double sum = 0;
		for (int i = new BigDecimal(XYData.xLowerBound).setScale(4, RoundingMode.HALF_UP).intValue(); i <= new BigDecimal(XYData.xUpperBound).setScale(4, RoundingMode.HALF_UP).intValue(); i++)
		{
			// System.out.println(f(i));

			double temp = f(i);
			XYData.data.getData().add(new XYChart.Data<Number, Number>(i, temp));
			XYData.data1.getData().add(new XYChart.Data<Number, Number>(i, sum += temp));
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
		primaryStage.setTitle("第二題超幾何分配");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	private double f(int x)
	{
		int n = Integer.parseInt(this.n.getText());
		int m = Integer.parseInt(this.m.getText());
		int k = Integer.parseInt(this.k.getText());
		//System.out.println(math.alg.bin(m,x));
		BigDecimal temp0 = new BigDecimal(math.alg.bin(m, x));
		BigDecimal temp1 = new BigDecimal(math.alg.bin(n - m, k - x));
		BigDecimal temp2 = new BigDecimal(math.alg.bin(n, k));

		return temp0.multiply(temp1).divide(temp2, 10, RoundingMode.HALF_UP).setScale(10, RoundingMode.HALF_UP).doubleValue();
	}
}