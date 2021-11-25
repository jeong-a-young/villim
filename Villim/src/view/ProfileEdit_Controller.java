//
//
//아직 미완성 클래스
//
//

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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import util.MethodUtil;
import util.Singleton;

public class ProfileEdit_Controller implements Initializable {
	// 이 클래스가 실행되면 호출되는 메소드 ^ 이거 있어야함 ^
	@Override
	public void initialize(URL location, ResourceBundle resources) {

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
	String sql, sql2 = "";
	Connection conn = JDBCUtill.getInstance().getConnection();

	// 변경
	@FXML
	private TextField memberNickName;
	@FXML
	private TextField memberPassword;
	@FXML
	private TextField memberPasswordCheck;

	public void passwordChange() {
		if (memberPassword.getText().isEmpty() && !memberNickName.getText().isEmpty()) {
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
		} else if (!memberPassword.getText().isEmpty() && memberNickName.getText().isEmpty()) {
			String password = memberPassword.getText();
			String passwordCheck = memberPasswordCheck.getText();
			sql = "update users set password='" + password + "' WHERE id='" + Singleton.getInstance().getAccountId()
					+ "'";

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
				// 변경된 회원정보를 새로고침 하려면?
				alert("변경에 성공했습니다");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (!memberPassword.getText().isEmpty() && !memberNickName.getText().isEmpty()) {
			String NickName = memberNickName.getText();
			String password = memberPassword.getText();
			String passwordCheck = memberPasswordCheck.getText();
			sql = "update users set password='" + password + "' WHERE id='" + Singleton.getInstance().getAccountId()
					+ "'";
			sql2 = "update users set name='" + NickName + "' WHERE id='" + Singleton.getInstance().getAccountId() + "'";

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
				pstmt = conn.prepareStatement(sql2);
				pstmt.executeUpdate();
				// 변경된 회원정보를 새로고침 하려면?
				alert("변경에 성공했습니다");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	// 프로필 화면 전환
	@FXML
	private Button changeProfileBtn;

	public void changeProfile() {
		methodUtil.changeScene("/view/Profile_Layout.fxml", changeProfileBtn);
	}

	// 이전 화면으로 가는 코드
	@FXML
	private Button backButton;

	public void back() {
		methodUtil.backScene(backButton);
	}
}