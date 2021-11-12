package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import util.MethodUtil;

public class Main_Controller {
	
	//싱글톤 
	private String currentLayoutClass;

	private static Main_Controller instance;
    private Main_Controller(){
    }
    public static Main_Controller getInstance(){
        if(instance == null){
            instance = new Main_Controller();
        }
        return instance;
    }
	public void setCLC(String text) {
		currentLayoutClass = text;
	}

	public String getCLC() {
		return currentLayoutClass;
	}

	MethodUtil methodUtil = new MethodUtil();
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