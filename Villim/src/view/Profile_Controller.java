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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import util.MethodUtil;
import util.Singleton;

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

	ResultSet rs = null;
	PreparedStatement pstmt = null;
	String sql = "";
	Connection conn = JDBCUtill.getInstance().getConnection();

	// 회원정보 가져오기
	@FXML
	private Label memberName;
	@FXML
	private Label memberId;
	@FXML
	private Label memberEmail;

	public void getInformation() {
		sql = "select id, name, email from users where id='" + Singleton.getInstance().getAccountId() + "'";
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

	@FXML
	private ImageView profileImage;
	
	// 프로필 사진 가져오기
	public void getPhoto() {
		profileImage.setImage(new Image(methodUtil.outputPhoto()));
	}
	
	// 회원정보 변경 화면 전환
	@FXML
	private Button changeProfileEditBtn;

	public void changeProfileEdit() {
		methodUtil.changeScene("/view/ProfileEdit_Layout.fxml", changeProfileEditBtn);
	}

	// 로그아웃
	@FXML
	private Button logoutBtn;

	public void Logout() {
		Singleton.getInstance().setAccountId(null);
		alert("로그아웃 되었습니다");
		methodUtil.changeScene("/view/Start_Layout.fxml", logoutBtn);
	}

	// 회원탈퇴
	@FXML
	private Button deleteAccountBtn;

	public void deleteAccount() {
		sql = "delete from users where id='" + Singleton.getInstance().getAccountId() + "'";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			Singleton.getInstance().setAccountId(null);
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