package view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import database.JDBCUtill;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import util.MethodUtil;
import util.Singleton;

public class Join_Controller implements Initializable {
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

	// 시작 화면
	@FXML
	public Pane alertPane_Start;
	@FXML
	public Text alertText_Start;

	MethodUtil methodUtil = new MethodUtil();

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

	ResultSet rs = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	String sql = "";
	Connection conn = JDBCUtill.getInstance().getConnection();

	@FXML
	private void signUp() {
		// button event
		Singleton.getInstance().debug(join_pass.getText() + " and " + join_pass_ok.getText());
		if (join_id.getText().isEmpty()) {
			alert("아이디를 입력해 주세요.");
		} else if (join_id.getText().matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*") || join_id.getText().contains(" ")) {
			alert("아이디에는 한글 또는 띄어쓰기를 사용할 수 없습니다.");
		} else if (join_pass.getText().isEmpty()) {
			alert("비밀번호를 입력해 주세요.");
		} else if (!join_pass.getText().equals(join_pass_ok.getText())) {
			alert("비밀번호를 다시 한 번 입력해 주세요.");
		} else if (join_pass.getText().length() < 8) {
			alert("비밀번호는 8자 이상이여야 합니다.");
		} else if (join_pass.getText().matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*") || join_pass.getText().contains(" ")) {
			alert("비밀번호에는 한글 또는 띄어쓰기를 사용할 수 없습니다.");
		} else if (join_pass.getText().matches("^[a-zA-Z0-9]*$")) {
			alert("비밀번호는 특수문자를 포함해야 합니다.");
		} else if (join_name.getText().isEmpty()) {
			alert("닉네임을 입력해 주세요.");
		} else if (join_name.getText().length() > 8) {
			alert("닉네임은 8자 이상이여야 합니다.");
		} else if (join_email.getText().isEmpty()) {
			alert("이메일을 입력해 주세요.");
		} else if (!join_email.getText().contains("@")) {
			alert("이메일 형식이 잘못 되었습니다.");
		} else if (!isIdChecked) {
			alert("아이디 중복체크를 해야합니다.");
		} else if (!isEmailChecked) {
			alert("이메일 중복체크를 해야합니다.");
		} else if (idEmailReCheck()) {
			alert("꼼수부리지 마십쇼");
		} else {
			changeLoginAfterJoin();

		}
	}

	public boolean idEmailReCheck() {
		sql = "SELECT * from profile where email=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, join_email.getText());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return true;
	}

	// 아이디 중복 체크
	boolean isIdChecked = false;

	public void checkId() {
		if (join_id_check()) {
			alert("이 아이디는 사용하실 수 없습니다");
			isIdChecked = false;
		} else {
			alert("사용 가능한 아이디입니다");
			isIdChecked = true;
		}
	}

	public boolean join_id_check() {
		sql = "SELECT count(*) cnt from profile where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, join_id.getText());
			ResultSet rs = pstmt.executeQuery();
			while (true) {
				if (rs.next()) {
					int cnt = rs.getInt("cnt");
					if (cnt > 0) {
						return true;
					} else {
						break;
					}
				}
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

	// 비밀번호 확인
	public void join_pass_check() {
		String joinPass = join_pass.getText();
		String joinPassOk = join_pass_ok.getText();
		if (joinPass.equals(joinPassOk) && !joinPass.isEmpty()) {
			checkPWImage.setImage(new Image("/resources/checkMark.png"));
			Singleton.getInstance().debug("확인 성공");
		} else if (joinPass.equals(joinPassOk) && joinPass.isEmpty()) {
			checkPWImage.setImage(new Image("/resources/xMark.png"));
			Singleton.getInstance().debug("확인 실패");
		} else {
			checkPWImage.setImage(new Image("/resources/xMark.png"));
			Singleton.getInstance().debug("확인 실패");
		}
	}

	// 이메일 중복 체크
	boolean isEmailChecked = false;

	public void checkEmail() {
		if (join_email_check()) {
			alert("이 이메일은 사용하실 수 없습니다");
			isEmailChecked = false;
		} else {
			alert("사용 가능한 이메일입니다");
			isEmailChecked = true;
		}
	}

	public boolean join_email_check() {
		int cnt = 0;
		sql = "SELECT email from profile";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String email = rs.getString("email");
				if (join_email.getText().equals(email)) {
					cnt++;
					if (cnt >= 1) {
						return true;
					} else {
						break;
					}
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

	// 회원가입 완료 후 로그인 화면 넘어가기
	public void changeLoginAfterJoin() {
		sql = "INSERT INTO profile(nick, id, password, email) VALUES (?,?,?,?)";
		try {
			String joinId = join_id.getText();
			String joinPass = join_pass.getText();
			String joinName = join_name.getText();
			String joinEmail = join_email.getText();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, joinName);
			pstmt.setString(2, joinId);
			pstmt.setString(3, joinPass);
			pstmt.setString(4, joinEmail);
			pstmt.executeUpdate();

			Singleton.getInstance().debug("성공");
			methodUtil.changeScene("/view/Login_Layout.fxml", join_button);
		} catch (Exception e) {
			Singleton.getInstance().debug("실패");
			e.printStackTrace();
		}
	}

	// 이전 화면 전환
	@FXML
	private Button backButton;

	public void back() {
		methodUtil.backScene(backButton);
	}

}