package view;

import database.JDBCUtill;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import util.MethodUtil;
import util.Singleton;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import static view.Login_Controller.userId;

public class ProfileEdit_Controller implements Initializable {
	// 이 클래스가 실행되면 호출되는 메소드 ^ 이거 있어야함 ^
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 현재 화면의 이전 화면을 변수
		Singleton.getInstance().setPreviousLayoutClass(Singleton.getInstance().getCLC());
		// 현재 화면을 나타내는 변수
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
	
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	String sql = "";
	Connection conn = JDBCUtill.getInstance().getConnection();

	// 닉네임 변경
	@FXML
	private TextField memberNickName;

	public void nickNameChange() {

		String NickName = memberNickName.getText();
		sql = "update users set name='" + NickName + "' WHERE id='" + Singleton.getInstance().getAccountId() + "'";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			// 변경된 회원정보를 새로고침 하려면?
			alert("변경에 성공했습니다");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 비밀번호 변경
	@FXML
	private TextField MemberPassword;
	@FXML
	private TextField MemberPasswordCheck;

	public void passwordChange() {

		String password = MemberPassword.getText();
		String passwordCheck = MemberPasswordCheck.getText();
		sql = "update users set password='" + password + "' WHERE id='" + userId + "'";

		try {
			if (passwordCheck.equals(password)) {
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
				// 변경된 회원정보를 새로고침 하려면?
				alert("변경에 성공했습니다");
			} else {
				alert("비밀번호가 일치하지 않습니다");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 프로필 화면 전환
	@FXML
	private Button changeProfileBtn;

	public void changeProfile() {
		methodUtil.changeScene("/view/Profile_Layout.fxml", changeProfileBtn);
	}
	
}