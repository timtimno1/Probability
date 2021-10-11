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
import java.net.URL;
import java.util.ResourceBundle;

public class UniformController implements Initializable
{
	@FXML
	private Button back;
	@FXML
	private TextField x;
	@FXML
	private TextField y;
	@FXML
	private TextField expectations;
	@FXML
	private TextField SD;

	public void setIn(ActionEvent event)
	{
		int x = Integer.parseInt(this.x.getText());
		int y = Integer.parseInt(this.y.getText());
		XYData.data.getData().clear();
		XYData.data1.getData().clear();

		expectations.setText(String.format("%4.2f", x + y / 2.0));
		SD.setText(String.format("%4.2f", Math.pow(x - y, 2) / 12.0));

		double sum = 0;
		for (int i = x; i <= y; i++)
		{
			double temp = 1.0 / (y - x);
			XYData.data.getData().add(new XYChart.Data<Number, Number>(i, temp));
			XYData.data1.getData().add(new XYChart.Data<Number, Number>(i, sum += temp));
		}

		XYData.xLowerBound = x - 10 > 0 ? x - 10 : 0;
		XYData.xUpperBound = y + 10;
		XYData.yLowerBound = 0;
		XYData.yUpperBound = 1;
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
		primaryStage.setTitle("第二題均勻隨機變數");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
}
