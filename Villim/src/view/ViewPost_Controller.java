package view;

import static view.Login_Controller.userId;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import database.JDBCUtill;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import util.MethodUtil;

public class ViewPost_Controller implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		getPost();
		changeBtn();
	}

	MethodUtil methodUtil = new MethodUtil();

	ResultSet rs = null;
	PreparedStatement pstmt = null;
	String sql = "";
	Connection conn = JDBCUtill.getConnection();

	// 내가 쓴 게시글인지 다른 사람이 쓴 게시글인지 판단
	@FXML
	private BorderPane btnPane;

	int meWrite = 0;
	int otherWrite = 0;

	public void changeBtn() {
		// DB를 이용해서 내가 쓴 게시글인지 다른 사람이 쓴 게시글인지 판단

		// 만약 내가 쓴 게시글이면
		// meWrite++;
		// 다른 사람이 쓴 게시글이면
		// otherWrite++;
		/*
		 * if (meWrite == 1) { methodUtil.changePartScene("/view/MeWrite_Layout.fxml",
		 * btnPane); } else if (otherWrite == 0) {
		 * methodUtil.changePartScene("/viewotherWrite_Layout.fxml", btnPane); }
		 */
	}

	// 게시글 불러오기
	@FXML
	private Label titleLabel;
	@FXML
	private Label writerLabel;
	@FXML
	private Label dateLabel;
	@FXML
	private Label recommendLabel;
	@FXML
	private Label contentLabel;

	public void getPost() {
		sql = "select * from post where id='" + userId + "'"; // post id 이용해서 불러오는 걸로 변경
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				titleLabel.setText(rs.getString("title"));
				contentLabel.setText(rs.getString("content"));
				dateLabel.setText(rs.getString("registration"));
				writerLabel.setText(rs.getString("writer_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 내가 쓴 게시물이라면

	// Edit

	// 수정 화면 전환
	@FXML
	private Button changeEditPostBtn;

	public void changeEditPost() {
		methodUtil.changeScene("/view/EditPost_Layout.fxml", changeEditPostBtn);
	}

	// Delete

	public void delete() {
		// DB 이용해서 삭제 구현
	}

	// 다른 사람이 쓴 게시물이라면

	// Recommend

	// 찜 개수 가져오기
	int recommend = 0;

	public int getRecommend() {

		String countRecommend = "";
		sql = "select recommend from post where writer_id='" + userId + "'"; // post id
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				countRecommend = (rs.getString("recommend"));
				recommend = Integer.parseInt(countRecommend);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recommend;
	}

	// 찜하기 버튼을 눌렀을 때 찜 개수 늘어나게 하기
	public void updateRecommend() {

		int numRecommend = getRecommend() + 1;
		String countRecommend = Integer.toString(numRecommend);

		sql = "update post set recommend='" + countRecommend + "' WHERE writer_id='" + userId + "'"; // recommend 수를 받아 post id를 이용해 업데이트
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 채팅하기
	@FXML
	private Button changeChattingBtn;

	public void changeChatting() {
		methodUtil.changeScene("/view/Chatting_Layout.fxml", changeChattingBtn);
	}

	// 홈 화면 전환
	@FXML
	private Button changeHomeBtn;

	public void changeHome() {
		methodUtil.changeScene("/view/Home_Layout.fxml", changeHomeBtn);
	}

}