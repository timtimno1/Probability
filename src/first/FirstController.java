package first;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import math.Integration;
import sample.Main;
import java.net.URL;
import java.util.ResourceBundle;

public class FirstController implements Initializable
{
	@FXML
	private Button back;
	@FXML
	private Button start;
	@FXML
	private TextField res;
	@FXML
	private TextField input1;
	@FXML
	private TextField input2;

	@Override
	public void initialize(URL location, ResourceBundle resou)
	{

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

	public void SetIn()
	{
		int upperInput = Integer.parseInt(input2.getText());
		int lowerInput = Integer.parseInt(input1.getText());

		double ave = 200, standardDeviation = 30;                //設定標準差及期望值
		double upper = (upperInput - ave) / standardDeviation;    //將輸入數值做標準化
		double lower = (lowerInput - ave) / standardDeviation;

		Integration d1 = new Integration();
		double rupperRes = d1.stdGaussValue(upper);           //積分
		double lowerRes = d1.stdGaussValue(lower);
		res.setText(String.format("%5.2f%%", (rupperRes - lowerRes) * 100));   //將上限減下限得到答案
	}

	public static void start(Stage primaryStage, Parent root) throws Exception
	{
		primaryStage.setTitle("第一題");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
}
