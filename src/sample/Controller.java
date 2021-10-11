package sample;

import first.FirstController;
import fourth.FourthController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import three.ThreeController;
import two.TwoController;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable
{
	@FXML
	private Button firstButton;
	@FXML
	private Button twoButton;
	@FXML
	private Button threeButton;
	@FXML
	private Button fourthButton;

	@Override
	public void initialize(URL location, ResourceBundle resou)
	{

	}

	public void toFirst(ActionEvent event)
	{
		Stage primaryStage = (Stage) firstButton.getScene().getWindow();
		primaryStage.close();
		primaryStage = null;


		try
		{
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("first/First.fxml"));
			FirstController.start(new Stage(), root);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void toTwo(ActionEvent event)
	{
		Stage primaryStage = (Stage) twoButton.getScene().getWindow();
		primaryStage.close();
		primaryStage = null;


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

	public void toThree(ActionEvent event)
	{
		Stage primaryStage = (Stage) threeButton.getScene().getWindow();
		primaryStage.close();
		primaryStage = null;

		try
		{
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("three/Three.fxml"));
			ThreeController.start(new Stage(), root);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void toFourth(ActionEvent event)
	{
		Stage primaryStage = (Stage) fourthButton.getScene().getWindow();
		primaryStage.close();
		primaryStage = null;


		try
		{
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fourth/Fourth.fxml"));
			FourthController.start(new Stage(), root);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
