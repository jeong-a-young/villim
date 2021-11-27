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
	String sql = "";
	Connection conn = JDBCUtill.getInstance().getConnection();

	// 변경
	@FXML
	private TextField memberNickName;
	@FXML
	private TextField memberPassword;
	@FXML
	private TextField memberPasswordCheck;

	public void memberChange() {

		// 만약 닉네임만 채워져있다면
		if (!memberNickName.getText().isEmpty() && memberPassword.getText().isEmpty()) {
			String nickName = memberNickName.getText();
			if (nickName.length() > 8) {
				alert("닉네임은 8자를 넘을 수 없습니다");
			} else {
				sql = "update profile set nick='" + nickName + "' WHERE id='" + Singleton.getInstance().getAccountId()
						+ "'";
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.executeUpdate();
					alert("닉네임 변경에 성공했습니다.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// 만약 비밀번호만 채워져있다면
		} else if (!memberPassword.getText().isEmpty() && memberNickName.getText().isEmpty()) {
			String password = memberPassword.getText();
			String passwordCheck = memberPasswordCheck.getText();
			if (password.equals(passwordCheck)) {
				if (password.length() < 8) {
					alert("비밀번호는 8자가 넘어야 합니다.");
				} else if (password.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*") || password.contains(" ")) {
					alert("비밀번호에는 한글 또는 띄어쓰기를 사용할 수 없습니다");
				} else if (password.matches("^[a-zA-Z0-9]*$")) {
					alert("비밀번호는 특수문자를 포함해야합니다");
				} else {
					sql = "update profile set password='" + password + "' WHERE id='"
							+ Singleton.getInstance().getAccountId() + "'";
					try {
						pstmt = conn.prepareStatement(sql);
						pstmt.executeUpdate();
						alert("비밀번호 변경에 성공했습니다.");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				alert("비밀번호가 일치하지 않습니다.");
			}

			// 만약 닉네임과 비밀번호가 둘 다 채워져있다면
		} else if (!memberPassword.getText().isEmpty() && !memberNickName.getText().isEmpty()) {
			String nickName = memberNickName.getText();
			String password = memberPassword.getText();
			String passwordCheck = memberPasswordCheck.getText();
			if (nickName.length() > 8) {
				alert("닉네임은 8자를 넘을 수 없습니다");
			} else {
				if (password.equals(passwordCheck)) {
					if (password.length() < 8) {
						alert("비밀번호는 8자가 넘어야 합니다.");
					} else if (password.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*") || password.contains(" ")) {
						alert("비밀번호에는 한글 또는 띄어쓰기를 사용할 수 없습니다");
					} else if (password.matches("^[a-zA-Z0-9]*$")) {
						alert("비밀번호는 특수문자를 포함해야합니다");
					} else {
						sql = "update profile set nick='" + nickName + "',password='" + password + "'";
						try {
							pstmt = conn.prepareStatement(sql);
							pstmt.executeUpdate();
							alert("회원정보 변경에 성공했습니다.");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				} else {
					alert("비밀번호가 일치하지 않습니다.");
				}
			}
		}
	}

	// 이전 화면으로 가는 코드
	@FXML
	private Button backButton;

	public void back() {
		methodUtil.backScene(backButton);
	}

}