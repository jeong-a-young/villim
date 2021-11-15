package view;

import javafx.fxml.Initializable;
import util.Singleton;

import java.net.URL;
import java.util.ResourceBundle;

public class Chatting_Controller implements Initializable {
    //이 클래스가 실행되면 호출되는 메소드                   ^ 이거 있어야함 ^
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //현재 화면의 이전 화면을 변수
        Singleton.getInstance().setPreviousLayoutClass(Singleton.getInstance().getCLC());
        //현재 화면을 나타내는 변수
        Singleton.getInstance().setCLC(getClass().getSimpleName());

    }

}
