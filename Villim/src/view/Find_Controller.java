package view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import database.JDBCUtill;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import util.MethodUtil;
import util.Singleton;

public class Find_Controller implements Initializable {

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

	// 팝업 알림
	public void popUpAlert(String msg, String header) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("안내");
		alert.setHeaderText(header);
		alert.setContentText(msg);

		alert.show();
	}

	MethodUtil methodUtil = new MethodUtil();

	ResultSet rs = null;
	PreparedStatement pstmt = null;
	String sql = "";
	Connection conn = JDBCUtill.getInstance().getConnection();

	// 아이디 찾기
	@FXML
	private TextField memberEmail;

	public void findId() {
		sql = "select id from profile where email='" + memberEmail.getText() + "'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (memberEmail.getText().isEmpty()) {
				alert("이메일을 입력해 주세요.");
			} else if (rs.next()) {
				String id = rs.getNString("id");
				popUpAlert("아이디: " + id, "아이디를 찾았습니다.");
			} else {
				alert("해당 이메일로 가입된 아이디를 찾지 못했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 비밀번호 찾기
	@FXML
	private TextField memberId;

	public void findPassword() {
		int idCount = selectId();
		int emailCount = selectEmail();
		int findCount = 0;
		
		sql = "select password from profile where id='" + memberId.getText() + "'";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (memberId.getText().isEmpty()) {
				alert("아이디를 입력해 주세요.");
			} else if (memberEmail.getText().isEmpty()) {
				alert("이메일을 입력해 주세요.");
			} else if (idCount > 0 && emailCount > 0) {
				if (rs.next()) {
					String password = rs.getNString("password");
					popUpAlert("비밀번호: " + password, "비밀번호를 찾았습니다.");
					findCount++;
				}
			} else if (findCount == 0) {
				alert("입력된 정보로 가입된 회원정보를 찾지 못했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 가입된 아이디인지 판단
	public int selectId() {
		int count = 0;
		sql = "select * from profile where id='" + memberId.getText() + "'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = rs.getNString("id");
				if (memberId.getText().equals(id)) {
					count++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	// 가입된 이메일인지 판단
	public int selectEmail() {
		int count = 0;
		sql = "select * from profile where email='" + memberEmail.getText() + "'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String email = rs.getNString("email");
				if (memberEmail.getText().equals(email)) {
					count++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	// 이전 화면 전환
	@FXML
	private Button backButton;

	public void back() {
		methodUtil.backScene(backButton);
	}

}