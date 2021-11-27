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
		if (Singleton.getInstance().sceneList.get(Singleton.getInstance().sceneList.size() - 1)
				.equals("/view/Join_Layout.fxml")) {
			alert("회원가입을 완료하였습니다.");
		}
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
						Thread.sleep(25);
					}
					Thread.sleep(2000);
					for (int i = 0; i >= -15; i--) {
						alertPane.setLayoutY(i * 4);
						Thread.sleep(25);
					}
					isAliveThread = false;
				} catch (Exception e) {
					Singleton.getInstance().debug(getClass().getName() + " 쓰레드 오류: " + e);
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
		sql = "select nick, id, password from profile";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String getId = rs.getString("id");
				String getNick = rs.getString("nick");
				String getPassword = rs.getString("password");
				if (id.getText().equals(getId) && pass.getText().equals(getPassword)) {
					count++;
					Singleton.getInstance().setAccountId(getId);
					Singleton.getInstance().setAccountNick(getNick);
					Singleton.getInstance().debug("로그인 성공" + Singleton.getInstance().getAccountId());
					methodUtil.changeScene("/view/Home_Layout.fxml", loginButton);
					break;
				}
			}
			if (count != 1) {
				Singleton.getInstance().debug("로그인 실패" + Singleton.getInstance().getAccountId());
				alert("아이디 또는 비밀번호가 틀렸습니다");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 아이디, 비밀번호 찾기
	@FXML
	private Button changeFindBtn;

	public void changeFind() {
		methodUtil.changeScene("/view/Find_Layout.fxml", changeFindBtn);
	}

	// 이전 화면으로
	// 시작 - 이 구역은 세트로 어디서든지 배치되야하는 코드
	@FXML
	private Button backButton;

	public void back() {
		methodUtil.backScene(backButton);
	}
	// 끝

}