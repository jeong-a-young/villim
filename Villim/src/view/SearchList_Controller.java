package view;

import static view.Home_Controller.searchContent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import database.JDBCUtill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import util.MethodUtil;
import util.PostUtil;
import util.Singleton;

public class SearchList_Controller implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		titleLabel.setText("'" + searchContent + "'의 검색 결과입니다.");
		titleLabel.setAlignment(Pos.CENTER);
		search();
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

	MethodUtil methodUtil = new MethodUtil();

	ResultSet rs = null;
	PreparedStatement pstmt = null;
	String sql = "";
	Connection conn = JDBCUtill.getInstance().getConnection();

	@FXML
	private Label titleLabel;

	@FXML
	private TableView<PostUtil> searchTableView;
	@FXML
	private TableColumn<PostUtil, String> searchCode;
	@FXML
	private TableColumn<PostUtil, String> searchTitle;
	@FXML
	private TableColumn<PostUtil, String> searchWriter;

	ObservableList<PostUtil> searchObservableList;

	public void search() {

		int count = 0;
		sql = "select * from resource where title like '%" + searchContent + "%'";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			searchObservableList = FXCollections.observableArrayList();
			while (rs.next()) {
				String writer = rs.getString("id");
				String title = rs.getString("title");
				String code = rs.getString("code");
				searchCode.setCellValueFactory(new PropertyValueFactory<>("code"));
				searchTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
				searchWriter.setCellValueFactory(new PropertyValueFactory<>("writer"));
				searchObservableList.add(new PostUtil(code, title, writer));
				searchTableView.setItems(searchObservableList);
				count++;
			}
			if (count == 0) {
				alert("검색 결과가 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 게시물 화면 전환
	@FXML
	private Button changeViewPostBtn;

	public void changeViewPost() {
		int choicePostIndax = searchTableView.getSelectionModel().getSelectedIndex();
		if (choicePostIndax > -1) {
			PostUtil postUtil = searchTableView.getSelectionModel().getSelectedItem();
			Singleton.getInstance().setCode(postUtil.getCode());
			methodUtil.changeScene("/view/ViewPost_Layout.fxml", changeViewPostBtn);
		} else {
			alert("게시물을 선택해 주세요.");
		}
	}
	
	// 이전 화면으로 가는 코드
	@FXML
	private Button backButton;

	public void back() {
		methodUtil.backScene(backButton);
	}

}