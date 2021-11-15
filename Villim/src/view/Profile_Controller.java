package view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import database.JDBCUtill;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.MethodUtil;

import static view.Login_Controller.userId;

public class Profile_Controller implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		getInformation();
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
	Connection conn = JDBCUtill.getConnection();

	// 회원정보 가져오기
	@FXML
	private Label memberName;
	@FXML
	private Label memberId;
	@FXML
	private Label memberEmail;

	public void getInformation() {
		sql = "select id, name, email from users where id='" + userId + "'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memberId.setText(rs.getString("id"));
				memberName.setText(rs.getString("name"));
				memberEmail.setText(rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 닉네임 변경 화면 팝업
	@FXML
	private Button popUpNickNameChangeBtn;
	@FXML
	private Stage popUpNickNameStage;

	public void popUpNickNameChange() {
		methodUtil.popUpScene(popUpNickNameChangeBtn, popUpNickNameStage, "/view/NickNameChange_Layout.fxml", "닉네임 변경");
	}

	// 비밀번호 변경 화면 팝업
	@FXML
	private Button popUpPasswordChangeBtn;
	@FXML
	private Stage popUpPasswordStage;

	public void popUpPasswordChange() {
		methodUtil.popUpScene(popUpPasswordChangeBtn, popUpPasswordStage, "/view/PasswordChange_Layout.fxml",
				"비밀번호 변경");
	}

	// 로그아웃
	@FXML
	private Button logoutBtn;

	public void Logout() {
		userId = "";
		alert("로그아웃 되었습니다");
		methodUtil.changeScene("/view/Start_Layout.fxml", logoutBtn);
	}

	// 회원탈퇴
	@FXML
	private Button deleteAccountBtn;

	public void deleteAccount() {
		sql = "delete from users where id='" + userId + "'";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			userId = "";
			alert("탈퇴 처리되었습니다");
			methodUtil.changeScene("/view/Start_Layout.fxml", deleteAccountBtn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 홈 화면 전환
	@FXML
	private Button changeHomeBtn;

	public void changeHome() {
		methodUtil.changeScene("/view/Home_Layout.fxml", changeHomeBtn);
	}

}