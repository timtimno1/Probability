package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
		primaryStage.setTitle("Probability");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}


	public static void main(String[] args)
	{
       /* Scanner in=new Scanner(System.in);
        while (true)
        {
            byte x=in.nextByte();byte y=in.nextByte();
            if(x==0 && y==0) break;
            JointProbProblem d1=new JointProbProblem(x,y);
            d1.jointProb((byte)0,(byte) 0,false,new byte[]{0,0,0});
        }*/
		launch(args);
	}
}
