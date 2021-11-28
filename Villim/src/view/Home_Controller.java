package view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import database.JDBCUtill;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import util.MethodUtil;
import util.Singleton;

public class Home_Controller implements Initializable {
	// 이 클래스가 실행되면 호출되는 메소드 ^ 이거 있어야함 ^

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (Singleton.getInstance().sceneList.get(Singleton.getInstance().sceneList.size() - 1)
				.equals("/view/Login_Layout.fxml")) {
			alert("반갑습니다 " + Singleton.getInstance().getAccountId() + "님");
		}
		if (Singleton.getInstance().isWriteSuccess()) {
			alert("게시물을 업로드하였습니다");
			Singleton.getInstance().setWriteSuccess(false);
		}
		batchPost();
		postListPage.setPageCount(3);

	}

	public void batchPage() {
		try {
			System.out.println("A1");
			conn = JDBCUtill.getInstance().getConnection();
			sql = "SELECT COUNT(*) FROM resource";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			System.out.println("A2");
			int size = 0;
			if (rs != null) {
				System.out.println("A3");
				rs.next();
				size = rs.getRow(); // get row id
				System.out.println("A4" + size);
			}
			if (size % 4 == 0) {
				System.out.println("A5");
				postListPage.setPageCount(size / 4);
			} else if (size % 4 != 0) {
				System.out.println("A51");
				postListPage.setPageCount((int) (size / 4) + 1);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}



	@FXML
	public Pane postPane1;
	@FXML
	public Pane postPane2;
	@FXML
	public Pane postPane3;
	@FXML
	public Pane postPane4;

	@FXML
	public Text recommand1;
	@FXML
	public Text title1;
	@FXML
	public Text category1;
	@FXML
	public Text writer1;
	@FXML
	public Text time1;
	@FXML
	public ImageView image1;

	@FXML
	public Text recommand2;
	@FXML
	public Text title2;
	@FXML
	public Text category2;
	@FXML
	public Text writer2;
	@FXML
	public Text time2;
	@FXML
	public ImageView image2;

	@FXML
	public Text recommand3;
	@FXML
	public Text title3;
	@FXML
	public Text category3;
	@FXML
	public Text writer3;
	@FXML
	public Text time3;
	@FXML
	public ImageView image3;

	@FXML
	public Text recommand4;
	@FXML
	public Text title4;
	@FXML
	public Text category4;
	@FXML
	public Text writer4;
	@FXML
	public Text time4;
	@FXML
	public ImageView image4;

	@FXML
	public Text nonPostList;

	PreparedStatement pstmt = null;
	PreparedStatement pstmt2 = null;
	String sql = "";
	String sql2 = "";
	Connection conn = null;

	public void batchPost() {
		postPane1.setVisible(false);
		postPane2.setVisible(false);
		postPane3.setVisible(false);
		postPane4.setVisible(false);
		image1.setImage(null);
		image2.setImage(null);
		image3.setImage(null);
		image4.setImage(null);
		List<String> postList = new ArrayList<String>();
		String a = "";
		String sql = "";
		try {
			conn = JDBCUtill.getInstance().getConnection();
			sql = "SELECT id, title, content, category, now, recommand, code FROM resource";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			while (rs.next()) {
				postList.add(rs.getString("code"));
			}
			Singleton.getInstance().setPostList(postList);
			ResultSet rs2;
			int row;
			int rc;
			conn = JDBCUtill.getInstance().getConnection();
			if (Singleton.getInstance().getPostList().size() > 0) {
				nonPostList.setVisible(false);
			}
			for (String t : Singleton.getInstance().getPostList()) {
				sql = "select * from resource where code='" + t + "'";
				sql2 = "select * from resource where code='" + t + "'";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				rs.next();
				rc = rs.getInt("recommand");
				a = rs.getString("title");
				if (Singleton.getInstance().getPostList().indexOf(t) + 1 == 1) {
					postPane1.setVisible(true);
					recommand1.setText(String.valueOf(rc));
					if (a.length() >= 10) {
						title1.setText(a.substring(0, 10) + "...");
					} else {
						title1.setText(a);
					}
					category1.setText(rs.getString("category"));
					writer1.setText(rs.getString("id"));
					time1.setText(rs.getString("now"));
					methodUtil.getResourcePhoto(t, image1);
				} else if (Singleton.getInstance().getPostList().indexOf(t)
						+ 1 == 2) {
					postPane2.setVisible(true);
					recommand2.setText(String.valueOf(rc));
					if (a.length() >= 10) {
						title2.setText(a.substring(0, 10) + "...");
					} else {
						title2.setText(a);
					}
					category2.setText(rs.getString("category"));
					writer2.setText(rs.getString("id"));
					time2.setText(rs.getString("now"));
					methodUtil.getResourcePhoto(t, image2);
				} else if (Singleton.getInstance().getPostList().indexOf(t)
						+ 1 == 3) {
					postPane3.setVisible(true);
					recommand3.setText(String.valueOf(rc));
					if (a.length() >= 10) {
						title3.setText(a.substring(0, 10) + "...");
					} else {
						title3.setText(a);
					}
					category3.setText(rs.getString("category"));
					writer3.setText(rs.getString("id"));
					time3.setText(rs.getString("now"));
					methodUtil.getResourcePhoto(t, image3);
				} else if (Singleton.getInstance().getPostList().indexOf(t)
						+ 1 == 4) {
					postPane4.setVisible(true);
					recommand4.setText(String.valueOf(rc));
					if (a.length() >= 10) {
						title4.setText(a.substring(0, 10) + "...");
					} else {
						title4.setText(a);
					}
					category4.setText(rs.getString("category"));
					writer4.setText(rs.getString("id"));
					time4.setText(rs.getString("now"));
					methodUtil.getResourcePhoto(t, image4);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		batchPage();
	}

	@FXML
	public Pagination postListPage;
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

	MethodUtil methodUtil = new MethodUtil();

	// 검색
	@FXML
	private TextField searchTextField;
	@FXML
	private Button searchBtn;

	public static String searchContent = "";

	public void changeSearch() {
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "";
		Connection conn = JDBCUtill.getInstance().getConnection();

		searchContent = searchTextField.getText();

		if (searchContent.isEmpty()) {
			alert("검색어를 입력해 주세요.");
		} else {
			methodUtil.changeScene("/view/SearchList_Layout.fxml", searchBtn);

			sql = "select * from resource where title like (?)";

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + searchContent + "%");

				rs = pstmt.executeQuery();
				System.out.println(rs.next());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 게시물 작성
	@FXML
	private Button changeWritePostBtn;

	public void changeWritePost() {
		methodUtil.changeScene("/view/WritePost_Layout.fxml", changeWritePostBtn);
	}

	// 게시물 목록
	@FXML
	private Button changePostListBtn;

	public void changePostList() {
		Singleton.getInstance().setCurrentCategory("전체");
		methodUtil.changeScene("/view/PostList_Layout.fxml", changePostListBtn);
	}

	// 카테고리
	@FXML
	private Button changeCategoryBtn;

	public void changeCategory() {
		methodUtil.changeScene("/view/Category_Layout.fxml", changeCategoryBtn);
	}

	// 프로필
	@FXML
	private Button changeProfileBtn;

	public void changeProfile() {
		methodUtil.changeScene("/view/Profile_Layout.fxml", changeProfileBtn);
	}

	// 제작자 정보
	@FXML
	private Button changeInformationBtn;

	public void changeInformation() {
		methodUtil.changeScene("/view/Information_Layout.fxml", changeInformationBtn);
	}

	// 이전 화면 전환
	@FXML
	private Button backButton;

	public void back() {
		methodUtil.backScene(backButton);
	}

}