package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import util.MethodUtil;

public class Category_Controller {

	MethodUtil methodUtil = new MethodUtil();

	// 의상, 소품
	@FXML
	private Button changeClothesBtn;

	public static String checkCategory;

	public void changeClothes() {
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeClothesBtn);
	}

	// 음반, 악기
	@FXML
	private Button changeInstrumentBtn;

	public void changeInstrument() {
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeInstrumentBtn);
	}

	// 전자기기
	@FXML
	private Button changeElectronicsBtn;

	public void changeElectronics() {
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeElectronicsBtn);
	}

	// 헬스, 요가
	@FXML
	private Button changeHealthBtn;

	public void changeHealth() {
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeHealthBtn);
	}

	// 스포츠, 레저
	@FXML
	private Button changeSportsBtn;

	public void changeSports() {
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeSportsBtn);
	}

	// 등산, 낚시, 캠핑
	@FXML
	private Button changeCampingBtn;

	public void changeCamping() {
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeCampingBtn);
	}

	// 도서, 문구
	@FXML
	private Button changeBookBtn;

	public void changeBook() {
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeBookBtn);
	}

	// 유아 용품
	@FXML
	private Button changeKidsBtn;

	public void changeKids() {
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeKidsBtn);
	}

	// 반려동물 용품
	@FXML
	private Button changeAnimalBtn;

	public void changeAnimal() {
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeAnimalBtn);
	}

	// 기타
	@FXML
	private Button changeEtcBtn;

	public void changeEtc() {
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeEtcBtn);
	}
	
	// 홈 화면 전환
	@FXML
	private Button changeHomeBtn;

	public void changeHome() {
		methodUtil.changeScene("/view/Home_Layout.fxml", changeHomeBtn);
	}

	// 카테고리 화면 전환
	@FXML
	private Button changeCategoryBtn;

	public void changeCategory() {
		methodUtil.changeScene("/view/Category_Layout.fxml", changeCategoryBtn);
	}
	
}