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

public class Login_Controller implements Initializable {
	// 이 클래스가 실행되면 호출되는 메소드 ^ 이거 있어야함 ^
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (Main_Controller.getInstance().getCLC().equals("Join_Controller")) {
			alert("회원가입을 완료하였습니다");
		}
		// 현재 화면을 나타내는 이 프로그램 내부의 정적 변수
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


	Statement stmt = null;
	PreparedStatement pstmt = null;
	String sql = "";
	Connection conn = JDBCUtill.getConnection();
	
	public static String userId = "";
	
	@FXML
	private TextField id;
	@FXML
	private TextField pass;
	@FXML
	private Button loginButton;
	
	
	public void login() {
		if(id.getText().isEmpty() && pass.getText().isEmpty()) {
			alert("아이디와 비밀번호를 입력해주세요");
		} else if(id.getText().isEmpty()) {
			alert("아이디를 입력해주세요");
		} else if(pass.getText().isEmpty()) {
			alert("비밀번호를 입력해주세요");
		} else {
			changeMainAfterLogin();
		}
	}
	
	@FXML
	private Button changeStartBtn;
	MethodUtil methodUtil = new MethodUtil();

	public void changeMainAfterLogin() {
		try {
			String loginId = id.getText();
			String loginPass = pass.getText();

			sql = "SELECT * FROM `users` WHERE id = ? and password = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginId);
			pstmt.setString(2, loginPass);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("로그인 성공");
				userId = loginId;
//				methodUtil.changeScene("/view/Home_Layout.fxml", loginButton);
				
			} else {
				System.out.println("로그인 실패");
				userId = "";
			}
			System.out.println(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void changeStart() {
		methodUtil.changeScene("/view/Start_Layout.fxml", changeStartBtn);
	}
}
