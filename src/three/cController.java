package three;

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

public class cController implements Initializable
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
		if (x >= 0 && x <= 6 && y >= 0 && y <= 6)
		{
			if (x == y)
				p.setText(String.format("%4.2f%%", 1 / 216.0 * 100));
			else if (y > x)
				p.setText(String.format("%4.2f%%", (6 * (y - x - 1) + 6) / 216.0 * 100));
			else
				p.setText("0%");
		}
		else
			p.setText("0%");
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

	public static void start(Stage primaryStage, Parent root)
	{
		primaryStage.setTitle("第三題C");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{

	}
}
