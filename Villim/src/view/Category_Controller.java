package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import util.MethodUtil;

public class Category_Controller {

	MethodUtil methodUtil = new MethodUtil();

	// 의상, 소품
	@FXML
	private Button changeClothesBtn;

	public static int clothesCount = 0;

	public void changeClothes() {
		clothesCount++;
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeClothesBtn);
	}

	// 음반, 악기
	@FXML
	private Button changeInstrumentBtn;

	public static int instrumentCount = 0;

	public void changeInstrument() {
		instrumentCount++;
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeInstrumentBtn);
	}

	// 전자기기
	@FXML
	private Button changeElectronicsBtn;

	public static int electronicsCount = 0;

	public void changeElectronics() {
		electronicsCount++;
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeElectronicsBtn);
	}

	// 헬스, 요가
	@FXML
	private Button changeHealthBtn;

	public static int healthCount = 0;

	public void changeHealth() {
		healthCount++;
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeHealthBtn);
	}

	// 스포츠, 레저
	@FXML
	private Button changeSportsBtn;

	public static int sportsCount = 0;

	public void changeSports() {
		sportsCount++;
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeSportsBtn);
	}

	// 등산, 낚시, 캠핑
	@FXML
	private Button changeCampingBtn;

	public static int campingCount = 0;

	public void changeCamping() {
		campingCount++;
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeCampingBtn);
	}

	// 도서, 문구
	@FXML
	private Button changeBookBtn;

	public static int bookCount = 0;

	public void changeBook() {
		bookCount++;
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeBookBtn);
	}

	// 유아 용품
	@FXML
	private Button changeKidsBtn;

	public static int kidsCount = 0;

	public void changeKids() {
		kidsCount++;
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeKidsBtn);
	}

	// 반려동물 용품
	@FXML
	private Button changeAnimalBtn;

	public static int animalCount = 0;

	public void changeAnimal() {
		animalCount++;
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeAnimalBtn);
	}

	// 기타
	@FXML
	private Button changeEtcBtn;

	public static int etcCount = 0;

	public void changeEtc() {
		etcCount++;
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeEtcBtn);
	}
	
	// 메인 화면 전환
	@FXML
	private Button changeMainBtn;

	public void changeMain() {
		methodUtil.changeScene("/view/Main_Layout.fxml", changeMainBtn);
	}

	// 카테고리 화면 전환
	@FXML
	private Button changeCategoryBtn;

	public void changeCategory() {
		methodUtil.changeScene("/view/Category_Layout.fxml", changeCategoryBtn);
	}
	
}