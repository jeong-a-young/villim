package view;

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

}