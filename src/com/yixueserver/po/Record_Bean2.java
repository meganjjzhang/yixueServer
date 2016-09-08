package com.yixueserver.po;

public class Record_Bean2 {

	private int r_id;
	private int s_id;
	private String theme;
	private String content;
	private String choice;
	private String correctChoice;
	private int score;
	private long endTime;
	public Record_Bean2() {
		super();
	}
	
	public Record_Bean2(int r_id, int s_id, String theme, String content,
			String choice, String correctChoice, int score, long endTime) {
		super();
		this.r_id = r_id;
		this.s_id = s_id;
		this.theme = theme;
		this.content = content;
		this.choice = choice;
		this.correctChoice = correctChoice;
		this.score = score;
		this.endTime = endTime;
	}

	public int getR_id() {
		return r_id;
	}
	
	public void setR_id(int r_id) {
		this.r_id = r_id;
	}
	
	public int getS_id() {
		return s_id;
	}

	public void setS_id(int s_id) {
		this.s_id = s_id;
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
	public String getChoice() {
		return choice;
	}
	public void setChoice(String choice) {
		this.choice = choice;
	}
	public String getCorrectChoice() {
		return correctChoice;
	}
	public void setCorrectChoice(String correctChoice) {
		this.correctChoice = correctChoice;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
}
