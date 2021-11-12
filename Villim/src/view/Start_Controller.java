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
		alert();
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
	MethodUtil methodUtil = new MethodUtil();
	
	//알림창
			@FXML
			public Pane alertPane;
			@FXML
			public Text alertText;
			public void alert() {
				alertText.setText("반갑습니다");
				Thread t = new Thread()
				{
				    public void run() {
						try {
							Thread.sleep(500);
				    	for (int i = -60; i <= 0; i++) {
							alertPane.setLayoutY(i);
							Thread.sleep(10);
						}
						} catch (Exception e) {
							System.out.println("alert_Start 쓰레드 오류: " + e);
						}
				    	
				    }
				};
				t.start();
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
