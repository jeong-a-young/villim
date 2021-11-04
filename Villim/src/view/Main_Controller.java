package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import database.JDBCUtill;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
	Connection con = JDBCUtill.getConnection();
	Alert alert = new Alert(AlertType.WARNING);
	
	public void join() {
		try {
			
			String joinId = join_id.getText();
			String joinPass = join_pass.getText();
			String joinEmail = join_email.getText();
			int tree = 0;
			
			sql = "INSERT INTO users VALUES(?, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(sql);
//			if(joinId != null && joinPass != null && joinName != null && joinEmail != null) {
//				pstmt.setString(1, joinId);
//				pstmt.setString(2, joinPass);
//				pstmt.setString(3, joinName);
//				pstmt.setString(4, joinEmail);
//				pstmt.setInt(5, tree);
//			}
			

			if(joinId != null) {
				pstmt.setString(1, joinId);
			}
			if(joinPass != null) {
				pstmt.setString(2, joinPass);
			}
//			if(joinName != null && name_cnt == joinName.length() && joinName.length() <= 8) {
//				pstmt.setString(3, joinName);
//			} else {
//			alert.setTitle("메세지");
//			alert.setHeaderText(null);
//			alert.setContentText("이름을 다시 지어주세요.");
//			alert.showAndWait();
//			}
//			while(true) {
//				작명 기준에 적합한지 확인할 때 사용하는 변수
//				int name_cnt = 0;
			
//				(조인 네임 입력 다시 받아야함)
			
//				작명 기준에 적합한지 확인
//				String joinName = join_name.getText();
//				for(byte i = 0; i < joinName.length(); i++) {
//					if((int)(joinName.charAt(i)) >= 12593 && (int)(joinName.charAt(i)) <= 12643) {
//					} else {
//						name_cnt += 1;
//					}
//				}
//				if(joinName != null && name_cnt == joinName.length() && joinName.length() <= 8) {
//					pstmt.setString(3, joinName);
//					break;
//				} else {
//					alert.setTitle("메세지");
//					alert.setHeaderText(null);
//					alert.setContentText("이름을 다시 지어주세요.");
//					alert.showAndWait();
//					continue;
//				}
//			}
			if(joinEmail != null) {
				pstmt.setString(4, joinEmail);
			}
			if(tree == 0) {
				pstmt.setInt(5, tree);
			}

			int rs = pstmt.executeUpdate();
			
		    if(rs <= 1) {
//				Parent c = FXMLLoader.load(getClass().getResource("Login_layout.fxml"));
//				Scene scene = new Scene(c);
//				Stage primaryStage = (Stage) join_button.getScene().getWindow();
//				primaryStage.setScene(scene);
				System.out.println("회원가입 성공");
		    }
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void join_id_check() {
		String joinId = join_id.getText();
		
//		sql = "Select " 
	}
	
	// 2. Login
	
	// 로그인 화면 전환
	@FXML
	private Button changeLoginBtn;
	
	public void changeLogin() {
		methodUtil.changeScene("/view/Login_Layout.fxml", changeLoginBtn);
	}
	
}

