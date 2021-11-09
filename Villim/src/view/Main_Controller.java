package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.JDBCUtill;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.MethodUtil;

public class Main_Controller {
	
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
	
	// 1. Join
	
	// 회원가입 화면 전환
	@FXML
	private Button changeJoinBtn;
	
	public void changeJoin() {
		methodUtil.changeScene("/view/Join_Layout.fxml", changeJoinBtn);
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
	Alert alert = new Alert(AlertType.WARNING);
	
	

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
	    }
	    else if (join_name.getText().length() > 8) {
//			errorScreen.setVisible(true);
//			startScreen.setDisable(true);
//			errorScreenMsg.setText("비밀번호는 8자가 넘어야 합니다");
			System.out.println("닉네임은 8자를 넘을 수 없습니다");
//			errorTitle.setText("[ ERROR ]");
	    }
		else if (!join_pass.getText().equals(join_pass_ok.getText()) || join_pass.getText().isEmpty()) {
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
		} 
		else if (join_email.getText().matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*") || join_email.getText().isEmpty()
				|| !join_email.getText().contains("@")) {
			//errorScreen.setVisible(true);
			//startScreen.setDisable(true);
			//errorScreenMsg.setText("이메일이 잘못되었습니다 확인을 다시 해주세요");
			System.out.println("이메일이 잘못되었습니다 확인을 다시 해주세요");
			//errorTitle.setText("[ ERROR ]");
		}
//		else  if (checkId(email_signUp.getText())) {
//            System.out.println("회원가입 실패");
//            }
//		else {
//			sql = "insert into UserLoginData values('" + email_signUp.getText() + "','" + pw_signUp.getText() + "','','','#ff9200')";
//			try {
//				pstmt = con.prepareStatement(sql);
//				pstmt.executeUpdate();
//				System.out.println("성공");
//				errorScreen.setVisible(true);
//				startScreen.setDisable(true);
//				errorScreenMsg.setText("회원가입이 완료되었습니다");
//				errorTitle.setText("[ ALERT ]");
//
//			} catch (Exception e) {
//				e.printStackTrace();
//				System.out.println("실패");
//			}
//		}
	}

	
	public void join_id_check() {
		String joinId = join_id.getText();
		sql = "select * from users where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, joinId);
			ResultSet rs = pstmt.executeQuery();
			while(true) {
				if(rs.next()) {
					System.out.println(rs.next());
					alert.setTitle("메세지");
					alert.setHeaderText(null);
					alert.setContentText("아이디 중복");
					alert.showAndWait();
				} else {
					break;
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void join_pass_check() {
		String joinPass = join_pass.getText();
		String joinPassOk = join_pass_ok.getText();
		if(joinPass.equals(joinPassOk) && !joinPass.isEmpty()) {
			checkPWImage.setImage(new Image("/resources/checkMark.png"));
			
			System.out.println("확인 성공");
		}else if (joinPass.equals(joinPassOk) && joinPass.isEmpty()){
			checkPWImage.setImage(new Image("/resources/xMark.png"));
			System.out.println("확인 실패");
		} else {
			checkPWImage.setImage(new Image("/resources/xMark.png"));
			System.out.println("확인 실패");
		}
	}
	
	// 2. Login
	
	// 로그인 화면 전환
	@FXML
	private Button changeLoginBtn;
	
	public void changeLogin() {
		methodUtil.changeScene("/view/Login_Layout.fxml", changeLoginBtn);
	}
	
}

