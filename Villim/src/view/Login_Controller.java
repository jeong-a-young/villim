package view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import database.JDBCUtill;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import util.MethodUtil;
import util.Singleton;

public class Login_Controller implements Initializable {
	// 이 클래스가 실행되면 호출되는 메소드 ^ 이거 있어야함 ^
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 현재 화면의 이전 화면을 변수
		Singleton.getInstance().setPreviousLayoutClass(Singleton.getInstance().getCLC());

		if (Singleton.getInstance().getCLC().equals("Join_Controller")) {
			alert("회원가입을 완료하였습니다");
		}
		// 현재 화면을 나타내는 이 프로그램 내부의 정적 변수
		Singleton.getInstance().setCLC(getClass().getSimpleName());
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

	MethodUtil methodUtil = new MethodUtil();
	
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	Connection conn = JDBCUtill.getInstance().getConnection();

	@FXML
	private TextField id;
	@FXML
	private TextField pass;
	@FXML
	private Button loginButton;

	// 공백 확인
	public void login() {
		if (id.getText().isEmpty() && pass.getText().isEmpty()) {
			alert("아이디와 비밀번호를 입력해주세요");
		} else if (id.getText().isEmpty()) {
			alert("아이디를 입력해주세요");
		} else if (pass.getText().isEmpty()) {
			alert("비밀번호를 입력해주세요");
		} else {
			changeMainAfterLogin();
		}
	}

	// 아이디와 비밀번호 일치 확인
	public void changeMainAfterLogin() {
		
		int count = 0;
		sql = "select id, password from users";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String getId = rs.getString("id");
				String getPassword = rs.getString("password");
				if (id.getText().equals(getId) && pass.getText().equals(getPassword)) {
					count++;
					Singleton.getInstance().setAccountId(getId);
					System.out.println("로그인 성공" + Singleton.getInstance().getAccountId());
					methodUtil.changeScene("/view/Home_Layout.fxml", loginButton);
					break;
				}
			}
			if (count != 1) {
				System.out.println("로그인 실패" + Singleton.getInstance().getAccountId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 시작 화면 전환
	@FXML
	private Button changeStartBtn;
	
	public void changeStart() {
		methodUtil.backScene(changeStartBtn);
	}
	
}