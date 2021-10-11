package three;

import backTracking.JointProbProblem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class aController implements Initializable
{

	@FXML
	private Button back;
	@FXML
	private TextField x;
	@FXML
	private TextField y;
	@FXML
	private TextField p;

	public void setIn(ActionEvent event)
	{
		byte x = Byte.parseByte(this.x.getText());
		byte y = Byte.parseByte(this.y.getText());

		JointProbProblem d1 = new JointProbProblem(x, y);
		d1.jointProb((byte) 0, (byte) 0, false, new byte[]{0, 0, 0});

		p.setText(String.format("%5.2f%%", d1.getCount() / 216.0 * 100));
	}

	public void back(ActionEvent event)
	{
		Stage a = (Stage) back.getScene().getWindow();
		a.close();
		a = null;

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

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{

	}

	public static void start(Stage primaryStage, Parent root)
	{
		primaryStage.setTitle("第三題A");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
}
