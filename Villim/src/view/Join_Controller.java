package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;

import database.JDBCUtill;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import util.MethodUtil;

public class Join_Controller {
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

	// 시작 화면
	@FXML
	public Pane alertPane_Start;
	@FXML
	public Text alertText_Start;
	
	MethodUtil methodUtil = new MethodUtil();


	@FXML
	private Button changeStartBtn;

	public void changeStart() {
		methodUtil.changeScene("/view/Start_Layout.fxml", changeStartBtn);
	}


	// 회원가입
	@FXML
	private ImageView checkPWImage;

	@FXML
	private TextField join_id;
	@FXML
	private TextField join_pass;
	@FXML
	private TextField join_pass_ok;
	@FXML
	private TextField join_name;
	@FXML
	private TextField join_email;
	@FXML
	private Button join_button;

	Statement stmt = null;
	PreparedStatement pstmt = null;
	String sql = "";
	Connection conn = JDBCUtill.getConnection();

	// 이 메소드는 완성이 안됨 건들지 마셈
	@FXML
	private void signUp() {
		// button event
		System.out.println(join_pass.getText() + " and " + join_pass_ok.getText());
		if (join_id.getText().isEmpty()) {
			alert("아이디를 입력해주세요");
		} else if (join_id.getText().matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*") || join_id.getText().contains(" ")) {
			alert("아이디에는 한글 또는 띄어쓰기를 사용할 수 없습니다");
		} else if (join_pass.getText().isEmpty()) {
			alert("비밀번호를 입력해주세요");
		} else if (!join_pass.getText().equals(join_pass_ok.getText())) {
			alert("비밀번호 확인을 다시 해주세요");
		} else if (join_pass.getText().length() < 8) {
			alert("비밀번호는 8자가 넘어야 합니다");
		} else if (join_pass.getText().matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*") || join_pass.getText().contains(" ")) {
			alert("비밀번호에는 한글 또는 띄어쓰기를 사용할 수 없습니다");
		} else if (join_pass.getText().matches("^[a-zA-Z0-9]*$")) {
			alert("비밀번호는 특수문자를 포함해야합니다");
		} else if (join_name.getText().isEmpty()) {
			alert("닉네임을 입력해주세요");
		} else if (join_name.getText().length() > 8) {
			alert("닉네임은 8자를 넘을 수 없습니다");
		} else if (join_email.getText().isEmpty()) {
			alert("이메일을 입력해주세요");
		} 
		else if (!join_email.getText().contains("@")) {
			alert("이메일이 잘못되었습니다");
		} else {
			changeLoginAfterJoin();
			
		}
	}

	public void checkId() {
		if (join_id_check()) {
			alert("이 아이디는 사용하실 수 없습니다");
		}
		else {
			alert("사용 가능한 아이디입니다");
		}
	}

	public boolean join_id_check() {
		sql = "SELECT count(*) cnt from users where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, join_id.getText());
			ResultSet rs = pstmt.executeQuery();
			while (true) {
				if (rs.next()) {
					int cnt = rs.getInt("cnt");
					if (cnt > 0) {
						return true;
					}

				}
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

	public void join_pass_check() {
		String joinPass = join_pass.getText();
		String joinPassOk = join_pass_ok.getText();
		if (joinPass.equals(joinPassOk) && !joinPass.isEmpty()) {
			checkPWImage.setImage(new Image("/resources/checkMark.png"));
			System.out.println("확인 성공");
		} else if (joinPass.equals(joinPassOk) && joinPass.isEmpty()) {
			checkPWImage.setImage(new Image("/resources/xMark.png"));
			System.out.println("확인 실패");
		} else {
			checkPWImage.setImage(new Image("/resources/xMark.png"));
			System.out.println("확인 실패");
		}
	}

	// 회원가입 완료 후 로그인 화면 넘어가기
	public void changeLoginAfterJoin() {
//		sql = "INSERT INTO users0 VALUES(?, ?, ?, ?, ?, ?)";
//		try {
//			String joinId = join_id.getText();
//			String joinPass = join_pass.getText();
//			String joinName = join_name.getText();
//			int tree = 0;
//			String joinEmail = join_email.getText();
//			String theme = "white";
//
//			pstmt.setString(1, joinId);
//			pstmt.setString(2, joinPass);
//			pstmt.setString(3, joinName);
//			pstmt.setInt(4, tree);
//			pstmt.setString(5, joinEmail);
//			pstmt.setString(6, theme);
//			pstmt = conn.prepareStatement(sql);
//			pstmt.executeUpdate();
//			System.out.println("성공");
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("실패");
//		}
		//new Login_Controller().alert("회원가입이 완료되었습니다");
		methodUtil.changeScene("/view/Login_Layout.fxml", join_button);
	}
}
