package util;

public class Singleton {

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	//싱글톤
	private String accountId;
	private String currentLayoutClass;

	public String getPreviousLayoutClass() {
		return previousLayoutClass;
	}

	public void setPreviousLayoutClass(String previousLayoutClass) {
		this.previousLayoutClass = previousLayoutClass;
	}

	public String getCurrentCategory() {
		return currentCategory;
	}

	public void setCurrentCategory(String currentCategory) {
		this.currentCategory = currentCategory;
	}

	private String previousLayoutClass;
	private String currentCategory;

	private static Singleton instance;
    private Singleton(){
    }
    public static Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
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