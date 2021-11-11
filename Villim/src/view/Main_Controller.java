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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import util.MethodUtil;

public class Main_Controller {
	// 시작 화면
	@FXML
	public Pane alertPane_Start;
	@FXML
	public Text alertText_Start;
	MethodUtil methodUtil = new MethodUtil();

	// 0.
	@FXML
	private Button changeStartBtn;

	public void changeStart() {
		methodUtil.changeScene("/view/Start_Layout.fxml", changeStartBtn);
	}

	@FXML
	private Button changeMainBtn;

	public void changeMain() {
		methodUtil.changeScene("/view/Main_Layout.fxml", changeMainBtn);
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

//이 메소드는 완성이 안됨 건들지 마셈	
	@FXML
	private void signUp() {
		// button event
		System.out.println(join_pass.getText() + " and " + join_pass_ok.getText());
		if (join_id.getText().matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*") || join_id.getText().contains(" ")) {
//		errorScreen.setVisible(true);
//		startScreen.setDisable(true);
//		errorScreenMsg.setText("비밀번호에는 한글 또는 띄어쓰기를 사용할 수 없습니다");
			System.out.println("아이디에는 한글 또는 띄어쓰기를 사용할 수 없습니다");
//		errorTitle.setText("[ ERROR ]")
		} else if (join_name.getText().length() > 8) {
//			errorScreen.setVisible(true);
//			startScreen.setDisable(true);
//			errorScreenMsg.setText("비밀번호는 8자가 넘어야 합니다");
			System.out.println("닉네임은 8자를 넘을 수 없습니다");
//			errorTitle.setText("[ ERROR ]");
		} else if (!join_pass.getText().equals(join_pass_ok.getText()) || join_pass.getText().isEmpty()) {
//			errorScreen.setVisible(true);
//			startScreen.setDisable(true);
//			errorScreenMsg.setText("비밀번호 확인을 다시 해주세요");
			System.out.println("비밀번호 확인을 다시 해주세요");
//			errorTitle.setText("[ ERROR ]");
		} else if (join_pass.getText().length() < 8) {
//			errorScreen.setVisible(true);
//			startScreen.setDisable(true);
//			errorScreenMsg.setText("비밀번호는 8자가 넘어야 합니다");
			System.out.println("비밀번호는 8자가 넘어야 합니다");
//			errorTitle.setText("[ ERROR ]");
		} else if (join_pass.getText().matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*") || join_pass.getText().contains(" ")) {
//			errorScreen.setVisible(true);
//			startScreen.setDisable(true);
//			errorScreenMsg.setText("비밀번호에는 한글 또는 띄어쓰기를 사용할 수 없습니다");
			System.out.println("비밀번호에는 한글 또는 띄어쓰기를 사용할 수 없습니다");
//			errorTitle.setText("[ ERROR ]");
		} else if (join_pass.getText().matches("^[a-zA-Z0-9]*$")) {
//			errorScreen.setVisible(true);
//			startScreen.setDisable(true);
//			errorScreenMsg.setText("비밀번호는 특수문자를 포함해야합니다");
			System.out.println("비밀번호는 특수문자를 포함해야합니다");
//			errorTitle.setText("[ ERROR ]");
		} else if (false/* join_id_check() */) {
//			errorScreen.setVisible(true);
//			startScreen.setDisable(true);
//			errorScreenMsg.setText("비밀번호에는 한글 또는 띄어쓰기를 사용할 수 없습니다");
			System.out.println("아이디에는 한글 또는 띄어쓰기를 사용할 수 없습니다");
//			errorTitle.setText("[ ERROR ]")
		} else if (!join_email.getText().contains("@")) {
//			errorScreen.setVisible(true);
//			startScreen.setDisable(true);
//			errorScreenMsg.setText("비밀번호에는 한글 또는 띄어쓰기를 사용할 수 없습니다");
			System.out.println("이메일이 잘못되었습니다");
//			errorTitle.setText("[ ERROR ]")
		} else {
			changeLoginAfterJoin(join_button, "회원가입을 완료하였습니다");
		}
	}

	public void successLogin() {

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

	// 2. Login

	// 로그인 화면 전환

	// 회원가입 완료 후 로그인 화면 넘어가기
	@FXML
	public Pane alertPane_Login;
	@FXML
	public Text alertText_Login;

	public void changeLoginAfterJoin(Button button, String text) {
		// sql = "insert into UserLoginData values('" + email_signUp.getText() + "','" +
		// pw_signUp.getText()
		// + "','','','#ff9200')";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			System.out.println("성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("실패");
		}
		methodUtil.changeScene("/view/Login_Layout.fxml", button);
		alertText_Login.setText(text);
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				for (int i = -50; i <= 0; i++) {
					alertPane_Login.setLayoutY(i * 2);
				}
			}
		}, 0, 50);
	}

	// 3. Main

	// 검색
	@FXML
	private TextField searchTextField;
	@FXML
	private Button searchBtn;

	public void search() {
		String searchContent = searchTextField.getText();

		// 검색 버튼 누르면 화면 전환하기
		methodUtil.changeScene("/view/Search_Layout.fxml", searchBtn);
	}

	// 버튼 화면 전환
	// 게시물 작성
	@FXML
	private Button changeWritePostBtn;

	public void changeWritePost() {
		methodUtil.changeScene("/view/WritePost_Layout.fxml", changeWritePostBtn);
	}

	// 채팅
	@FXML
	private Button changeChattingBtn;

	public void changeChatting() {
		methodUtil.changeScene("/view/Chatting_Layout.fxml", changeChattingBtn);
	}

	// 게시물 목록
	@FXML
	private Button changePostListBtn;

	public static int postListCount = 0;
	public static int postListChangeCount = 0;

	public void changePostList() {
		postListCount++;
		postListChangeCount++;
		methodUtil.changeScene("/view/PostList_Layout.fxml", changePostListBtn);
	}

	// 카테고리
	@FXML
	private Button changeCategoryBtn;

	public static int categoryChangeCount = 0;

	public void changeCategory() {
		categoryChangeCount++;
		methodUtil.changeScene("/view/Category_Layout.fxml", changeCategoryBtn);
	}

}