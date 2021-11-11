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
	//시작 화면
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
	@FXML
	private TextField write_title;
	@FXML
	private TextArea write_content;
	@FXML
	private Button write_success;

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
	// 게시물 목록
	@FXML
	private Button changePostListBtn;

	int postListCount = 0;

	public void changePostList() {
		methodUtil.changeScene("/view/PostList_Layout.fxml", changePostListBtn);
		postListCount++;
	}

	// 카테고리
	@FXML
	private Button changeCategoryBtn;

	public void changeCategory() {
		methodUtil.changeScene("/view/Category_Layout.fxml", changeCategoryBtn);
	}

	// Category

	// 의상, 소품
	@FXML
	private Button changeClothesBtn;

	int clothesCount = 0;

	public void changeClothes() {
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeClothesBtn);
		clothesCount++;
	}

	// 음반, 악기
	@FXML
	private Button changeInstrumentBtn;

	int instrumentCount = 0;

	public void changeInstrument() {
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeInstrumentBtn);
		instrumentCount++;
	}

	// 전자기기
	@FXML
	private Button changeElectronicsBtn;

	int electronicsCount = 0;

	public void changeElectronics() {
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeElectronicsBtn);
		electronicsCount++;
	}

	// 헬스, 요가
	@FXML
	private Button changeHealthBtn;

	int healthCount = 0;

	public void changeHealth() {
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeHealthBtn);
		healthCount++;
	}

	// 스포츠, 레저
	@FXML
	private Button changeSportsBtn;

	int sportsCount = 0;

	public void changeSports() {
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeSportsBtn);
		sportsCount++;
	}

	// 등산, 낚시, 캠핑
	@FXML
	private Button changeCampingBtn;

	int campingCount = 0;

	public void changeCamping() {
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeCampingBtn);
		campingCount++;
	}

	// 도서, 문구
	@FXML
	private Button changeBookBtn;

	int bookCount = 0;

	public void changeBook() {
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeBookBtn);
		bookCount++;
	}

	// 유아 용품
	@FXML
	private Button changeKidsBtn;

	int kidsCount = 0;

	public void changeKids() {
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeKidsBtn);
		kidsCount++;
	}

	// 반려동물 용품
	@FXML
	private Button changeAnimalBtn;

	int animalCount = 0;

	public void changeAnimal() {
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeAnimalBtn);
		animalCount++;
	}

	// 기타
	@FXML
	private Button changeEtcBtn;

	int etcCount = 0;

	public void changeEtc() {
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeEtcBtn);
		etcCount++;
	}

}
