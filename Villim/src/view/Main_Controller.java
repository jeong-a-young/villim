package view;

import java.io.File;
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void join() {
		try {

			String joinId = join_id.getText();
			String joinPass = join_pass.getText();
			String joinEmail = join_email.getText();
			int tree = 0;
			
			sql = "INSERT INTO users VALUES(?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			

			if(joinId != null) {
				pstmt.setString(1, joinId);
			}
			if(joinPass != null) {
				pstmt.setString(2, joinPass);
			}

			
			try {
				String joinName = join_name.getText();
				
				//작명 기준에 적합한지 확인할 때 사용하는 변수
				int name_cnt = 0;
				
				//작명 기준에 적합한지 확인
				for(byte i = 0; i < joinName.length(); i++) {
					if((int)(joinName.charAt(i)) >= 12593 && (int)(joinName.charAt(i)) <= 12643) {
					} else {
						name_cnt += 1;
					}
				}
				if(joinName != null && name_cnt == joinName.length() && joinName.length() <= 8) {
					pstmt.setString(3, joinName);
				} else {
					alert.setTitle("메세지");
					alert.setHeaderText(null);
					alert.setContentText("이름을 다시 지어주세요.");
					alert.showAndWait();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
				
			if(tree == 0) {
				pstmt.setInt(4, tree);
			}
			if(joinEmail != null) {
				pstmt.setString(5, joinEmail);
			}

			int rs = pstmt.executeUpdate();
			
		    if(rs <= 1) {
				System.out.println("회원가입 성공");
		    }
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		if(joinPass.equals(joinPassOk)) {
			checkPWImage.setImage(new Image("/resources/checkMark.png"));
			
			System.out.println("확인 성공");
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

