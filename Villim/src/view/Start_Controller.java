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
	//이 클래스가 실행되면 호출되는 메소드                   ^ 이거 있어야함 ^
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//현재 화면을 나타내는 이 프로그램 내부의 정적 변수
		Main_Controller.getInstance().setCLC(getClass().getSimpleName());
		//알림 메소드
		alert("반갑습니다!");
		
	}

	MethodUtil methodUtil = new MethodUtil();
	//알림창
	@FXML
	public Pane alertPane;
	@FXML
	public Text alertText;
	public Thread t = new Thread(){
	    public void run() {
			try {
				Thread.sleep(500);
	    	for (int i = -15; i <= 0; i++) {
				alertPane.setLayoutY(i*4);
				Thread.sleep(20);
			}
	    	Thread.sleep(2000);
	    	for (int i = 0; i >= -15; i--) {
				alertPane.setLayoutY(i*4);
				Thread.sleep(20);
			}
			} catch (Exception e) {
				System.out.println(getClass().getName()+" 쓰레드 오류: " + e);
			}
	    	
	    }
	};
	public void alert(String text) {
		alertText.setText(text);
		if (t.isAlive()) {
			return;
		}
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
