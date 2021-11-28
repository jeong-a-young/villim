package util;

public class PostUtil {

	private String code;
	private String title;
	private String writer;
	
	public PostUtil(String code, String title, String writer) {
		super();
		this.code = code;
		this.title = title;
		this.writer = writer;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}
	
}
