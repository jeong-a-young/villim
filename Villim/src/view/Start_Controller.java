package view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import database.JDBCUtill;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import util.MethodUtil;

public class Start_Controller implements Initializable {
	int ii = -50;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		alertText_Start.setText("반갑습니다");
//		Thread thread = new myThread();
//        thread.start();
	}
	//https://stackoverflow.com/questions/6409262/when-wil-the-new-thread-without-reference-be-garbage-collected
	//https://www.google.com/search?q=new+Thread+no+class&oq=new+Thread+no+class&aqs=chrome..69i57.6174j0j1&sourceid=chrome&ie=UTF-8
//	public class myThread extends Thread {
//		@Override
//		public void run() {
//				alertPane_Start.setLayoutY(ii * 2);
//				ii++;
//				Thread.sleep(20);
//				
//		}
//	}

	@FXML
	public Pane alertPane_Start;
	MethodUtil methodUtil = new MethodUtil();
	@FXML
	public Text alertText_Start;

	public void alert_Start() {
		alertText_Start.setText("반갑습니다");
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				for (int i = -50; i <= 0; i++) {
					alertPane_Start.setLayoutY(i * 2);
				}
			}
		}, 0, 50);
	}
	// 1. Join

	// 회원가입 화면 전환
	@FXML
	private Button changeJoinBtn;

	public void changeJoin() {
		methodUtil.changeScene("/view/Join_Layout.fxml", changeJoinBtn);
	}

	@FXML
	private Button changeLoginBtn;

	public void changeLogin() {
		methodUtil.changeScene("/view/Login_Layout.fxml", changeLoginBtn);
	}

}
