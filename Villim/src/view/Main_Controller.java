package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import database.JDBCUtill;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Main_Controller {
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
	public void join() {
		try {
			
			String joinId = join_id.getText();
			String joinPass = join_pass.getText();
			String joinName = join_name.getText();
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
			if(joinName != null) {
				pstmt.setString(3, joinName);
			}
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
}

