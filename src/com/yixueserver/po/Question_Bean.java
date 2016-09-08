package com.yixueserver.po;

/*无选择问题的bean*/
public class Question_Bean {

	private int q_id;
	private String theme;
	private String content;
	private int status;
	private String time;
	
	public Question_Bean() {
		super();
	}

	public Question_Bean(int q_id,String theme, String content, int status, String time) {
		super();
		this.q_id = q_id;
		this.theme = theme;
		this.content = content;
		this.status = status;
		this.time = time;
	}

	public int getQ_id() {
		return q_id;
	}
	
	public void setQ_id(int q_id) {
		this.q_id = q_id;
	}

	public String getTheme() {
		return theme;
	}
	
	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}

	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
}
