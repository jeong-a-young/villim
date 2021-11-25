package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import util.MethodUtil;

public class Start_Controller implements Initializable {
	// 이 클래스가 실행되면 호출되는 메소드 ^ 이거 있어야함 ^
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 알림 메소드
		alert("반갑습니다!");

	}

	MethodUtil methodUtil = new MethodUtil();
	// 알림창
	@FXML
	public Pane alertPane;
	@FXML
	public Text alertText;
	public Thread t = new Thread() {
		public void run() {
			try {
				Thread.sleep(500);
				for (int i = -15; i <= 0; i++) {
					alertPane.setLayoutY(i * 4);
					Thread.sleep(25);
				}
				Thread.sleep(2000);
				for (int i = 0; i >= -15; i--) {
					alertPane.setLayoutY(i * 4);
					Thread.sleep(25);
				}
			} catch (Exception e) {
				System.out.println(getClass().getName() + " 쓰레드 오류: " + e);
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

	// 회원가입 화면 전환
	@FXML
	private Button changeJoinBtn;

	public void changeJoin() {
		methodUtil.changeScene("/view/Join_Layout.fxml", changeJoinBtn);
	}

	// 로그인 화면 전환
	@FXML
	private Button changeLoginBtn;

	public void changeLogin() {
		methodUtil.changeScene("/view/Login_Layout.fxml", changeLoginBtn);
	}

}