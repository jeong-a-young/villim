package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import util.MethodUtil;

public class Login_Controller implements Initializable {
	//이 클래스가 실행되면 호출되는 메소드                   ^ 이거 있어야함 ^
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (Main_Controller.getInstance().getCLC().equals("Login_Controller")) {
			alert("회원가입을 완료하였습니다");
		}
		//현재 화면을 나타내는 이 프로그램 내부의 정적 변수
		Main_Controller.getInstance().setCLC(getClass().getSimpleName());
	}
	// 알림창
	@FXML
	public Pane alertPane;
	@FXML
	public Text alertText;
	public boolean isAliveThread;

	public void alert(String text) {
		Thread t = new Thread() {
			public void run() {
				try {
					isAliveThread = true;
					Thread.sleep(500);
					for (int i = -15; i <= 0; i++) {
						alertPane.setLayoutY(i * 4);
						Thread.sleep(20);
					}
					Thread.sleep(2000);
					for (int i = 0; i >= -15; i--) {
						alertPane.setLayoutY(i * 4);
						Thread.sleep(20);
					}
					isAliveThread = false;
				} catch (Exception e) {
					System.out.println(getClass().getName() + " 쓰레드 오류: " + e);
				}

			}
		};
		alertText.setText(text);
		if (isAliveThread) {
			return;
		}
		t.start();
	}
	@FXML
	private Button changeStartBtn;
	MethodUtil methodUtil = new MethodUtil();
	public void changeStart() {
		methodUtil.changeScene("/view/Start_Layout.fxml", changeStartBtn);
	}
}
